package com.ect.bigjump.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 实体类基础抽象类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-12
 */
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * 主键id,自增长生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * 行数据生成时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    protected Date creationDate;

    /**
     * 行数据创建者
     */
    @Column(length = 32, updatable = false)
    protected String createdBy;

    /**
     * 最后一次更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdateDate;

    /**
     * 最后一次更新者
     */
    @Column(length = 32)
    protected String lastUpdatedBy;

    /**
     * 其他行属性attribute1~15,扩展应用
     */
    @Column(length = 255)
    protected String attribute1;

    @Column(length = 255)
    protected String attribute2;

    @Column(length = 255)
    protected String attribute3;

    @Column(length = 255)
    protected String attribute4;

    @Column(length = 255)
    protected String attribute5;

    @Column(length = 255)
    protected String attribute6;

    @Column(length = 255)
    protected String attribute7;

    @Column(length = 255)
    protected String attribute8;

    @Column(length = 255)
    protected String attribute9;

    @Column(length = 255)
    protected String attribute10;

    @Column(length = 255)
    protected String attribute11;

    @Column(length = 255)
    protected String attribute12;

    @Column(length = 255)
    protected String attribute13;

    @Column(length = 255)
    protected String attribute14;

    @Column(length = 255)
    protected String attribute15;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute11() {
        return attribute11;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    public String getAttribute12() {
        return attribute12;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    public String getAttribute15() {
        return attribute15;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }


}
