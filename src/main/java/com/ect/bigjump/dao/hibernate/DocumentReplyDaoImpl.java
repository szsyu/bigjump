package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.DocumentReplyDao;
import com.ect.bigjump.domain.DocumentReply;
import org.springframework.stereotype.Repository;

/**
 * Document Reply持久层实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Repository("documentReplyDao")
public class DocumentReplyDaoImpl extends BaseDaoImpl<DocumentReply,Long> implements DocumentReplyDao {
}
