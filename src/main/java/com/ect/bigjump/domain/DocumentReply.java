package com.ect.bigjump.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Document签核结果实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Entity
@Table(name = "BJ_DOCUMENT_REPLY")
public class DocumentReply extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 7629984818025848279L;

    /**
     * 外键字段:documentMasterId,对应Document Master的主键id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentMasterId")
    private DocumentMaster documentMaster;

    /**
     * 反馈次序
     */
    private Integer sequence;

    /**
     * 操作用户,对应User的主键id
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    /**
     * 操作角色,对应Role的主键id
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role role;

    /**
     * 操作类型
     */
    @Column(length = 20)
    private String actionType;

    /**
     * 操作值
     */
    @Column(length = 20)
    private String actionValue;

    /**
     * 节点名称
     */
    @Column(length = 64)
    private String nodeName;

    /**
     * 任务接受时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedDate;

    /**
     * 任务完成时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedDate;

    /**
     * 反馈附带的内容
     */
    @Column(length = 1000)
    private String comment;

    public DocumentMaster getDocumentMaster() {
        return documentMaster;
    }

    public void setDocumentMaster(DocumentMaster documentMaster) {
        this.documentMaster = documentMaster;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
