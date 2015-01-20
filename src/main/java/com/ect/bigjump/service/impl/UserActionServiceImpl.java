package com.ect.bigjump.service.impl;

import com.ect.bigjump.domain.DocAssigneeCandidates;
import com.ect.bigjump.domain.DocumentMaster;
import com.ect.bigjump.domain.DocumentReply;
import com.ect.bigjump.domain.User;
import com.ect.bigjump.service.*;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户签核行为Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-18
 */
@Service("userActionService")
public class UserActionServiceImpl implements UserActionService {

    private static final String REJECTED_VARIABLE_NAME = "rejectedFlag";

    private static final String LAST_ACTION_USER_VARIABLE_NAME = "lastApproval";

    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "runtimeService")
    private RuntimeService runtimeService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "documentMasterService")
    private DocumentMasterService documentMasterService;

    @Resource(name = "documentReplyService")
    private DocumentReplyService documentReplyService;

    @Resource(name = "docAssigneeCandidatesService")
    private DocAssigneeCandidatesService docAssigneeCandidatesService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void completeTask(Task task, String action, String comment) throws Exception {
        Map<String, Object> variables = new HashMap<>();
        String currentActionUserId = task.getAssignee();
        /**
         * 将当前Action User设置成流程变量,以便下面的任务参考
         */
        variables.put(LAST_ACTION_USER_VARIABLE_NAME, currentActionUserId);

        User currentUser = userService.get(Long.parseLong(currentActionUserId));
        String processInstanceId = task.getProcessInstanceId();
        //ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().
        DocumentMaster documentMaster = documentMasterService.getByProcessInstanceId(processInstanceId);
        Integer nextSequence = documentReplyService.getMaxSeqForDocMaster(documentMaster.getId()) + 1;
        /**
         * 插入签核记录
         */
        DocumentReply documentReply = new DocumentReply();
        documentReply.setActionType("STANDARD");
        documentReply.setActionValue(action);
        documentReply.setCompletedDate(new Date());
        documentReply.setReceivedDate(task.getCreateTime());
        documentReply.setNodeName(task.getName());
        documentReply.setUser(currentUser);
        documentReply.setSequence(nextSequence);
        documentReply.setDocumentMaster(documentMaster);
        documentReply.setComment(comment);
        documentReplyService.add(documentReply);

        /**
         * 删除Document Assignee Candicates记录
         */
        docAssigneeCandidatesService.deleteByTaskId(task.getId(), documentMaster.getId());

        if ("APPROVE".equals(action)) {
            taskService.complete(task.getId(), variables);
            /**
             * 更新Document Master状态
             */
            documentMaster.setLastApprovalDate(new Date());
            if (checkProcessInstanceEnded(processInstanceId)) {
                documentMaster.setApprovalStatus("P");
                documentMaster.setProcessCode("F");
                documentMaster.setLastApprovalDate(new Date());
                documentMasterService.update(documentMaster);
                //删除当前所有Document Assignee Candicates记录

            } else {
                /**
                 * 获取下一个任务节点名称
                 */
                List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
                if (!taskList.isEmpty()) {
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
                            docAssigneeCandidatesService.add(docAssigneeCandidates);
                        } else if (assigneeArray.length > 1) {
                            for (int j = 0; j < assigneeArray.length; j++) {
                                DocAssigneeCandidates docAssigneeCandidates = new DocAssigneeCandidates();
                                docAssigneeCandidates.setDelegateType("C");
                                docAssigneeCandidates.setDocumentMaster(documentMaster);
                                docAssigneeCandidates.setNodeName(taskList.get(i).getName());
                                docAssigneeCandidates.setTaskId(taskList.get(i).getId());
                                docAssigneeCandidates.setUser(userService.get(Long.parseLong(assigneeArray[j])));
                                docAssigneeCandidatesService.add(docAssigneeCandidates);
                            }
                        }
                    }
                }
                documentMaster.setLastApprovalDate(new Date());
                documentMaster.setApprovalStatus("A");
                documentMaster.setProcessCode("A");
                documentMasterService.update(documentMaster);
                /**
                 * 判断新产生的任务委托人是否跟当前Action User一样，如果一样，则自动Approve
                 */
                if (!taskList.isEmpty()) {
                    for (Task nextTask : taskList) {
                        /**
                         * 当任务的Assignee与当前相同时,自动Approve
                         */
                        if (currentActionUserId.equals(nextTask.getAssignee())) {
                            completeTask(nextTask, "APPROVE", null);
                        }
                    }
                }
            }

        } else if ("REJECT".equals(action)) {
            variables.put(REJECTED_VARIABLE_NAME, "Y");
            taskService.complete(task.getId(), variables);
            if (checkProcessInstanceEnded(processInstanceId)) {
                documentMaster.setProcessCode("F");
                documentMaster.setApprovalStatus("R");
                documentMaster.setLastApprovalDate(new Date());
            }
            documentMasterService.update(documentMaster);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void approve(String taskId, String comment) throws Exception {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        completeTask(task, "APPROVE", comment);
    }

    @Override
    public void reject(String taskId, String comment) throws Exception {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        completeTask(task, "REJECT", comment);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void claim(String taskId, Long userId) throws Exception {
        taskService.setAssignee(taskId, userId.toString());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void claimAndApproval(String taskId, Long userId, String comment) throws Exception {
        claim(taskId, userId);
        approve(taskId, comment);
    }

    @Override
    public void claimAndReject(String taskId,Long userId, String comment) throws Exception {
        claim(taskId, userId);
        reject(taskId,comment);
    }

    @Override
    public void claimAndForward(String taskId, String comment) throws Exception {

    }

    @Override
    public void forward(String taskId, String comment) throws Exception {

    }

    /**
     * 判断流程实例是否结束
     *
     * @param processInstanceId
     * @return
     */
    public boolean checkProcessInstanceEnded(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            return true;
        }
        return false;
    }
}
