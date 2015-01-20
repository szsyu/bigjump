package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocumentProcessDao;
import com.ect.bigjump.domain.Document;
import com.ect.bigjump.domain.DocumentProcess;
import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.ProcessVariable;
import com.ect.bigjump.service.DocumentProcessService;
import com.ect.bigjump.service.DocumentService;
import com.ect.bigjump.service.ProcessVariableService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Document Process Service类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-06
 */
@Service("documentProcessService")
public class DocumentProcessServiceImpl extends BaseServiceImpl<DocumentProcess, Long> implements DocumentProcessService {

    private static final String ORDER_BY = " order by isCurrentVersion desc,version desc";

    private static final int PAGE_SIZE = 10;

    private static final String ZIP_DIAGRAM_SAVE_DIR = "/upload/DiagramZip/";

    @Resource(name = "documentProcessDao")
    private DocumentProcessDao documentProcessDao;

    @Resource(name = "documentService")
    private DocumentService documentService;

    @Resource(name = "repositoryService")
    private RepositoryService repositoryService;

    @Resource(name = "processVariableService")
    private ProcessVariableService processVariableService;

    @Override
    public BaseDao<DocumentProcess, Long> getDao() {
        return documentProcessDao;
    }

    @Override
    public Page<DocumentProcess> queryForPage(int currentPage, Long documentId) {
        String sql = "select * from BJ_DOCUMENT_PROCESS where 1 = 1";
        SQLQuery query;
        String baseUrl;

        Object[] params = {documentId};
        sql = sql + " and documentId = ?" + ORDER_BY;
        query = documentProcessDao.createSQLQuery(sql, params);
        baseUrl = "../Document/showDocProcessList?documentId=" + documentId + "&page=";


        int allRow = query.list().size();
        int totalPage = Page.countTotalPage(PAGE_SIZE, allRow);
        int offset = Page.countOffset(PAGE_SIZE, currentPage);

        query.setFirstResult(offset);
        query.setMaxResults(PAGE_SIZE);
        List<DocumentProcess> documentProcessList = query.addEntity(DocumentProcess.class).list();

        Page<DocumentProcess> page = new Page<DocumentProcess>();
        page.setBasePageUrl(baseUrl);
        page.setPageSize(PAGE_SIZE);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(documentProcessList);
        page.init();

        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void setCurrentProcess(Long documentProcessId) throws Exception {
        DocumentProcess documentProcess = get(documentProcessId);
        //将当前的Process设置为非当前
        String sqlUpdateOld = "update BJ_DOCUMENT_PROCESS set isCurrentVersion = ? where documentId = ? and isCurrentVersion = ?";
        Object[] paramsUpdateOld = {"N", documentProcess.getDocument().getId(), "Y"};
        SQLQuery sqlQueryUpdateOld = documentProcessDao.createSQLQuery(sqlUpdateOld, paramsUpdateOld);
        sqlQueryUpdateOld.executeUpdate();
        //设置新的当前Process
        documentProcess.setIsCurrentVersion("Y");
        update(documentProcess);
    }

    @Override
    public String getRelativePathForDiagramZipStore() {
        return ZIP_DIAGRAM_SAVE_DIR;
    }

    @Override
    public String getRealPathForDiagramZipStore(HttpServletRequest request) throws Exception {

        String realPath = request.getSession().getServletContext().getRealPath(ZIP_DIAGRAM_SAVE_DIR);

        File path = new File(realPath);
        if (!path.exists()) {
            path.mkdirs();
        }

        return realPath;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deployProcessByZip(String zipFilePath, Long documentId) throws Exception {
        Document document = documentService.get(documentId);

        File file = new File(zipFilePath);
        InputStream inputStream = new FileInputStream(file);
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        //部署新流程
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();
        //获取部署的流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        //创建Document Process对象
        DocumentProcess documentProcess = new DocumentProcess();
        documentProcess.setDocument(document);
        documentProcess.setProcessDefinitionId(processDefinition.getId());
        documentProcess.setProcessDefinitionKey(processDefinition.getKey());
        documentProcess.setProcessName(processDefinition.getName());
        documentProcess.setVersion(processDefinition.getVersion());
        documentProcess.setIsCurrentVersion("N");
        //保存部署的流程定义
        add(documentProcess);

        //获取Document当前使用的流程定义,并将其下isActive为Y的Process Variable增加到新部署的流程
        DocumentProcess currentDocProcess = getCurrentProcess(documentId);
        List<ProcessVariable> processVariableList = currentDocProcess.getProcessVariableList();
        //ProcessVariable processVariableNew = new ProcessVariable();
        for (ProcessVariable processVariable : processVariableList) {
            ProcessVariable processVariableNew = new ProcessVariable();
            if ("Y".equals(processVariable.getIsActive())) {

                processVariableNew.setDocumentProcess(documentProcess);
                processVariableNew.setIsActive(processVariable.getIsActive());
                processVariableNew.setDocumentDictionary(processVariable.getDocumentDictionary());
                processVariableNew.setDescription(processVariable.getDescription());
                processVariableNew.setDataType(processVariable.getDataType());
                processVariableNew.setValue(processVariable.getValue());
                processVariableNew.setVariableName(processVariable.getVariableName());
                processVariableNew.setSourceType(processVariable.getSourceType());

                processVariableService.add(processVariableNew);
            }
        }


    }

    @Override
    public DocumentProcess getCurrentProcess(Long documentId) {
        String sql = "select * from BJ_DOCUMENT_PROCESS where documentId = ? and isCurrentVersion = ?";
        Object[] params = {documentId, "Y"};
        SQLQuery sqlQuery = documentProcessDao.createSQLQuery(sql, params);
        DocumentProcess currentDocProcess = (DocumentProcess) sqlQuery.addEntity(DocumentProcess.class).uniqueResult();
        return currentDocProcess;
    }


}
