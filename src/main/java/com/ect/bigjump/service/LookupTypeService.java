package com.ect.bigjump.service;

import com.ect.bigjump.domain.LookupType;
import com.ect.bigjump.domain.Page;

/**
 * Lookup Type Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-09
 */
public interface LookupTypeService extends BaseService<LookupType, Long> {

    /**
     * Lookup Type分页查询
     * @param currentPage
     * @param lookupTypeCodeLike
     * @return
     */
    Page<LookupType> queryForPage(int currentPage,String lookupTypeCodeLike);

    /**
     * 根据Lookup Code查询出唯一对象
     * @param lookupTypeCode
     * @return
     */
    LookupType getByCode(String lookupTypeCode);
}
