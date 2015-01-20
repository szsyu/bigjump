package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.OrganizationDao;
import com.ect.bigjump.domain.Organization;
import com.ect.bigjump.service.OrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Organization Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
@Service("organizationService")
public class OrganizationServiceImpl extends BaseServiceImpl<Organization, Long> implements OrganizationService {

    @Resource(name = "organizationDao")
    private OrganizationDao organizationDao;

    @Override
    public BaseDao<Organization, Long> getDao() {
        return organizationDao;
    }

    @Override
    public Organization getByCode(String orgCode) {
        String hql = "from Organization where orgCode = ?";
        Object[] params = {orgCode};
        List<Organization> organizationList = organizationDao.findByHql(hql, params);
        if (organizationList.isEmpty()) {
            return null;
        } else {
            return organizationList.get(0);
        }
    }

    @Override
    public Organization getRootOrganization(Long organizationId) {
        return organizationDao.get(organizationId);
    }

    @Override
    @Transactional
    public String getOrganizationTree(Long organizationId) {
        Organization organization = organizationDao.get(organizationId);
        Long masterOrganizationId = organization.getId();
        String treeStr = "<ul>" + getChildTree(organization, masterOrganizationId) + "</ul>";
        return treeStr;
    }

    @Override
    public List<Organization> getMasterOrganizationList() {
        String hql = "from Organization where isMaster = ?";
        Object[] params = {"Y"};
        return organizationDao.findByHql(hql, params);
    }

    public String getChildTree(Organization organization, Long masterOrganizationId) {

        String treeStr = "<li>"
                + "<span><i class=\"icon-minus-sign\"></i> " + organization.getOrgName() + "</span>" + "&nbsp"
                + "<button class=\"btn btn-xs btn-success\" onclick=\"addChildOrganization(" + organization.getId() + "," + masterOrganizationId + ")\">\n"
                + "<i class=\"fa fa-plus-square fa-fw\"></i> Add Child</button>" + "&nbsp"
                + "<button class=\"btn btn-xs btn-primary\" onclick=\"editOrganization(" + organization.getId() + "," + masterOrganizationId + ")\">\n"
                + "<i class=\"fa fa-edit fa-fw\"></i> Edit</button>" + "&nbsp"
                + "<button class=\"btn btn-xs btn-danger\" onclick=\"deleteOrganization(" + organization.getId() + "," + masterOrganizationId + ")\">\n"
                + "<i class=\"fa fa-trash-o fa-fw\"></i> Delete</button>";
        if (organization.getChildOrganizationList().size() > 0) {
            treeStr = treeStr + "<ul>";
            for (Organization organization1 : organization.getChildOrganizationList()) {
                treeStr = treeStr + getChildTree(organization1, masterOrganizationId);
            }
            treeStr = treeStr + "</ul>";
        }
        treeStr = treeStr + "</li>";
        return treeStr;
    }
}
