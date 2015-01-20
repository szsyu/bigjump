package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.LookupValueDao;
import com.ect.bigjump.domain.LookupValue;
import org.springframework.stereotype.Repository;

/**
 * Lookup Value持久化实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
@Repository("lookupValueDao")
public class LookupValueDaoImpl extends BaseDaoImpl<LookupValue, Long> implements LookupValueDao {

}
