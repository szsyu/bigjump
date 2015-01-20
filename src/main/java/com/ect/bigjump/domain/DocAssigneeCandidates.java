package com.ect.bigjump.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Document 当前委托人或者候选人实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-20
 */
@Entity
@Table(name = "BJ_DOC_ASS_CAN")
public class DocAssigneeCandidates extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -6858216392361806066L;

    /**
     * 外键字段:documentMasterId,对应Document Master的id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentMasterId")
    private DocumentMaster documentMaster;

    /**
     * 委派类型,A为Assignee,C为Candidate
     */
    @Column(length = 1,nullable = false)
    private String delegateType;

    /**
     * 任务ID
     */
    @Column(length = 64,nullable = false)
    private String taskId;

    /**
     * 任务节点名称
     */
    private String nodeName;

    /**
     * 外键字段:userId,对应User的主键id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public DocumentMaster getDocumentMaster() {
        return documentMaster;
    }

    public void setDocumentMaster(DocumentMaster documentMaster) {
        this.documentMaster = documentMaster;
    }

    public String getDelegateType() {
        return delegateType;
    }

    public void setDelegateType(String delegateType) {
        this.delegateType = delegateType;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
