package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocumentDataDao;
import com.ect.bigjump.domain.*;
import com.ect.bigjump.service.DocumentDataService;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * DocumentData服务层实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
@Service("documentDataService")
public class DocumentDataServiceImpl extends BaseServiceImpl<DocumentData, Long> implements DocumentDataService {

    @Resource(name = "documentDataDao")
    private DocumentDataDao documentDataDao;

    @Override
    public BaseDao<DocumentData, Long> getDao() {
        return documentDataDao;
    }

    @Override
    public DocumentDetail getDocumentDetail(DocumentMaster documentMaster) throws Exception {
        DocumentDetail documentDetail = new DocumentDetail();
        documentDetail.setDocumentNumber(documentMaster.getDocumentNumber());
        documentDetail.setDescription(documentMaster.getDescription());

        DocumentData documentData0 = documentMaster.getDocumentData();
        List<DocumentData> documentDataList1 = documentData0.getChildDataList();

        Document document = documentMaster.getDocument();

        List<DocumentDataLevel> documentDataLevelList = document.getDocumentDataLevelList();
        for (DocumentDataLevel documentDataLevel : documentDataLevelList) {
            List<DocumentDictionary> documentDictionaryList = documentDataLevel.getDocumentDictionaryList();
            //当Data Level为0时
            if (documentDataLevel.getDataLevel() == 0 && "Y".equals(documentDataLevel.getIsActive())) {
                documentDetail.setLevelZeroName(documentDataLevel.getLevelName());
                String title;
                String value;
                for (DocumentDictionary documentDictionary : documentDictionaryList) {

                    //组装 Data Level为0时的title和data
                    if ("Y".equals(documentDictionary.getIsActive()) && "Y".equals(documentDictionary.getVisible())) {
                        title = documentDictionary.getColumnLabel();
                        documentDetail.getLevelZeroTitle().add(title);

                        //使用反射获取Document Dictionary对应的栏位值
                        Object field = FieldUtils.readField(documentData0, documentDictionary.getDocumentDataColumn(), true);
                        if (field != null) {
                            value = field.toString();
                        } else {
                            value = null;
                        }
                        documentDetail.getLevelZeroData().add(value);
                    }

                }
                //当Data Level为1时
            } else if (documentDataLevel.getDataLevel() == 1 && "Y".equals(documentDataLevel.getIsActive())) {

                documentDetail.setLevelOneName(documentDataLevel.getLevelName());
                //组装Data Level为1时的title
                String title;
                for (DocumentDictionary documentDictionary : documentDictionaryList) {
                    if ("Y".equals(documentDictionary.getIsActive()) && "Y".equals(documentDictionary.getVisible())) {
                        title = documentDictionary.getColumnLabel();
                        documentDetail.getLevelOneTitle().add(title);

                    }
                }

                //组装Data Level为1时的data,与title匹配
                for (DocumentData documentData : documentDataList1) {
                    String value;
                    List<String> levelOneDataList = new ArrayList<String>();
                    for (DocumentDictionary documentDictionary : documentDictionaryList) {
                        if ("Y".equals(documentDictionary.getIsActive()) && "Y".equals(documentDictionary.getVisible())) {
                            //使用反射获取Document Dictionary对应的栏位值
                            Object field = FieldUtils.readField(documentData, documentDictionary.getDocumentDataColumn(), true);
                            if (field != null) {
                                value = field.toString();
                            } else {
                                value = null;
                            }
                            levelOneDataList.add(value);
                        }
                    }
                    documentDetail.getLevelOneData().add(levelOneDataList);
                }

            }
        }

        return documentDetail;
    }
}
