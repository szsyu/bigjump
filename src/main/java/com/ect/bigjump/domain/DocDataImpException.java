package com.ect.bigjump.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Document Data 导入异常记录类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014
 */
@Entity
@Table(name = "BJ_DOCDATA_IMPT_EXP")
public class DocDataImpException extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8930543593871513075L;

    /**
     * 数据接口表ID
     */
    private Long docDataInterfaceId;

    /**
     * 错误信息
     */
    @Column(length = 1000)
    private String errorMessage;

    public DocDataImpException(){}

    public DocDataImpException(Long docDataInterfaceId,String errorMessage){
        this.docDataInterfaceId = docDataInterfaceId;
        this.errorMessage = errorMessage;
    }

    public Long getDocDataInterfaceId() {
        return docDataInterfaceId;
    }

    public void setDocDataInterfaceId(Long docDataInterfaceId) {
        this.docDataInterfaceId = docDataInterfaceId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
