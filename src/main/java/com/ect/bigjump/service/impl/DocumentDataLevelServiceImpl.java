package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocumentDataLevelDao;
import com.ect.bigjump.domain.DocumentDataLevel;
import com.ect.bigjump.service.DocumentDataLevelService;
import com.ect.bigjump.service.DocumentDictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Document Data Level Service类
 *
 * @author Shawn Yu
 * @since 2014-12-04
 * @version 1.0
 *
 */
@Service("documentDataLevelService")
public class DocumentDataLevelServiceImpl extends BaseServiceImpl<DocumentDataLevel,Long> implements DocumentDataLevelService{

    @Resource(name="documentDataLevelDao")
    private DocumentDataLevelDao documentDataLevelDao;

    @Resource(name = "documentDictionaryService")
    private DocumentDictionaryService documentDictionaryService;

    @Override
    public BaseDao<DocumentDataLevel, Long> getDao() {
        return documentDataLevelDao;
    }

    /**
     * 重写add方法,在新建Data Level时,产生此Level下的数据字典
     *
     * @param documentDataLevel
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void add(DocumentDataLevel documentDataLevel) throws Exception{
        super.add(documentDataLevel);
        documentDictionaryService.generateDocuDictsByDataLevel(documentDataLevel);
    }
}
