package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.DocumentMasterDao;
import com.ect.bigjump.domain.DocumentMaster;
import org.springframework.stereotype.Repository;

/**
 * Document Master持久层类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Repository("documentMasterDao")
public class DocumentMasterDaoImpl extends BaseDaoImpl<DocumentMaster, Long> implements DocumentMasterDao {
}
