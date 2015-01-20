package com.ect.bigjump.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 组织实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
@Entity
@Table(name = "BJ_ORGANIZATION")
public class Organization extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7205749261049028196L;

    /**
     * 组织代码
     */
    @Column(length = 32, updatable = false)
    private String orgCode;

    /**
     * 组织名称
     */
    @Column(length = 64, nullable = false)
    private String orgName;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否为Master Organization
     */
    @Column(length = 1, updatable = false)
    private String isMaster;

    /**
     * 是否可用
     */
    @Column(length = 1)
    private String isActive;

    /**
     * 上级组织
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentOrganizationId")
    private Organization parentOrganization;

    /**
     * 组织负责人列表
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<OrganizationManager> managerList;

    /**
     * 下一级组织列表
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentOrganization")
    private List<Organization> childOrganizationList;

    /**
     * 组织人员列表
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<User> userList;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Organization getParentOrganization() {
        return parentOrganization;
    }

    public void setParentOrganization(Organization parentOrganization) {
        this.parentOrganization = parentOrganization;
    }

    public List<OrganizationManager> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<OrganizationManager> managerList) {
        this.managerList = managerList;
    }

    public List<Organization> getChildOrganizationList() {
        return childOrganizationList;
    }

    public void setChildOrganizationList(List<Organization> childOrganizationList) {
        this.childOrganizationList = childOrganizationList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(String isMaster) {
        this.isMaster = isMaster;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public List<OrganizationManager> getActiveOrgManager() {
        if (managerList.isEmpty()) {
            return null;
        } else {
            List<OrganizationManager> activeOrgManagerList = new ArrayList<>();
            for (OrganizationManager organizationManager : managerList) {
                if ("Y".equals(organizationManager.getManager().getIsActive()) && "Y".equals(organizationManager.getIsActive())) {
                    activeOrgManagerList.add(organizationManager);
                }
            }
            return activeOrgManagerList;
        }
    }
}
