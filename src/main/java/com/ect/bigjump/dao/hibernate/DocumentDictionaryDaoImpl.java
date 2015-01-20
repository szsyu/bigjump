package com.ect.bigjump.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.ect.bigjump.dao.DocumentDictionaryDao;
import com.ect.bigjump.domain.DocumentDictionary;

/**
 * DocumentDictionary持久化实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
@Repository("documentDictionaryDao")
public class DocumentDictionaryDaoImpl extends BaseDaoImpl<DocumentDictionary, Long> implements DocumentDictionaryDao {
}
