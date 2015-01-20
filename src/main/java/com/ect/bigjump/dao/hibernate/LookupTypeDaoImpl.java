package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.LookupTypeDao;
import com.ect.bigjump.domain.LookupType;
import org.springframework.stereotype.Repository;

/**
 * Lookup Type 持久层类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-09
 */
@Repository("lookupTypeDao")
public class LookupTypeDaoImpl extends BaseDaoImpl<LookupType, Long> implements LookupTypeDao {
}
