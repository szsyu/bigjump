package com.ect.bigjump.service;

import com.ect.bigjump.domain.DocAssigneeCandidates;
import com.ect.bigjump.domain.Page;

import java.util.List;

/**
 * Document Assignee Or Candidates Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-20
 */
public interface DocAssigneeCandidatesService extends BaseService<DocAssigneeCandidates, Long> {

    /**
     * 删除taskId对应的记录
     *
     * @param taskId
     * @param documentMasterId
     */
    void deleteByTaskId(String taskId, Long documentMasterId) throws Exception;

    /**
     * 删除documentMaster对应的记录
     *
     * @param documentMasterId
     * @throws Exception
     */
    void deleteByDocMasterId(Long documentMasterId) throws Exception;

    /**
     * 根据User获取当前所有任务
     *
     * @param userId
     * @return
     */
    List<DocAssigneeCandidates> getAllTaskByUser(Long userId);

    /**
     * 分页查询
     *
     * @param currentPage
     * @param userId
     * @param documentNumberLike
     * @param documentCode
     * @return
     */
    Page<DocAssigneeCandidates> queryForPage(int currentPage, Long userId, String documentNumberLike, String documentCode);
}
