package com.ect.bigjump.service.impl;

import javax.annotation.Resource;

import com.ect.bigjump.domain.Document;
import com.ect.bigjump.domain.DocumentDataLevel;
import org.springframework.stereotype.Service;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocumentDictionaryDao;
import com.ect.bigjump.domain.DocumentDictionary;
import com.ect.bigjump.service.DocumentDictionaryService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DocumentDictionary服务层实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
@Service("documentDictionaryService")
public class DocumentDictionaryServiceImpl extends BaseServiceImpl<DocumentDictionary, Long> implements DocumentDictionaryService {

    private static final int DOCUMENT_DATA_COLUMN_COUNT = 32;

    @Resource(name = "documentDictionaryDao")
    private DocumentDictionaryDao documentDictionaryDao;

    @Override
    public BaseDao<DocumentDictionary, Long> getDao() {
        return documentDictionaryDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void generateDocuDictsByDataLevel(DocumentDataLevel documentDataLevel) throws Exception {
        for (int i = 1; i <= DOCUMENT_DATA_COLUMN_COUNT; i++) {
            DocumentDictionary documentDictionary = new DocumentDictionary();
            documentDictionary.setDocumentDataColumn("data" + i);
            documentDictionary.setDocument(documentDataLevel.getDocument());
            documentDictionary.setVisible("N");
            documentDictionary.setDocumentDataLevel(documentDataLevel);
            super.add(documentDictionary);
        }
    }

    @Override
    public List<DocumentDictionary> getActiveDocDictForDoc(Document document) {
        String sql = "select * from BJ_DOCUMENT_DICT where documentCode = ? and isActive = ? order by documentDataLevelId,columnName";
        Object[] params = {document.getDocumentCode(), "Y"};
        List<DocumentDictionary> activeDocDictList = documentDictionaryDao.findBySql(sql, params);
        return activeDocDictList;
    }
}
