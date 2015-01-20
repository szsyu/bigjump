package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.LookupTypeDao;
import com.ect.bigjump.domain.Document;
import com.ect.bigjump.domain.LookupType;
import com.ect.bigjump.domain.Page;
import com.ect.bigjump.service.LookupTypeService;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Lookup Type Service类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-09
 */
@Service("lookupTypeService")
public class LookupTypeServiceImpl extends BaseServiceImpl<LookupType, Long> implements LookupTypeService {

    private static final String ORDER_BY = " order by lookupTypeCode asc";

    private static final int PAGE_SIZE = 10;

    @Resource(name = "lookupTypeDao")
    private LookupTypeDao lookupTypeDao;

    @Override
    public BaseDao<LookupType, Long> getDao() {
        return lookupTypeDao;
    }

    @Override
    public Page<LookupType> queryForPage(int currentPage, String lookupTypeCodeLike) {
        String sql = "select * from BJ_LOOKUP_TYPE where 1 = 1";
        SQLQuery query;
        String baseUrl;
        if (lookupTypeCodeLike != null && !"".equals(lookupTypeCodeLike)) {
            Object[] params = {lookupTypeCodeLike};
            sql = sql + " and lookupTypeCode like ?" + ORDER_BY;
            query = lookupTypeDao.createSQLQuery(sql, params);
            baseUrl = "../Lookup/showLookupTypeList?lookupTypeCodeLike=" + lookupTypeCodeLike + "&page=";
        } else {
            sql = sql + ORDER_BY;
            query = lookupTypeDao.createSQLQuery(sql);
            baseUrl = "../Lookup/showLookupTypeList?page=";
        }

        int allRow = query.list().size();
        int totalPage = Page.countTotalPage(PAGE_SIZE, allRow);
        int offset = Page.countOffset(PAGE_SIZE, currentPage);

        query.setFirstResult(offset);
        query.setMaxResults(PAGE_SIZE);
        List<LookupType> lookupTypeList = query.addEntity(LookupType.class).list();

        Page<LookupType> page = new Page<LookupType>();
        page.setBasePageUrl(baseUrl);
        page.setPageSize(PAGE_SIZE);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(lookupTypeList);
        page.init();

        return page;
    }

    @Override
    public LookupType getByCode(String lookupTypeCode) {
        String sql = "select * from BJ_LOOKUP_TYPE where lookupTypeCode = ?";
        Object[] params = {lookupTypeCode};
        SQLQuery sqlQuery = lookupTypeDao.createSQLQuery(sql,params);
        return (LookupType)sqlQuery.addEntity(LookupType.class).uniqueResult();
    }
}
