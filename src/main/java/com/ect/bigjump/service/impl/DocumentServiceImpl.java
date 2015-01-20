package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocumentDao;
import com.ect.bigjump.domain.Document;
import com.ect.bigjump.domain.DocumentDataLevel;
import com.ect.bigjump.domain.Page;
import com.ect.bigjump.service.DocumentDataLevelService;
import com.ect.bigjump.service.DocumentDictionaryService;
import com.ect.bigjump.service.DocumentService;
import com.ect.bigjump.utility.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Document服务层实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
@Service("documentService")
public class DocumentServiceImpl extends BaseServiceImpl<Document, Long> implements DocumentService {

    private static final String ORDER_BY = " order by isActive,documentCode";

    private static final int PAGE_SIZE = 10;

    @Resource(name = "documentDao")
    private DocumentDao documentDao;

    @Resource(name = "documentDataLevelService")
    private DocumentDataLevelService documentDataLevelService;

    @Resource(name = "documentDictionaryService")
    private DocumentDictionaryService documentDictionaryService;

    @Override
    public BaseDao<Document, Long> getDao() {
        return documentDao;
    }

    @Override
    public List<Document> getActiveDocuments() {
        String hql = "from Document where isActive = ?";
        Object[] params = {"Y"};
        List<Document> activeDocumentList = documentDao.findByHql(hql, params);
        return activeDocumentList;
    }

    @Override
    @Transactional
    public Document getByCode(String documentCode) {
        List<Document> documentList = documentDao.findBy("documentCode", documentCode);
        if (documentList != null) {
            return documentList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Page<Document> queryForPage(int currentPage, String documentCodeLike) {
        String sql = "select * from BJ_DOCUMENT where 1 = 1";
        SQLQuery query;
        String baseUrl;
        if (documentCodeLike != null && !"".equals(documentCodeLike)) {
            Object[] params = {documentCodeLike};
            sql = sql + " and documentCode like ?" + ORDER_BY;
            query = documentDao.createSQLQuery(sql, params);
            baseUrl = "../Document/showDocumentList?documentCodeLike=" + documentCodeLike + "&page=";
        } else {
            sql = sql + ORDER_BY;
            query = documentDao.createSQLQuery(sql);
            baseUrl = "../Document/showDocumentList?page=";
        }

        int allRow = query.list().size();
        int totalPage = Page.countTotalPage(PAGE_SIZE, allRow);
        int offset = Page.countOffset(PAGE_SIZE, currentPage);

        query.setFirstResult(offset);
        query.setMaxResults(PAGE_SIZE);
        List<Document> documentList = query.addEntity(Document.class).list();

        Page<Document> page = new Page<Document>();
        page.setBasePageUrl(baseUrl);
        page.setPageSize(PAGE_SIZE);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(documentList);
        page.init();

        return page;
    }

    @Override
    public boolean checkAvaiableByCode(String documentCode) {
        Document document = getByCode(documentCode);
        if (document == null) {
            return false;
        }
        if ("Y".equals(document.getIsActive())) {
            return true;
        }

        return true;
    }

    /**
     * 重写add方法,当保存新建的Document时,生成Level为0的Date Level和Document Dictionary
     *
     * @param document
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(Document document) throws Exception {
        super.add(document);
        //新增Level为0的Data Level
        DocumentDataLevel documentDataLevel = new DocumentDataLevel();
        documentDataLevel.setDocument(document);
        documentDataLevel.setDataLevel(0);
        documentDataLevel.setLevelName("Please fill level name");
        documentDataLevel.setIsActive("Y");
        documentDataLevelService.add(documentDataLevel);

    }

}
