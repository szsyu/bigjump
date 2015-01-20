package com.ect.bigjump.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 流程变量定义实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-06
 */
@Entity
@Table(name = "BJ_PROCESS_VARIABLE")
public class ProcessVariable extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentProcessId")
    private DocumentProcess documentProcess;

    /**
     * 变量名称
     */
    @Column(nullable = false)
    private String variableName;

    /**
     * 描述
     */
    private String description;

    /**
     * 数据类型
     */
    @Column(length = 20, nullable = false)
    private String dataType;

    /**
     * 数据源类型,R为参考Document Data Column,D为直接给值(固定值)
     */
    @Column(length = 1,nullable = false)
    private String sourceType;

    /**
     * 当数据源类型为R,对应参考的Document Data Column
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentDictionaryId")
    private DocumentDictionary documentDictionary;

    /**
     * 当数据源类型为D,固定值
     */
    private String value;

    /**
     * 是否可以为空
     */
    private String nullable;

    private String isActive;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DocumentProcess getDocumentProcess() {
        return documentProcess;
    }

    public void setDocumentProcess(DocumentProcess documentProcess) {
        this.documentProcess = documentProcess;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public DocumentDictionary getDocumentDictionary() {
        return documentDictionary;
    }

    public void setDocumentDictionary(DocumentDictionary documentDictionary) {
        this.documentDictionary = documentDictionary;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }
}
