package com.ect.bigjump.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Document Detail实体类,用于组装Document Data和Document Dictionary,非持久化
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-26
 */
public class DocumentDetail implements Serializable{

    private String documentNumber;

    private String description;

    private String levelZeroName;

    private List<String> levelZeroTitle = new ArrayList<>();

    private List<String> levelZeroData = new ArrayList<>();

    private String levelOneName;

    private List<String> levelOneTitle = new ArrayList<String>();

    private List<List<String>> levelOneData = new ArrayList<>();

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

    public List<String> getLevelZeroTitle() {
        return levelZeroTitle;
    }

    public void setLevelZeroTitle(List<String> levelZeroTitle) {
        this.levelZeroTitle = levelZeroTitle;
    }

    public List<String> getLevelZeroData() {
        return levelZeroData;
    }

    public void setLevelZeroData(List<String> levelZeroData) {
        this.levelZeroData = levelZeroData;
    }

    public List<String> getLevelOneTitle() {
        return levelOneTitle;
    }

    public void setLevelOneTitle(List<String> levelOneTitle) {
        this.levelOneTitle = levelOneTitle;
    }

    public List<List<String>> getLevelOneData() {
        return levelOneData;
    }

    public void setLevelOneData(List<List<String>> levelOneData) {
        this.levelOneData = levelOneData;
    }

    public String getLevelZeroName() {
        return levelZeroName;
    }

    public void setLevelZeroName(String levelZeroName) {
        this.levelZeroName = levelZeroName;
    }

    public String getLevelOneName() {
        return levelOneName;
    }

    public void setLevelOneName(String levelOneName) {
        this.levelOneName = levelOneName;
    }
}
