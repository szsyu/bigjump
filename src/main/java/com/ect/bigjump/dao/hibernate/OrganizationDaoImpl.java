package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.OrganizationDao;
import com.ect.bigjump.domain.Organization;
import org.springframework.stereotype.Repository;

/**
 * Organization持久化实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
@Repository("organizationDao")
public class OrganizationDaoImpl extends BaseDaoImpl<Organization, Long> implements OrganizationDao {
}
