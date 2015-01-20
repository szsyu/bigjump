package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocDataImpExceptionDao;
import com.ect.bigjump.domain.DocDataImpException;
import com.ect.bigjump.service.DocDataImpExceptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Document Data导入异常记录Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Service("docDataImpExceptionService")
public class DocDataImpExceptionServiceImpl extends BaseServiceImpl<DocDataImpException, Long> implements DocDataImpExceptionService {

    @Resource(name = "docDataImpExceptionDao")
    private DocDataImpExceptionDao docDataImpExceptionDao;

    @Override
    public BaseDao<DocDataImpException, Long> getDao() {
        return docDataImpExceptionDao;
    }

    @Override
    public void newDataImpException(Long docDataIfaceId, String errorMessage) {
        DocDataImpException docDataImpException = new DocDataImpException(docDataIfaceId, errorMessage);
        try {
            add(docDataImpException);
        } catch (Exception e) {
            //todo section
        }
    }
}
