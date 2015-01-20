package com.ect.bigjump.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Document Data导入接口实体类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Entity
@Table(name = "BJ_DOCUMENT_DATA_INTERFACE")
public class DocDataInterface extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 818872644602263271L;

    /**
     * 外键字段:documentCode,对应Document的唯一索引字段documentCode
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentCode", referencedColumnName = "documentCode",nullable = false)
    private Document document;

    /**
     * Document Number,当dataLevel为0时有值，且是唯一
     */
    private String documentNumber;

    /**
     * 数据级别:0为第一层，如PO Header,1为下一层，如PO Line
     */
    private Integer dataLevel;

    /**
     * 数据的运行状态：N为未进行数据导入操作的数据,E为导入出错的数据
     */
    @Column(length = 1, nullable = false)
    private String processStatus;

    /**
     * 源数据的主键
     */
    @Column(length = 64)
    private String sourcePKId;

    /**
     * 源数据的外键ID,当dataLevel > 0时有值,当dataLevel为0时为空
     */
    @Column(length = 64)
    private String sourceFKId;

    /**
     * 数据data1~32
     */
    private String data1;

    private String data2;

    private String data3;

    private String data4;

    private String data5;

    private String data6;

    private String data7;

    private String data8;

    private String data9;

    private String data10;

    private String data11;

    private String data12;

    private String data13;

    private String data14;

    private String data15;

    private String data16;

    private String data17;

    private String data18;

    private String data19;

    private String data20;

    private String data21;

    private String data22;

    private String data23;

    private String data24;

    private String data25;

    private String data26;

    private String data27;

    private String data28;

    private String data29;

    private String data30;

    private String data31;

    private String data32;

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

    public Integer getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(Integer dataLevel) {
        this.dataLevel = dataLevel;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getSourcePKId() {
        return sourcePKId;
    }

    public void setSourcePKId(String sourcePKId) {
        this.sourcePKId = sourcePKId;
    }

    public String getSourceFKId() {
        return sourceFKId;
    }

    public void setSourceFKId(String sourceFKId) {
        this.sourceFKId = sourceFKId;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }

    public String getData5() {
        return data5;
    }

    public void setData5(String data5) {
        this.data5 = data5;
    }

    public String getData6() {
        return data6;
    }

    public void setData6(String data6) {
        this.data6 = data6;
    }

    public String getData7() {
        return data7;
    }

    public void setData7(String data7) {
        this.data7 = data7;
    }

    public String getData8() {
        return data8;
    }

    public void setData8(String data8) {
        this.data8 = data8;
    }

    public String getData9() {
        return data9;
    }

    public void setData9(String data9) {
        this.data9 = data9;
    }

    public String getData10() {
        return data10;
    }

    public void setData10(String data10) {
        this.data10 = data10;
    }

    public String getData11() {
        return data11;
    }

    public void setData11(String data11) {
        this.data11 = data11;
    }

    public String getData12() {
        return data12;
    }

    public void setData12(String data12) {
        this.data12 = data12;
    }

    public String getData13() {
        return data13;
    }

    public void setData13(String data13) {
        this.data13 = data13;
    }

    public String getData14() {
        return data14;
    }

    public void setData14(String data14) {
        this.data14 = data14;
    }

    public String getData15() {
        return data15;
    }

    public void setData15(String data15) {
        this.data15 = data15;
    }

    public String getData16() {
        return data16;
    }

    public void setData16(String data16) {
        this.data16 = data16;
    }

    public String getData17() {
        return data17;
    }

    public void setData17(String data17) {
        this.data17 = data17;
    }

    public String getData18() {
        return data18;
    }

    public void setData18(String data18) {
        this.data18 = data18;
    }

    public String getData19() {
        return data19;
    }

    public void setData19(String data19) {
        this.data19 = data19;
    }

    public String getData20() {
        return data20;
    }

    public void setData20(String data20) {
        this.data20 = data20;
    }

    public String getData21() {
        return data21;
    }

    public void setData21(String data21) {
        this.data21 = data21;
    }

    public String getData22() {
        return data22;
    }

    public void setData22(String data22) {
        this.data22 = data22;
    }

    public String getData23() {
        return data23;
    }

    public void setData23(String data23) {
        this.data23 = data23;
    }

    public String getData24() {
        return data24;
    }

    public void setData24(String data24) {
        this.data24 = data24;
    }

    public String getData25() {
        return data25;
    }

    public void setData25(String data25) {
        this.data25 = data25;
    }

    public String getData26() {
        return data26;
    }

    public void setData26(String data26) {
        this.data26 = data26;
    }

    public String getData27() {
        return data27;
    }

    public void setData27(String data27) {
        this.data27 = data27;
    }

    public String getData28() {
        return data28;
    }

    public void setData28(String data28) {
        this.data28 = data28;
    }

    public String getData29() {
        return data29;
    }

    public void setData29(String data29) {
        this.data29 = data29;
    }

    public String getData30() {
        return data30;
    }

    public void setData30(String data30) {
        this.data30 = data30;
    }

    public String getData31() {
        return data31;
    }

    public void setData31(String data31) {
        this.data31 = data31;
    }

    public String getData32() {
        return data32;
    }

    public void setData32(String data32) {
        this.data32 = data32;
    }
}
