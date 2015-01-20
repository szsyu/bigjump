package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.LookupValueDao;
import com.ect.bigjump.domain.LookupType;
import com.ect.bigjump.domain.LookupValue;
import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.User;
import com.ect.bigjump.service.LookupValueService;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Lookup Value Service类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-08
 */
@Service("lookupValueService")
public class LookupValueServiceImpl extends BaseServiceImpl<LookupValue, Long> implements LookupValueService {

    private static final String ORDER_BY = " order by lookupCode asc";

    private static final int PAGE_SIZE = 10;

    @Resource(name = "lookupValueDao")
    private LookupValueDao lookupValueDao;

    @Override
    public BaseDao<LookupValue, Long> getDao() {
        return lookupValueDao;
    }

    @Override
    public User getUserByLookupCode(String lookupCode, String lookupTypeCode) {
        return null;
    }

    @Override
    public Page<LookupValue> queryForPage(int currentPage, String lookupTypeCode, String lookupCodeLike) {
        String sql = "select * from BJ_LOOKUP_VALUE where 1 = 1 and lookupTypeCode = ?";
        SQLQuery query;
        String baseUrl;
        if (lookupCodeLike != null && !"".equals(lookupCodeLike)) {
            Object[] params = {lookupTypeCode, lookupCodeLike};
            sql = sql + " and lookupCode like ?" + ORDER_BY;
            query = lookupValueDao.createSQLQuery(sql, params);
            baseUrl = "../Lookup/showLookupValueList?lookupTypeCode=" + lookupTypeCode + "&lookupCodeLike=" + lookupCodeLike + "&page=";
        } else {
            Object[] params = {lookupTypeCode};
            sql = sql + ORDER_BY;
            query = lookupValueDao.createSQLQuery(sql, params);
            baseUrl = "../Lookup/showLookupValueList?lookupTypeCode=" + lookupTypeCode + "&page=";
        }

        int allRow = query.list().size();
        int totalPage = Page.countTotalPage(PAGE_SIZE, allRow);
        int offset = Page.countOffset(PAGE_SIZE, currentPage);

        query.setFirstResult(offset);
        query.setMaxResults(PAGE_SIZE);
        List<LookupValue> lookupValueList = query.addEntity(LookupValue.class).list();

        Page<LookupValue> page = new Page<LookupValue>();
        page.setBasePageUrl(baseUrl);
        page.setPageSize(PAGE_SIZE);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(lookupValueList);
        page.init();

        return page;
    }

    @Override
    public List<String> getDataTypes() {
        return Arrays.asList(DATA_TYPES);
    }

    @Override
    public User getUserByTypeCodeAndCode(String lookupTypeCode, String lookupCode) {
        LookupValue lookupValue = getByTypeCodeAndCode(lookupTypeCode,lookupCode);
        if(lookupValue != null){
            return lookupValue.getUser();
        }
        else{
            return null;
        }
    }

    @Override
    public LookupValue getByTypeCodeAndCode(String lookupTypeCode, String lookupCode) {
        String sql = "select * from BJ_LOOKUP_VALUE where lookupTypeCode = ? and lookupCode = ? and isActive = ?";
        Object[] params = {lookupTypeCode,lookupCode,"Y"};
        List<LookupValue> lookupValueList = lookupValueDao.findBySql(sql,params);
        if(lookupValueList.isEmpty()){
            return null;
        }else{
            return lookupValueList.get(0);
        }
    }
}
