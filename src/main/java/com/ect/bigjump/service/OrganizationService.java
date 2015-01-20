package com.ect.bigjump.service;

import com.ect.bigjump.domain.Organization;

import java.util.List;

/**
 * Organization Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
public interface OrganizationService extends BaseService<Organization, Long> {

    /**
     * 根据Code获取Organization对象
     *
     * @param orgCode
     * @return
     */
    Organization getByCode(String orgCode);


    /**
     * 获取根Organization
     *
     * @param organizationId
     * @return
     */
    Organization getRootOrganization(Long organizationId);

    /**
     * 生成组织树图
     *
     * @param organizationId
     * @return
     */
    String getOrganizationTree(Long organizationId);

    /**
     * 获取所有Master Organization List
     *
     * @return
     */
    List<Organization> getMasterOrganizationList();
}
