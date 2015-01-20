package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.OrganizationManagerDao;
import com.ect.bigjump.domain.OrganizationManager;
import com.ect.bigjump.service.OrganizationManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Organization Manager Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
@Service("organizationManagerService")
public class OrganizationManagerServiceImpl extends BaseServiceImpl<OrganizationManager, Long> implements OrganizationManagerService {

    @Resource(name = "organizationManagerDao")
    private OrganizationManagerDao organizationManagerDao;

    @Override
    public BaseDao<OrganizationManager, Long> getDao() {
        return organizationManagerDao;
    }
}
