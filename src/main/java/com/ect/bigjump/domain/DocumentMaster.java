package com.ect.bigjump.domain;


import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Document 主数据实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Entity
@Table(name = "BJ_DOCUMENT_MASTER")
public class DocumentMaster extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -876426614440016559L;

    /**
     * 外键字段:Document Code,对应Document里的documentCode字段
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentCode", referencedColumnName = "documentCode")
    private Document document;

    /**
     * 外键字段:Document Data Id,对应Document Data里的主键id
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentDataId")
    private DocumentData documentData;

    /**
     * Document Number
     */
    @Column(length = 64, unique = true)
    private String documentNumber;

    /**
     * 描述
     */
    private String description;

    /**
     * 流程实例ID,由Activiti生成
     */
    @Column(length = 64, unique = true)
    private String processInstanceId;

    /**
     * 流程代码：
     * A为Approving,表示正在签核中
     * F为Approval Finished,表示已经完成
     */
    @Column(length = 1)
    private String processCode;

    /**
     * 流程代码：
     * A为Approving,表示正在签核中
     * R为Rejected,表示被拒绝
     * P为Approval Finished,表示已经完成
     */
    @Column(length = 1)
    private String approvalStatus;

    /**
     * 最后一次签核的完成时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastApprovalDate;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getLastApprovalDate() {
        return lastApprovalDate;
    }

    public void setLastApprovalDate(Date lastApprovalDate) {
        this.lastApprovalDate = lastApprovalDate;
    }

    public DocumentData getDocumentData() {
        return documentData;
    }

    public void setDocumentData(DocumentData documentData) {
        this.documentData = documentData;
    }

}
