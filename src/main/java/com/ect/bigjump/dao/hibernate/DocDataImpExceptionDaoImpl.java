package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.DocDataImpExceptionDao;
import com.ect.bigjump.domain.DocDataImpException;
import org.springframework.stereotype.Repository;

/**
 * Document Data导入异常记录持久化实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Repository("docDataImpExceptionDao")
public class DocDataImpExceptionDaoImpl extends BaseDaoImpl<DocDataImpException, Long> implements DocDataImpExceptionDao {
}
