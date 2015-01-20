package com.ect.bigjump.service;

import org.activiti.engine.task.Task;

/**
 * 用户签核行为Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-17
 */
public interface UserActionService {

    /**
     * 完成任务
     *
     * @param task
     * @param action
     * @param comment
     * @throws Exception
     */
    void completeTask(Task task, String action, String comment) throws Exception;

    /**
     * 签核通过
     *
     * @param taskId
     * @param comment
     * @throws Exception
     */
    void approve(String taskId, String comment) throws Exception;

    /**
     * 签核拒绝
     *
     * @param taskId
     * @param comment
     * @throws Exception
     */
    void reject(String taskId, String comment) throws Exception;

    /**
     * 拾取任务到用户
     *
     * @param taskId
     * @param userId
     * @throws Exception
     */
    void claim(String taskId, Long userId) throws Exception;

    /**
     * 拾取任务到用户并签核通过
     *
     * @param taskId
     * @param userId
     * @param comment
     * @throws Exception
     */
    void claimAndApproval(String taskId, Long userId, String comment) throws Exception;

    /**
     * 拾取任务到用户并签核拒绝
     *
     * @param taskId
     * @param userId
     * @param comment
     * @throws Exception
     */
    void claimAndReject(String taskId, Long userId, String comment) throws Exception;

    /**
     * 拾取并会签
     *
     * @param taskId
     * @param comment
     * @throws Exception
     */
    void claimAndForward(String taskId, String comment) throws Exception;

    /**
     * 会签
     *
     * @param taskId
     * @param comment
     * @throws Exception
     */
    void forward(String taskId, String comment) throws Exception;

}
