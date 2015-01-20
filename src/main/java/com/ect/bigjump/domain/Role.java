package com.ect.bigjump.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 角色实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-06
 */
@Entity
@Table(name = "BJ_ROLE")
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8867867626752785811L;

    /**
     * 角色代码
     */
    @Column(length = 64, updatable = false)
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(length = 64)
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否可用
     */
    @Column(length = 1)
    private String isActive;

    /**
     * 外键字段:documentCode,对应Document的唯一索引字段documentCode
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentCode", referencedColumnName = "documentCode", updatable = false)
    private Document document;

    /**
     * 拥有该角色的用户集合
     */
    @Cascade(value = {CascadeType.SAVE_UPDATE})
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
