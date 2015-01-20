package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.DocumentProcessDao;
import com.ect.bigjump.domain.DocumentProcess;
import org.springframework.stereotype.Repository;

/**
 * Document Process持久化类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-06
 */
@Repository("documentProcessDao")
public class DocumentProcessDaoImpl extends BaseDaoImpl<DocumentProcess, Long> implements DocumentProcessDao {
}
