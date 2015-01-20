package com.ect.bigjump.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Document流程定义实体类
 *
 * @author Shawn Yu
 * @since 2014-12-06
 * @version 1.0
 */
@Entity
@Table(name = "BJ_DOCUMENT_PROCESS")
public class DocumentProcess extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6052522577123130703L;

    /**
     * 外键字段:documentId,对应Document里的主键id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentId")
    private Document document;

    /**
     * 流程定义key,由activiti生成
     */
    private String processDefinitionKey;

    /**
     * 流程定义id,由activiti生成
     */
    private String processDefinitionId;

    /**
     * 流程定义版本号
     */
    private Integer version;

    /**
     * 流程名称
     */
    private String processName;

    /**
     * 是否为当前流程版本
     */
    @Column(length = 1)
    private String isCurrentVersion;

    /**
     * 所拥有的流程变量定义集合
     */
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "documentProcess")
    private List<ProcessVariable> processVariableList = new ArrayList<>();

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public List<ProcessVariable> getProcessVariableList() {
        return processVariableList;
    }

    public void setProcessVariableList(List<ProcessVariable> processVariableList) {
        this.processVariableList = processVariableList;
    }

    public String getIsCurrentVersion() {
        return isCurrentVersion;
    }

    public void setIsCurrentVersion(String isCurrentVersion) {
        this.isCurrentVersion = isCurrentVersion;
    }
}
