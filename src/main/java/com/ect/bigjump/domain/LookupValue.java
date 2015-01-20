package com.ect.bigjump.domain;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Lookup Value实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-01-19
 */
@Entity
@Table(name = "BJ_LOOKUP_VALUE", uniqueConstraints = {@UniqueConstraint(columnNames = {"lookupTypeCode", "lookupCode"})})
public class LookupValue extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7392338904730879060L;

    /**
     * 外键字段:lookupTypeCode,对应Lookup Type的唯一字段lookupTypeCode
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lookupTypeCode", referencedColumnName = "lookupTypeCode")
    private LookupType lookupType;

    /**
     * Lookup Code,与lookupTypeCode组合唯一
     */
    @Column(length = 128, updatable = false)
    private String lookupCode;

    /**
     * Lookup value
     */
    private String value;

    /**
     * 外键字典:userId,对应User的主键id
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否有效
     */
    @Column(length = 1)
    private String isActive;

    public LookupType getLookupType() {
        return lookupType;
    }

    public void setLookupType(LookupType lookupType) {
        this.lookupType = lookupType;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
