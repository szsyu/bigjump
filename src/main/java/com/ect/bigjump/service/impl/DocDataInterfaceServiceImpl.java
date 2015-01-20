package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocDataInterfaceDao;
import com.ect.bigjump.domain.DocDataInterface;
import com.ect.bigjump.service.DocDataInterfaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Document Data 数据接口Service类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Service("docDataInterfaceService")
public class DocDataInterfaceServiceImpl extends BaseServiceImpl<DocDataInterface, Long> implements DocDataInterfaceService {

    private static final String ORDER_BY = " order by documentCode";

    @Resource(name = "docDataInterfaceDao")
    private DocDataInterfaceDao docDataInterfaceDao;

    @Override
    public BaseDao<DocDataInterface, Long> getDao() {
        return docDataInterfaceDao;
    }

    @Override
    public List<DocDataInterface> getUnprocessedData(Integer dataLevel) {
        String sql = "select * from BJ_DOCUMENT_DATA_INTERFACE where dataLevel = ? and processStatus = ?" + ORDER_BY;
        Object[] params = {dataLevel, "N"};
        List<DocDataInterface> unProcessedDataList = docDataInterfaceDao.findBySql(sql, params);
        return unProcessedDataList;
    }

    @Override
    public List<DocDataInterface> getChildData(DocDataInterface parentData) {
        String sql = "select * from BJ_DOCUMENT_DATA_INTERFACE where dataLevel = ? and sourceFKId = ?";
        Object[] params = {parentData.getDataLevel() + 1, parentData.getSourcePKId()};
        List<DocDataInterface> childDataList = docDataInterfaceDao.findBySql(sql, params);
        return childDataList;
    }
}
