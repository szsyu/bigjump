package com.ect.bigjump.activiti.service;

import org.activiti.engine.delegate.DelegateTask;

/**
 * 任务委派 Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
public interface DelegateService {

    /**
     * 根据Lookup匹配出委派任务接受者
     *
     * @param task
     * @param lookupTypeCode
     * @param lookupCode
     * @throws Exception
     */
    public void delegateByLookup(DelegateTask task, String lookupTypeCode, String lookupCode) throws Exception;

    /**
     * 根据角色获取委派任务接受者
     *
     * @param task
     * @param roleCode
     * @throws Exception
     */
    public void delegateByRole(DelegateTask task, String roleCode) throws Exception;

    /**
     * 根据用户直接获取委派任务接受者
     *
     * @param task
     * @param userName
     * @throws Exception
     */
    public void delegateByUser(DelegateTask task, String userName) throws Exception;

    /**
     * 将组织负责人赋予委派任务接受者
     *
     * @param task
     * @param orgCode
     * @throws Exception
     */
    public void delegateToOrgManager(DelegateTask task, String orgCode) throws Exception;

    /**
     * 获取用户所在组织的负责人,并赋予委派任务接受者
     *
     * @param task
     * @param userId
     * @throws Exception
     */
    public void delegateToUserManager(DelegateTask task, String userId) throws Exception;
}
