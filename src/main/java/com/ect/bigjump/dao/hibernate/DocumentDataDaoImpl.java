package com.ect.bigjump.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.ect.bigjump.dao.DocumentDataDao;
import com.ect.bigjump.domain.DocumentData;

@Repository("documentDataDao")
public class DocumentDataDaoImpl extends BaseDaoImpl<DocumentData, Long> implements DocumentDataDao{

}
