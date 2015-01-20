package com.ect.bigjump.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.ect.bigjump.dao.RoleDao;
import com.ect.bigjump.domain.Role;

/**
 * Role持久化实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {
}
