package com.ect.bigjump.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lookup Type实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-08
 */
@Entity
@Table(name = "BJ_LOOKUP_TYPE")
public class LookupType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8939056460080691816L;

    /**
     * Lookup Type Code,唯一，且不可更改
     */
    @Column(length = 64,nullable = false,unique = true,updatable = false)
    private String lookupTypeCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否可用
     */
    @Column(length = 1)
    private String isActive;

    /**
     * 值得来源,U为User对象,D为直接量
     */
    @Column(length = 1, nullable = false)
    private String valueSource;

    /**
     * 数据类型:Long长整形,Double长浮点型,String字符串
     */
    @Column(length = 20)
    private String dataType;

    /**
     * 所拥有的Lookup Value集合
     */
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "lookupType")
    private List<LookupValue> lookupValueList = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLookupTypeCode() {
        return lookupTypeCode;
    }

    public void setLookupTypeCode(String lookupTypeCode) {
        this.lookupTypeCode = lookupTypeCode;
    }

    public List<LookupValue> getLookupValueList() {
        return lookupValueList;
    }

    public void setLookupValueList(List<LookupValue> lookupValueList) {
        this.lookupValueList = lookupValueList;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getValueSource() {
        return valueSource;
    }

    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
