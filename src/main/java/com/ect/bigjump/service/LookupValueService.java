package com.ect.bigjump.service;

import com.ect.bigjump.domain.LookupValue;
import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.User;

import java.util.List;

/**
 * Lookup Value Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-09
 */
public interface LookupValueService extends BaseService<LookupValue, Long> {

    /**
     * 查询LookupCode和LookupType下指定的代理人
     *
     * @param lookupCode
     * @param lookupTypeCode
     * @return
     */
    User getUserByLookupCode(String lookupCode, String lookupTypeCode);

    /**
     * 分页查询
     * @param currentPage
     * @param lookupTypeCode
     * @param lookupCodeLike
     * @return
     */
    Page<LookupValue> queryForPage(int currentPage,String lookupTypeCode,String lookupCodeLike);

    /**
     * 获取Data Type
     * @return
     */
    List<String> getDataTypes();

    /**
     * 获取指定Type Code和 Lookup Code下的委托用户，用于Activiti获取Assignee的Lookup方式
     * @param lookupTypeCode
     * @param lookupCode
     * @return
     */
    User getUserByTypeCodeAndCode(String lookupTypeCode,String lookupCode);

    /**
     * 获取指定Type Code和 Lookup Code下的Lookup Value对象
     * @param lookupTypeCode
     * @param lookupCode
     * @return
     */
    LookupValue getByTypeCodeAndCode(String lookupTypeCode, String lookupCode);
}
