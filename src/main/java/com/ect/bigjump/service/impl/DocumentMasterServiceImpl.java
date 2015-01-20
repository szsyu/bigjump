package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocumentMasterDao;
import com.ect.bigjump.domain.DocumentMaster;
import com.ect.bigjump.service.DocumentMasterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Document Master Service类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Service("documentMasterService")
public class DocumentMasterServiceImpl extends BaseServiceImpl<DocumentMaster, Long> implements DocumentMasterService {

    @Resource(name = "documentMasterDao")
    private DocumentMasterDao documentMasterDao;

    @Override
    public BaseDao<DocumentMaster, Long> getDao() {
        return documentMasterDao;
    }

    @Override
    public DocumentMaster getByProcessInstanceId(String processIntanceId) {
        String hql = "from DocumentMaster where processInstanceId = ?";
        Object[] params = {processIntanceId};
        List<DocumentMaster> documentMasterList = documentMasterDao.findByHql(hql, params);
        if (!documentMasterList.isEmpty()) {
            return documentMasterList.get(0);
        } else {
            return null;
        }
    }

}
