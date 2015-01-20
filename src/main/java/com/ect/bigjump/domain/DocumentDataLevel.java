package com.ect.bigjump.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Document Data Level实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Entity
@Table(name = "BJ_DOCUMENT_DATA_LEVEL")
public class DocumentDataLevel extends BaseEntity implements Serializable, Comparable<DocumentDataLevel> {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "documentId", updatable = false)
    private Document document;

    /**
     * 数据层次,从0开始,为最高层
     */
    @Column(nullable = false, updatable = false)
    private Integer dataLevel;

    /**
     * 数据层名称
     */
    @Column(length = 64)
    private String levelName;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否有效
     */
    private String isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentDataLevel")
    private List<DocumentDictionary> documentDictionaryList = new ArrayList<>();

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Integer getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(Integer dataLevel) {
        this.dataLevel = dataLevel;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DocumentDictionary> getDocumentDictionaryList() {
        return documentDictionaryList;
    }

    public void setDocumentDictionaryList(List<DocumentDictionary> documentDictionaryList) {
        this.documentDictionaryList = documentDictionaryList;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public int compareTo(DocumentDataLevel o) {
        return this.getDataLevel().compareTo(o.getDataLevel());
    }
}
