package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.DocumentDataLevelDao;
import com.ect.bigjump.domain.DocumentDataLevel;
import org.springframework.stereotype.Repository;

/**
 * DocumentDataLevel持久层类
 *
 * @author Shawn Yu
 * @since 2014-12-04
 * @version 1.0
 */
@Repository("documentDataLevelDao")
public class DocumentDataLevelDaoImpl extends BaseDaoImpl<DocumentDataLevel,Long> implements DocumentDataLevelDao{
}
