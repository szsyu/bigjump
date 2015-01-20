package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.DocAssigneeCandidatesDao;
import com.ect.bigjump.domain.DocAssigneeCandidates;
import com.ect.bigjump.domain.Page;
import com.ect.bigjump.service.DocAssigneeCandidatesService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Document Assignee Or Candicates Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-20
 */
@Service("docAssigneeCandidatesService")
public class DocAssigneeCandidatesServiceImpl extends BaseServiceImpl<DocAssigneeCandidates, Long> implements DocAssigneeCandidatesService {

    private static final int PAGE_SIZE = 10;

    @Resource(name = "docAssigneeCandidatesDao")
    private DocAssigneeCandidatesDao docAssigneeCandidatesDao;

    @Override
    public BaseDao<DocAssigneeCandidates, Long> getDao() {
        return docAssigneeCandidatesDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteByTaskId(String taskId, Long documentMasterId) throws Exception {
        String sql = "delete from BJ_DOC_ASS_CAN where taskId = ? and documentMasterId = ?";
        Object[] params = {taskId, documentMasterId};
        SQLQuery sqlQuery = docAssigneeCandidatesDao.createSQLQuery(sql, params);
        sqlQuery.executeUpdate();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteByDocMasterId(Long documentMasterId) throws Exception {
        String sql = "delete from BJ_DOC_ASS_CAN where documentMasterId = ?";
        Object[] params = {documentMasterId};
        SQLQuery sqlQuery = docAssigneeCandidatesDao.createSQLQuery(sql, params);
        sqlQuery.executeUpdate();
    }

    @Override
    public List<DocAssigneeCandidates> getAllTaskByUser(Long userId) {
        String sql = "from BJ_DOC_ASS_CAN where userId = ? order by creationDate desc";
        Object[] params = {userId};
        return docAssigneeCandidatesDao.findBySql(sql, params);
    }

    @Override
    public Page<DocAssigneeCandidates> queryForPage(int currentPage, Long userId, String documentNumberLike, String documentCode) {
        String sql = "select bdac.* " +
                "from BJ_DOC_ASS_CAN bdac," +
                "BJ_DOCUMENT_MASTER bdm " +
                "where bdm.id = bdac.documentMasterId ";
        SQLQuery query;
        String baseUrl;

        if (!StringUtils.isBlank(documentNumberLike)) {
            if (!StringUtils.isBlank(documentCode)) {
                sql = sql + " and  bdac.userId = ? and bdm.documentCode = ? and bdm.documentNumber like ?";
                Object[] params = {userId, documentCode, documentNumberLike};
                query = docAssigneeCandidatesDao.createSQLQuery(sql, params);
                baseUrl = "../Task/showMyTaskList?userId=" + userId + "&documentCode=" + documentCode + "&documentNumberLike=" + documentNumberLike + "&page=";
            } else {
                sql = sql + " and bdac.userId = ? and bdm.documentNumber like ?";
                Object[] params = {userId, documentNumberLike};
                query = docAssigneeCandidatesDao.createSQLQuery(sql, params);
                baseUrl = "../Task/showMyTaskList?userId=" + userId + "&documentNumberLike=" + documentNumberLike + "&page=";
            }

        } else {
            if (!StringUtils.isBlank(documentCode)) {
                sql = sql + " and bdac.userId = ? and bdm.documentCode = ?";
                Object[] params = {userId, documentCode};
                query = docAssigneeCandidatesDao.createSQLQuery(sql, params);
                baseUrl = "../Task/showMyTaskList?userId=" + userId + "&documentCode=" + documentCode + "&page=";
            } else {
                sql = sql + " and  bdac.userId = ?";
                Object[] params = {userId};
                query = docAssigneeCandidatesDao.createSQLQuery(sql, params);
                baseUrl = "../Task/showMyTaskList?userId=" + userId + "&page=";
            }
        }

        int allRow = query.list().size();
        int totalPage = Page.countTotalPage(PAGE_SIZE, allRow);
        int offset = Page.countOffset(PAGE_SIZE, currentPage);

        query.setFirstResult(offset);
        query.setMaxResults(PAGE_SIZE);
        List<DocAssigneeCandidates> taskList = query.addEntity(DocAssigneeCandidates.class).list();

        Page<DocAssigneeCandidates> page = new Page<DocAssigneeCandidates>();
        page.setBasePageUrl(baseUrl);
        page.setPageSize(PAGE_SIZE);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(taskList);
        page.init();

        return page;
    }
}
