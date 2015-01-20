package com.ect.bigjump.controller;

import com.ect.bigjump.domain.*;
import com.ect.bigjump.service.*;
import com.ect.bigjump.utility.DateTimeParse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Document前端控制类,包括Document和Process的配置操作
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
@Controller
@RequestMapping("/Document")
public class DocumentController extends BaseController {

    @Resource(name = "documentService")
    private DocumentService documentService;

    @Resource(name = "documentDataLevelService")
    private DocumentDataLevelService documentDataLevelService;

    @Resource(name = "documentDictionaryService")
    private DocumentDictionaryService documentDictionaryService;

    @Resource(name = "documentProcessService")
    private DocumentProcessService documentProcessService;

    @Resource(name = "processVariableService")
    private ProcessVariableService processVariableService;

    /**
     * Document分页显示查询页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/showDocumentList")
    public ModelAndView showDocumentList(HttpServletRequest request) {
        Map model = new HashMap<>();

        String page = request.getParameter("page");
        String documentNameLike = request.getParameter("documentNameLike");
        int pageNo = (page == null || "".equals(page)) ? 1 : Integer.parseInt(page);
        documentNameLike = documentNameLike == null || "".equals(documentNameLike) ? null : documentNameLike;

        Page<Document> documentPage = documentService.queryForPage(pageNo, documentNameLike);
        model.put("documentPage", documentPage);
        return new ModelAndView("/Document/showDocumentList", model);
    }

    /**
     * 新增定义Document页面
     *
     * @return
     */
    @RequestMapping(value = "/defineDocument")
    public ModelAndView defineDocument() {
        return new ModelAndView("/Document/defineDocument");
    }

    /**
     * 保存新增的Document
     *
     * @param document
     * @return
     */
    @RequestMapping(value = "/saveDocument", method = RequestMethod.POST)
    public String saveDocument(Document document) {
        try {
            documentService.add(document);
            return "redirect:/Document/showDocumentDataLevel?documentId=" + document.getId();
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * 更新Document
     *
     * @param document
     * @return
     */
    @RequestMapping(value = "/updateDocument", method = RequestMethod.POST)
    public String updateDocument(Document document) {
        try {
            documentService.update(document);
            return "redirect:/Document/showDocumentList";
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * Document 数据层次页面
     *
     * @param documentId
     * @return
     */
    @RequestMapping(value = "/showDocumentDataLevel")
    public ModelAndView showDocumentDataLevel(@RequestParam("documentId") Long documentId) {
        Map model = new HashMap<>();
        Document document = documentService.get(documentId);
        model.put("document", document);

        return new ModelAndView("/Document/showDocumentDataLevel", model);
    }

    /**
     * 展现指定Data Level下的数据字典定义
     *
     * @param documentDataLevelId
     * @return
     */
    @RequestMapping(value = "/showDocDictForDataLevel", method = RequestMethod.GET)
    public ModelAndView showDocDictForDataLevel(@RequestParam("documentDataLevelId") Long documentDataLevelId) {
        Map model = new HashMap<>();

        DocumentDataLevel documentDataLevel = documentDataLevelService.get(documentDataLevelId);
        model.put("documentDataLevel", documentDataLevel);

        List<DocumentDictionary> documentDictionaryList = documentDataLevelService.get(documentDataLevelId).getDocumentDictionaryList();
        model.put("documentDictionaryList", documentDictionaryList);

        return new ModelAndView("/Document/showDocDictForDataLevel", model);
    }

    /**
     * Ajax方式获取下一个DataLevel,当前只支持两层数据
     *
     * @param documentId
     * @return
     */
    @RequestMapping(value = "/getNextDataLevelAjax", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getNextDataLevelAjax(@RequestParam("documentId") Long documentId) {
        Map model = new HashMap<>();
        Document document = documentService.get(documentId);
        int dataLevelCount = document.getDocumentDataLevelList().size();
        model.put("nextDataLevel", dataLevelCount);
        return model;
    }

    /**
     * 保存新增的Data Level,刷新主页面
     *
     * @param documentDataLevel
     * @param documentId
     * @return
     */
    @RequestMapping(value = "/saveDocumentDataLevel", method = RequestMethod.POST)
    public String saveDocumentDataLevel(DocumentDataLevel documentDataLevel, @RequestParam("documentId") Long documentId) {

        Document document = documentService.get(documentId);
        documentDataLevel.setDocument(document);
        try {
            documentDataLevelService.add(documentDataLevel);
            String s = "redirect:/Document/showDocumentDataLevel?documentId=" + documentId;
            return "redirect:/Document/showDocumentDataLevel?documentId=" + documentId;
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * 更新Data Level,并刷新主页面
     *
     * @param documentDataLevel
     * @param documentId
     * @return
     */
    @RequestMapping(value = "/updateDocumentDataLevel", method = RequestMethod.POST)
    public String updateDocumentDataLevel(DocumentDataLevel documentDataLevel, @RequestParam("documentId") Long documentId) {
        try {
            documentDataLevelService.update(documentDataLevel);

            return "redirect:/Document/showDocumentDataLevel?documentId=" + documentId;
        } catch (Exception e) {
            e.printStackTrace();
            return "/Common/error";
        }
    }

    /**
     * 更新Document Dictionary
     *
     * @param documentDictionary
     * @return
     */
    @RequestMapping(value = "/updateDocumentDict", method = RequestMethod.POST)
    public String updateDocumentDict(DocumentDictionary documentDictionary) {
        DocumentDictionary originalDocumentDictionary = documentDictionaryService.get(documentDictionary.getId());
        Long documentDataLevelId = originalDocumentDictionary.getDocumentDataLevel().getId();
        originalDocumentDictionary.setIsActive(documentDictionary.getIsActive());
        originalDocumentDictionary.setVisible(documentDictionary.getVisible());
        originalDocumentDictionary.setColumnLabel(documentDictionary.getColumnLabel());
        originalDocumentDictionary.setColumnName(documentDictionary.getColumnName());
        originalDocumentDictionary.setDescription(documentDictionary.getDescription());
        try {
            documentDictionaryService.update(originalDocumentDictionary);
            return "redirect:/Document/showDocDictForDataLevel?documentDataLevelId=" + documentDataLevelId;
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * Document Process分页显示
     *
     * @param documentId
     * @param request
     * @return
     */
    @RequestMapping(value = "/showDocProcessList", method = RequestMethod.GET)
    public ModelAndView showDocProcessList(@RequestParam("documentId") Long documentId, HttpServletRequest request) {
        Map model = new HashMap<>();

        String page = request.getParameter("page");
        int pageNo = (page == null || "".equals(page)) ? 1 : Integer.parseInt(page);

        Page<DocumentProcess> documentProcessPage = documentProcessService.queryForPage(pageNo, documentId);
        model.put("documentProcessPage", documentProcessPage);
        model.put("document", documentService.get(documentId));
        return new ModelAndView("/Document/showDocProcessList", model);

    }

    /**
     * 将Process设置为对应Document的当前Process
     *
     * @param documentProcessId
     * @return
     */
    @RequestMapping(value = "/setAsCurrentProcess", method = RequestMethod.GET)
    public String setAsCurrentProcess(@RequestParam("documentProcessId") Long documentProcessId) {
        Document document = documentProcessService.get(documentProcessId).getDocument();
        try {
            documentProcessService.setCurrentProcess(documentProcessId);
            return "redirect:/Document/showDocProcessList?documentId=" + document.getId();
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * 上传定义Process的ZIP文件
     *
     * @param zipFile
     * @param documentId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/uploadDiagramZipFile")
    @ResponseBody
    public Map<String, Object> uploadDiagramZipFile(@RequestParam(value = "zipFile", required = false) MultipartFile zipFile, @RequestParam("documentId") Long documentId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String originalFilename;
        String newFileName = null;
        try {
            String realPath = documentProcessService.getRealPathForDiagramZipStore(request);
            if (zipFile.isEmpty()) {
                model.put("status", "empty");
                return model;
            } else {
                try {
                    originalFilename = zipFile.getOriginalFilename();
                    newFileName = originalFilename.substring(0, originalFilename.lastIndexOf("."))
                            + DateTimeParse.convertDate2Str(new Date(), "yyyyMMddHHmmss")
                            + originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
                    FileUtils.copyInputStreamToFile(zipFile.getInputStream(), new File(realPath, newFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    model.put("status", "error");
                    return model;
                }
            }
        } catch (Exception e) {

        }
        model.put("status", "success");
        model.put("filename", documentProcessService.getRelativePathForDiagramZipStore() + newFileName);
        return model;
    }

    /**
     * 根据上传的ZIP Diagram部署Process
     *
     * @param zipPath
     * @param documentId
     * @return
     */
    @RequestMapping(value = "deployProcessByZip")
    public String deployProcessByZip(@RequestParam("zipPath") String zipPath, @RequestParam("documentId") Long documentId, HttpServletRequest request) {
        String realZipPath = request.getSession().getServletContext().getRealPath("") + zipPath;
        try {
            documentProcessService.deployProcessByZip(realZipPath, documentId);
            return "redirect:/Document/showDocProcessList?documentId=" + documentId;
        } catch (Exception e) {
            e.printStackTrace();
            return "/Common/error";
        }
    }

    /**
     * Process的变量定义列表
     *
     * @param documentProcessId
     * @return
     */
    @RequestMapping(value = "/showProcessVariableList")
    public ModelAndView showProcessVariableList(@RequestParam("documentProcessId") Long documentProcessId) {
        Map model = new HashMap<>();

        DocumentProcess documentProcess = documentProcessService.get(documentProcessId);
        model.put("processVariableList", documentProcess.getProcessVariableList());
        model.put("document", documentProcess.getDocument());
        model.put("documentProcess", documentProcess);
        model.put("dataTypeList", processVariableService.getDataTypes());
        model.put("activeDocDictList", documentDictionaryService.getActiveDocDictForDoc(documentProcess.getDocument()));
        return new ModelAndView("/Document/showProcessVariableList", model);
    }

    /**
     * 保存流程变量定义
     *
     * @param processVariable
     * @param documentProcessId
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveProcessVariable", method = RequestMethod.POST)
    public String saveProcessVariable(ProcessVariable processVariable, @RequestParam("documentProcessId") Long documentProcessId, HttpServletRequest request) {
        DocumentProcess documentProcess = documentProcessService.get(documentProcessId);
        processVariable.setDocumentProcess(documentProcess);
        //当选择的是Referenced Doc Data时
        if ("R".equals(processVariable.getSourceType())) {
            String documentDictIdStr = request.getParameter("referencedDataColumnId");
            Long docmentDictId = documentDictIdStr == null ? null : Long.parseLong(documentDictIdStr);
            DocumentDictionary documentDictionary;
            documentDictionary = docmentDictId == null ? null : documentDictionaryService.get(docmentDictId);
            processVariable.setDocumentDictionary(documentDictionary);
            processVariable.setValue(null);
        } else if ("D".equals(processVariable.getSourceType())) {//当选择Static Value时
            processVariable.setDocumentDictionary(null);
        }
        try {
            processVariableService.add(processVariable);
            return "redirect:/Document/showProcessVariableList?documentProcessId=" + documentProcessId;
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * 启用或停用流程参数
     *
     * @param processVariableId
     * @param action
     * @return
     */
    @RequestMapping(value = "/activeOpForProVar", method = RequestMethod.GET)
    public String activeOpForProVar(@RequestParam("processVariableId") Long processVariableId, @RequestParam("action") String action) {
        ProcessVariable processVariable = processVariableService.get(processVariableId);

        if ("inactive".equals(action)) {
            processVariable.setIsActive("N");
        } else if ("active".equals(action)) {
            processVariable.setIsActive("Y");
        }

        try {
            processVariableService.update(processVariable);
            return "redirect:/Document/showProcessVariableList?documentProcessId=" + processVariable.getDocumentProcess().getId();
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * 删除流程变量
     *
     * @param processVariableId
     * @return
     */
    @RequestMapping(value = "/deleteProcessVariable", method = RequestMethod.GET)
    public String deleteProcessVariable(@RequestParam("processVariableId") Long processVariableId) {
        ProcessVariable processVariable = processVariableService.get(processVariableId);
        Long documentProcessId = processVariable.getDocumentProcess().getId();
        try {
            processVariableService.delete(processVariable);
            return "redirect:/Document/showProcessVariableList?documentProcessId=" + documentProcessId;
        } catch (Exception e) {
            return "/Common/error";
        }
    }

}
