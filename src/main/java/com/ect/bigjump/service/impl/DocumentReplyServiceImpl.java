package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocumentReplyDao;
import com.ect.bigjump.domain.DocumentMaster;
import com.ect.bigjump.domain.DocumentReply;
import com.ect.bigjump.service.DocumentReplyService;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Document Reply Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-11
 */
@Service("documentReplyService")
public class DocumentReplyServiceImpl extends BaseServiceImpl<DocumentReply, Long> implements DocumentReplyService {

    @Resource(name = "documentReplyDao")
    private DocumentReplyDao documentReplyDao;

    @Override
    public BaseDao<DocumentReply, Long> getDao() {
        return documentReplyDao;
    }

    @Override
    public Integer getMaxSeqForDocMaster(Long docMasterId) {
        String sql = "select max(sequence) maxSequence from BJ_DOCUMENT_REPLY where documentMasterId = ?";
        Object[] params = {docMasterId};
        SQLQuery sqlQuery = documentReplyDao.createSQLQuery(sql, params);
        Integer maxSequence = (Integer) sqlQuery.addScalar("maxSequence", StandardBasicTypes.INTEGER).uniqueResult();
        if (maxSequence == null) {
            return 0;
        } else {
            return maxSequence;
        }
    }

    @Override
    public List<DocumentReply> getDocumentReplyListByDocumentMaster(DocumentMaster documentMaster) {
        String hql = "from DocumentReply where documentMaster = ? order by sequence";
        Object[] params = {documentMaster};
        List<DocumentReply> documentReplyList = documentReplyDao.findByHql(hql, params);
        return documentReplyList;
    }
}
