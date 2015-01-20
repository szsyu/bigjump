package com.ect.bigjump.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Document实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-12
 */
@Entity
@Table(name = "BJ_DOCUMENT")
public class Document extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6359851661133595010L;

    /**
     * Document Code,唯一,不可更改
     */
    @Column(unique = true, length = 64, updatable = false)
    private String documentCode;

    /**
     * Document Name,不可空
     */
    @Column(length = 128, nullable = false)
    private String documentName;

    /**
     * 描述
     */
    @Column(length = 255)
    private String description;

    /**
     * 开始日期
     */
    @Temporal(TemporalType.DATE)
    private Date startDate;

    /**
     * 结束日期
     */
    @Temporal(TemporalType.DATE)
    private Date endDate;

    /**
     * 是否可用
     */
    @Column(length = 1)
    private String isActive;

    /**
     * Document所拥有的Data Level
     */
    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    private List<DocumentDataLevel> documentDataLevelList = new ArrayList<>();

    /**
     * Document下的流程定义
     */
    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    private List<DocumentProcess> documentProcessList = new ArrayList<>();

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public List<DocumentDataLevel> getDocumentDataLevelList() {
        return documentDataLevelList;
    }

    public void setDocumentDataLevelList(List<DocumentDataLevel> documentDataLevelList) {
        this.documentDataLevelList = documentDataLevelList;
    }

    public List<DocumentProcess> getDocumentProcessList() {
        return documentProcessList;
    }

    public void setDocumentProcessList(List<DocumentProcess> documentProcessList) {
        this.documentProcessList = documentProcessList;
    }
}
