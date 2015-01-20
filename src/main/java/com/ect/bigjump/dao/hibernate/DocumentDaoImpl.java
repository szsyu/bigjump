package com.ect.bigjump.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.ect.bigjump.dao.DocumentDao;
import com.ect.bigjump.domain.Document;

/**
 * Document持久化实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
@Repository("documentDao")
public class DocumentDaoImpl extends BaseDaoImpl<Document, Long> implements DocumentDao {
}
