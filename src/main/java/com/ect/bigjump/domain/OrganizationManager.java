package com.ect.bigjump.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 组织负责人实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
@Entity
@Table(name = "BJ_ORG_MANAGER")
public class OrganizationManager extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8304582278867237466L;

    /**
     * 外键字段:organizationId,对应organization的主键id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizationId")
    private Organization organization;

    /**
     * 组织负责人,外键字段:userId,对应User的主键id
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User manager;

    /**
     * 是否可用
     */
    @Column(length = 1)
    private String isActive;

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
