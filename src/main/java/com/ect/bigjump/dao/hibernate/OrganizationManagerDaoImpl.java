package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.OrganizationManagerDao;
import com.ect.bigjump.domain.OrganizationManager;
import org.springframework.stereotype.Repository;

/**
 * Organization Manager持久化实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
@Repository("organizationManagerDao")
public class OrganizationManagerDaoImpl extends BaseDaoImpl<OrganizationManager, Long> implements OrganizationManagerDao {
}
