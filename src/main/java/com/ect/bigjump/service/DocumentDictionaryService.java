package com.ect.bigjump.service;

import com.ect.bigjump.domain.Document;
import com.ect.bigjump.domain.DocumentDataLevel;
import com.ect.bigjump.domain.DocumentDictionary;

import java.util.List;

/**
 * DocumentDictionary服务层接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
public interface DocumentDictionaryService extends BaseService<DocumentDictionary, Long> {

    /**
     * 批量产生DataLevel下所有数据字典
     *
     * @param documentDataLevel
     */
    void generateDocuDictsByDataLevel(DocumentDataLevel documentDataLevel) throws Exception;

    /**
     * 获取Document下的所有Active数据字典
     *
     * @param document
     * @return
     */
    List<DocumentDictionary> getActiveDocDictForDoc(Document document);
}
