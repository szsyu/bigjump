package com.ect.bigjump.service.impl;

import com.ect.bigjump.domain.*;
import com.ect.bigjump.service.*;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Document Data导入Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Service("documentDataImportingService")
public class DocumentDataImportServiceImpl implements DocumentDataImportService {

    private static final int DATA_COLUMN_COUNT = 32;

    @Resource(name = "docDataInterfaceService")
    private DocDataInterfaceService docDataInterfaceService;

    @Resource(name = "documentService")
    private DocumentService documentService;

    @Resource(name = "docDataImpExceptionService")
    private DocDataImpExceptionService docDataImpExceptionService;

    @Resource(name = "documentDataLevelService")
    private DocumentDataLevelService documentDataLevelService;

    @Resource(name = "processVariableService")
    private ProcessVariableService processVariableService;

    @Resource(name = "documentProcessService")
    private DocumentProcessService documentProcessService;

    @Resource(name = "documentDataService")
    private DocumentDataService documentDataService;

    @Resource(name = "docAssigneeCandidatesService")
    private DocAssigneeCandidatesService docAssigneeCandicatesService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "documentMasterService")
    private DocumentMasterService documentMasterService;

    @Resource(name = "runtimeService")
    private RuntimeService runtimeService;

    @Resource(name = "taskService")
    private TaskService taskService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public synchronized void  startImporting() {
        Date startDate = new Date();
        //获取Data Level为0的未处理的数据
        List<DocDataInterface> docDataInterfaceList = docDataInterfaceService.getUnprocessedData(0);
        for (DocDataInterface docDataInterface : docDataInterfaceList) {
            documentDataHandleFromZeroLevel(docDataInterface);
        }
        Date endDate = new Date();
        System.out.println("Process started at:" + startDate + " ended at:" +endDate);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void documentDataHandleFromZeroLevel(DocDataInterface docDataInterface) {

        //验证成功时
        if (checkDataIntegrity(docDataInterface)) {

            docDataInterface.setProcessStatus("A");
            try {
                //将接口表数据复制到Document Data表
                DocumentData documentData0 = copyInterfaceToDestination(docDataInterface);

                //获取流程变量,启动流程实例
                DocumentProcess currentDocumentProcess = documentProcessService.getCurrentProcess(docDataInterface.getDocument().getId());
                ProcessInstance processInstance = runtimeService.
                        startProcessInstanceById(currentDocumentProcess.getProcessDefinitionId(), getProcessVariables(docDataInterface));

                //新增Document Master数据
                DocumentMaster documentMaster = new DocumentMaster();
                documentMaster.setDocument(docDataInterface.getDocument());
                documentMaster.setApprovalStatus("A");
                documentMaster.setProcessInstanceId(processInstance.getProcessInstanceId());
                documentMaster.setDocumentNumber(docDataInterface.getDocumentNumber());
                documentMaster.setProcessCode("A");
                documentMaster.setLastApprovalDate(new Date());
                documentMaster.setDocumentData(documentData0);
                documentMasterService.add(documentMaster);

                //新增当前的document assignee 和 candicates
                List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
                for (int i = 0; i < taskList.size(); i++) {
                    String assignee = taskList.get(i).getAssignee();
                    String[] assigneeArray = StringUtils.split(assignee, ",");
                    if (assigneeArray.length == 1) {
                        DocAssigneeCandidates docAssigneeCandidates = new DocAssigneeCandidates();
                        docAssigneeCandidates.setDelegateType("A");
                        docAssigneeCandidates.setDocumentMaster(documentMaster);
                        docAssigneeCandidates.setNodeName(taskList.get(i).getName());
                        docAssigneeCandidates.setTaskId(taskList.get(i).getId());
                        docAssigneeCandidates.setUser(userService.get(Long.parseLong(assigneeArray[0])));
                        docAssigneeCandicatesService.add(docAssigneeCandidates);
                    } else if (assigneeArray.length > 1) {
                        for (int j = 0; j < assigneeArray.length; j++) {
                            DocAssigneeCandidates docAssigneeCandidates = new DocAssigneeCandidates();
                            docAssigneeCandidates.setDelegateType("C");
                            docAssigneeCandidates.setDocumentMaster(documentMaster);
                            docAssigneeCandidates.setNodeName(taskList.get(i).getName());
                            docAssigneeCandidates.setTaskId(taskList.get(i).getId());
                            docAssigneeCandidates.setUser(userService.get(Long.parseLong(assigneeArray[j])));
                            docAssigneeCandicatesService.add(docAssigneeCandidates);
                        }
                    }
                }
            } catch (Exception e) {
                //TODO SECTION
            }

        } else {
            docDataInterface.setProcessStatus("E");
        }

        /*
        try {
            docDataInterfaceService.update(docDataInterface);
        } catch (Exception e) {
            //TODO SECTION
        }
         */
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean checkDataIntegrity(DocDataInterface docDataInterface) {
        DocumentProcess currentDocumentProcess = documentProcessService.getCurrentProcess(docDataInterface.getDocument().getId());
        List<ProcessVariable> processVariableList = currentDocumentProcess.getProcessVariableList();
        Map<String, Object> processInstanceVariableMap = new HashMap<>();

        /**
         * Step 1
         * 判断Document是否可用
         */
        if (!documentService.checkAvaiableByCode(docDataInterface.getDocument().getDocumentCode())) {
            docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                    "Document:" + docDataInterface.getDocument().getDocumentCode() + " is unavaiable!");
            return false;
        }

        /**
         * Step 2
         * 判断dataLevel为0的数据的Document Number是否为空
         */
        if (StringUtils.isBlank(docDataInterface.getDocumentNumber())) {
            docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                    "Column:documentNumber cannot be blank!");
            return false;
        }

        /**
         * Step 3
         * 判断数据是否满足Document Dictionary的定义,先判断dataLevel为0的,即docDataInterface本身
         */
        List<DocumentDataLevel> documentDataLevelList = docDataInterface.getDocument().getDocumentDataLevelList();
        if (documentDataLevelList.isEmpty()) {
            docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                    "Document:" + docDataInterface.getDocument().getDocumentCode() + " has no DataLevel definition!");
            return false;
        }
        Collections.sort(documentDataLevelList);

        for (int i = 0; i < documentDataLevelList.size(); i++) {
            /**
             * Step 3.1
             * 判断dataLevel为0时,即docDataInterface本身
             */
            if (i == 0) {
                List<DocumentDictionary> documentDictionaryList0 = documentDataLevelList.get(i).getDocumentDictionaryList();

                /**
                 * Step 3.1.1
                 * 判断是否有dataLevel为0的Document Dictionary定义
                 */
                if (documentDictionaryList0.isEmpty()) {
                    docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                            "Data Level:" + documentDataLevelList.get(i).getDataLevel() + " has no Document Dictionary!");
                    return false;
                }

                if (!auditData(docDataInterface, documentDictionaryList0, processVariableList)) {
                    return false;
                }

            }
            /**
             * Step 4
             * 判断dataLevel为1时的接口数据是否有效
             */
            else if (i == 1) {
                List<DocDataInterface> childDataList = docDataInterfaceService.getChildData(docDataInterface);
                /**
                 * Step 4.1
                 */
                if (childDataList.isEmpty()) {
                    docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                            "Data Level:" + documentDataLevelList.get(i).getDataLevel() + " has no interface data!");
                    return false;
                }

                List<DocumentDictionary> documentDictionaryList1 = documentDataLevelList.get(i).getDocumentDictionaryList();

                /**
                 * Step 4.2
                 * 判断是否有dataLevel为1的Document Dictionary定义
                 */
                if (documentDictionaryList1.isEmpty()) {
                    docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                            "Data Level:" + documentDataLevelList.get(i).getDataLevel() + " has no Document Dictionary!");
                    return false;
                }
                /**
                 * Step 4.3
                 * 遍历子数据,判断是否合规
                 */
                for (DocDataInterface childData : childDataList) {
                    if (!auditData(childData, documentDictionaryList1, processVariableList)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * 审查接口表数据是否与Document Dictionary和Process Variable匹配,检查是否合规
     *
     * @param docDataInterface
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean auditData(DocDataInterface docDataInterface, List<DocumentDictionary> documentDictionaryList, List<ProcessVariable> processVariableList) {
        /**
         * Step 1
         * 判断接口数据是否满足Document Doctionary的定义
         */
        for (DocumentDictionary documentDictionary : documentDictionaryList) {
            /**
             * Step 1.1
             * 通过反射获取接口表某个字段的值,并判断如果Doc Dict定义的为非空,则需判断接口表对应的字段是否为空
             */
            if ("Y".equals(documentDictionary.getRequiredNotNull()) && "Y".equals(documentDictionary.getIsActive())) {
                String documentDataColumn = documentDictionary.getDocumentDataColumn();
                try {

                    String dataColumnValue = (String) FieldUtils.readField(docDataInterface, documentDataColumn, true);
                    if (StringUtils.isBlank(dataColumnValue)) {
                        docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                                "Column '" + documentDataColumn + "' cannot be empty");
                        return false;
                    }

                } catch (Exception e) {
                    //TODO
                }
            }
        }

        /**
         * Step 2
         * 判断接口表中字段的值是否能匹配参考流程变量定义
         */
        for (ProcessVariable processVariable : processVariableList) {
            if ("R".equals(processVariable.getSourceType()) &&
                    processVariable.getDocumentDictionary().getDocumentDataLevel().getDataLevel() == 0) {

                String documentDataColumn = processVariable.getDocumentDictionary().getDocumentDataColumn();
                String variableDataType = processVariable.getDataType();
                String dataColumnValue = null;

                try {
                    dataColumnValue = (String) FieldUtils.readField(docDataInterface, documentDataColumn, true);
                } catch (IllegalAccessException e) {
                    docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                            "IllegalAccessException when getting column " + documentDataColumn);
                    return false;
                } catch (Exception e) {
                    docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                            "Exception when getting column " + documentDataColumn);
                    return false;
                }
                /**
                 * Step 2.1
                 * 判断变量是否可以为空
                 */
                if ("N".equals(processVariable.getNullable())) {
                    if (StringUtils.isBlank(dataColumnValue)) {
                        docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                                "Value '" + documentDataColumn + "' cannot be empty");
                        return false;
                    }
                }
                /**
                 * Step 2.2
                 * 将String转成流程变量指定的数据类型
                 */
                switch (variableDataType) {
                    case "LONG": {
                        try {
                            Long variableLong = Long.parseLong(dataColumnValue);
                            //processInstanceVariableMap.put(processVariable.getVariableName(),variableLong);
                        } catch (NumberFormatException e) {
                            docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                                    "NumberFormatException when converting Column " + documentDataColumn + "to Long type");
                            return false;
                        } catch (Exception e) {
                            docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                                    "Exception when converting Column " + documentDataColumn + "to Long type");
                            return false;
                        }
                        break;
                    }
                    case "DOUBLE": {
                        try {
                            Double variableDouble = Double.parseDouble(dataColumnValue);
                            //processInstanceVariableMap.put(processVariable.getVariableName(),variableDouble);
                        } catch (NumberFormatException e) {
                            docDataImpExceptionService.newDataImpException(docDataInterface.getId(),
                                    "Exception when converting Column " + documentDataColumn + "to Double type");
                            return false;
                        }
                        break;
                    }
                    case "STRING": {
                        //processInstanceVariableMap.put(processVariable.getVariableName(),dataColumnValue);
                        break;
                    }
                    default: {
                        //processInstanceVariableMap.put(processVariable.getVariableName(),dataColumnValue);
                        break;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 当验证成功时，复制数据到目标表,并删除接口表数据,并返回dataLevel为0的Document Data对象
     *
     * @param docDataInterface0
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DocumentData copyInterfaceToDestination(DocDataInterface docDataInterface0) throws Exception {
        DocumentData documentData0 = copyValueFromInterface(docDataInterface0);
        documentDataService.add(documentData0);
        List<DocDataInterface> childDocDataIFaceList = docDataInterfaceService.getChildData(docDataInterface0);
        for (DocDataInterface docDataInterface : childDocDataIFaceList) {
            DocumentData documentData1 = copyValueFromInterface(docDataInterface);
            documentData1.setParentDocumentData(documentData0);
            documentDataService.add(documentData1);
            docDataInterfaceService.delete(docDataInterface);
        }
        docDataInterfaceService.delete(docDataInterface0);
        return documentData0;
    }

    /**
     * 复制接口表对象数据到Document Data对象
     *
     * @param docDataInterface
     * @return
     * @throws IllegalAccessException
     */
    public DocumentData copyValueFromInterface(DocDataInterface docDataInterface) throws IllegalAccessException {
        DocumentData documentData = new DocumentData();
        documentData.setDataLevel(docDataInterface.getDataLevel());
        documentData.setSourceFKId(docDataInterface.getSourceFKId());
        documentData.setSourcePKId(docDataInterface.getSourcePKId());
        for (int i = 1; i < DATA_COLUMN_COUNT; i++) {
            String fieldValue = (String) FieldUtils.readField(docDataInterface, "data" + i, true);
            FieldUtils.writeField(documentData, "data" + i, fieldValue, true);
        }
        return documentData;
    }

    /**
     * 获取流程变量集合,用于启动流程实例
     *
     * @param docDataInterface0
     * @return
     * @throws IllegalAccessException
     */
    public Map<String, Object> getProcessVariables(DocDataInterface docDataInterface0) throws IllegalAccessException {
        Map<String, Object> variableMap = new HashMap<>();

        Document document = docDataInterface0.getDocument();
        List<ProcessVariable> processVariableList = documentProcessService.getCurrentProcess(document.getId()).getProcessVariableList();
        for (ProcessVariable processVariable : processVariableList) {
            if ("Y".equals(processVariable.getIsActive())) {
                String mapKey = processVariable.getVariableName();
                String columnValue;
                if ("R".equals(processVariable.getSourceType()) && processVariable.getDocumentDictionary().getDocumentDataLevel().getDataLevel() == 0) {
                    String columnName = processVariable.getDocumentDictionary().getDocumentDataColumn();
                    columnValue = (String) FieldUtils.readField(docDataInterface0, columnName, true);
                } else {
                    columnValue = processVariable.getValue();
                }

                switch (processVariable.getDataType()) {
                    case "LONG": {
                        variableMap.put(mapKey, Long.parseLong(columnValue));
                        break;
                    }
                    case "DOUBLE": {
                        variableMap.put(mapKey, Double.parseDouble(columnValue));
                        break;
                    }
                    case "STRING": {
                        variableMap.put(mapKey, columnValue);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
        return variableMap;
    }
}
