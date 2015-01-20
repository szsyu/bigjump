package com.ect.bigjump.controller;

import com.ect.bigjump.domain.*;
import com.ect.bigjump.service.*;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户任务前端控制类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-17
 */
@Controller
@RequestMapping("/Task")
public class TaskController extends BaseController {

    @Resource(name = "userActionService")
    private UserActionService userActionService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "documentService")
    private DocumentService documentService;

    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "repositoryService")
    private RepositoryService repositoryService;

    @Resource(name = "runtimeService")
    private RuntimeService runtimeService;

    @Resource(name = "documentDataService")
    private DocumentDataService documentDataService;

    @Resource(name = "documentReplyService")
    private DocumentReplyService documentReplyService;

    @Resource(name = "docAssigneeCandidatesService")
    private DocAssigneeCandidatesService docAssigneeCandidatesService;


    /**
     * Approve Task
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/approveTask", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView approveTask(@RequestParam("taskId") String taskId, HttpServletRequest request) {
        String comment = request.getParameter("comment");
        try {
            userActionService.approve(taskId, comment);
            return new ModelAndView("redirect:/Task/showMyTaskList");
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.getMessage());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * 拾取并Approve
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/claimAndApproveTask", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView claimAndApproveTask(@RequestParam("taskId") String taskId, HttpServletRequest request) {
        String comment = request.getParameter("comment");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        User user = userService.getByUserName(userName);
        try {
            userActionService.claimAndApproval(taskId, user.getId(), comment);
            return new ModelAndView("redirect:/Task/showMyTaskList");
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e);
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * Reject 操作
     *
     * @param taskId
     * @param request
     * @return
     */
    @RequestMapping(value = "rejectTask", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView rejectTask(@RequestParam("taskId") String taskId, HttpServletRequest request) {
        String comment = request.getParameter("comment");

        try {
            userActionService.reject(taskId, comment);
            return new ModelAndView("redirect:/Task/showMyTaskList");
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.getMessage());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }


    /**
     * 拾取并Reject操作
     *
     * @param taskId
     * @param request
     * @return
     */
    @RequestMapping(value = "claimAndRejectTask", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView claimAndRejectTask(@RequestParam("taskId") String taskId, HttpServletRequest request) {
        String comment = request.getParameter("comment");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        User user = userService.getByUserName(userName);
        try {
            userActionService.claimAndReject(taskId, user.getId(), comment);
            return new ModelAndView("redirect:/Task/showMyTaskList");
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e);
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * 用户任务列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/showMyTaskList", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView showMyTaskList(HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        User user = userService.getByUserName(userName);
        Map model = new HashMap<>();

        String page = request.getParameter("page");
        int pageNo = (page == null || "".equals(page)) ? 1 : Integer.parseInt(page);

        String documentNumberLike = request.getParameter("q_documentNumberLike");
        documentNumberLike = StringUtils.isBlank(documentNumberLike) ? null : "%" + documentNumberLike + "%";

        String documentCode = request.getParameter("q_documentCode");
        documentCode = StringUtils.isBlank(documentCode) ? null : documentCode;

        Page<DocAssigneeCandidates> docAssCanPage = docAssigneeCandidatesService.queryForPage(pageNo, user.getId(), documentNumberLike, documentCode);
        model.put("docAssCanPage", docAssCanPage);

        List<Document> documentList = documentService.getActiveDocuments();
        model.put("documentList", documentList);

        return new ModelAndView("/Task/showMyTaskList", model);
    }

    /**
     * 所有任务
     *
     * @return
     */
    @RequestMapping(value = "/showAllTaskList")
    public ModelAndView showAllTaskList() {
        Map model = new HashMap<>();
        model.put("myTaskList", docAssigneeCandidatesService.getAll());
        return new ModelAndView("/Task/showMyTaskList", model);
    }

    /**
     * 查看Task的详细信息页
     *
     * @param docAssCanId
     * @return
     */
    @RequestMapping(value = "/viewTaskDetail")
    public ModelAndView viewTaskDetail(@RequestParam("docAssCanId") Long docAssCanId) {
        Map model = new HashMap<>();

        DocAssigneeCandidates docAssigneeCandidates = docAssigneeCandidatesService.get(docAssCanId);
        //Long documentDataId = docAssigneeCandidates.getDocumentMaster().getDocumentData().getId();
        //获取Document Detail
        try {
            DocumentDetail documentDetail = documentDataService.getDocumentDetail(docAssigneeCandidates.getDocumentMaster());
            model.put("documentDetail", documentDetail);
        } catch (Exception e) {
            model = new HashMap<>();
            model.put("exception", e.toString());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
        //获取历史签核记录
        List<DocumentReply> documentReplyList = documentReplyService.getDocumentReplyListByDocumentMaster(docAssigneeCandidates.getDocumentMaster());
        model.put("documentReplyList", documentReplyList);
        model.put("taskDelegateType", docAssigneeCandidates.getDelegateType());
        model.put("taskId", docAssigneeCandidates.getTaskId());

        return new ModelAndView("/Task/viewTaskDetail", model);
    }

    /**
     * 获取当前任务的Activity和流程定义图
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/showCurrActImg")
    public ModelAndView showCurrActImg(@RequestParam("taskId") String taskId) {
        Map<String, Object> model = new HashMap<>();
        try {
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            ProcessDefinition processDefinition = repositoryService.getProcessDefinition(task.getProcessDefinitionId());
            String deploymentId = processDefinition.getDeploymentId();
            String imageName = processDefinition.getDiagramResourceName();
            //获取当前活动的坐标
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            String currentActivitiId = processInstance.getActivityId();
            ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
            //使用流程定义通过currentActivitiId得到活动对象
            ActivityImpl activity = processDefinitionEntity.findActivity(currentActivitiId);
            //获取活动的坐标
            model.put("x", activity.getX());
            model.put("y", activity.getY());
            model.put("width", activity.getWidth());
            model.put("height", activity.getHeight());
            model.put("deploymentId", deploymentId);
            model.put("imageName", imageName);

            return new ModelAndView("/Task/showCurrActImg", model);
        } catch (Exception e) {
            model.put("exception", "Hint:流程实例已结束.");
            return new ModelAndView("/Common/exceptionOccurs", model);
        }
    }

    /**
     * 获取流程定义图
     *
     * @param deploymentId
     * @param imageName
     * @param response
     * @return
     */
    @RequestMapping(value = "/viewProcDefImage")
    public String viewProcDefImage(@RequestParam("deploymentId") String deploymentId, @RequestParam("imageName") String imageName, HttpServletResponse response) {

        InputStream in = repositoryService.getResourceAsStream(deploymentId, imageName);

        try {
            OutputStream out = response.getOutputStream();
            // 把图片的输入流写入resp的输出流中
            byte[] b = new byte[1024];
            for (int len = -1; (len = in.read(b)) != -1; ) {
                out.write(b, 0, len);
            }   // 关闭流
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
