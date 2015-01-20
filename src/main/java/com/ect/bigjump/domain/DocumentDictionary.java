package com.ect.bigjump.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Document Dictionary实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-01-19
 */
@Entity
@Table(name = "BJ_DOCUMENT_DICT")
public class DocumentDictionary extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8328604873424648148L;

    /**
     * Document
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "documentCode", referencedColumnName = "documentCode", updatable = false)
    private Document document;

    /**
     * Document Data Level
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "documentDataLevelId", updatable = false)
    private DocumentDataLevel documentDataLevel;

    /**
     * Document Data Column 在Document Data里面对应的字段名称
     */
    @Column(length = 10, nullable = false, updatable = false)
    private String documentDataColumn;

    /**
     * 字段名称
     */
    @Column(length = 64)
    private String columnName;

    /**
     * 字段显示标签
     */
    @Column(length = 64)
    private String columnLabel;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否可见
     */
    @Column(length = 1)
    private String visible;

    /**
     * 是否非空
     */
    @Column(length = 1)
    private String requiredNotNull;

    /**
     * 是否可用
     */
    @Column(length = 1)
    private String isActive;

    public String getDocumentDataColumn() {
        return documentDataColumn;
    }

    public void setDocumentDataColumn(String documentDataColumn) {
        this.documentDataColumn = documentDataColumn;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public DocumentDataLevel getDocumentDataLevel() {
        return documentDataLevel;
    }

    public void setDocumentDataLevel(DocumentDataLevel documentDataLevel) {
        this.documentDataLevel = documentDataLevel;
    }

    public String getRequiredNotNull() {
        return requiredNotNull;
    }

    public void setRequiredNotNull(String requiredNotNull) {
        this.requiredNotNull = requiredNotNull;
    }
}
