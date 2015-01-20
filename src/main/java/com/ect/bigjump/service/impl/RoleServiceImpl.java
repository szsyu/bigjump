package com.ect.bigjump.service.impl;

import javax.annotation.Resource;

import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.User;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.RoleDao;
import com.ect.bigjump.domain.Role;
import com.ect.bigjump.service.RoleService;

import java.util.List;


/**
 * Role服务层实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    private final static int PAGE_SIZE = 10;

    private final static String ORDER_BY = " order by roleCode";

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @Override
    public BaseDao<Role, Long> getDao() {
        return roleDao;
    }

    @Override
    public Page<Role> queryForPage(int currentPage, String documentCode) {
        String sql = "select * from BJ_ROLE where 1 = 1";
        SQLQuery query;
        String baseUrl;
        if (documentCode != null && !"".equals(documentCode)) {
            Object[] params = {documentCode};
            sql = sql + " and documentCode = ?" + ORDER_BY;
            query = roleDao.createSQLQuery(sql, params);
            baseUrl = "../Role/showRoleList?documentCOde=" + documentCode + "&page=";
        } else {
            sql = sql + ORDER_BY;
            query = roleDao.createSQLQuery(sql);
            baseUrl = "../Role/showRoleList?page=";
        }

        int allRow = query.list().size();
        int totalPage = Page.countTotalPage(PAGE_SIZE, allRow);
        int offset = Page.countOffset(PAGE_SIZE, currentPage);

        query.setFirstResult(offset);
        query.setMaxResults(PAGE_SIZE);
        List<Role> roleList = query.addEntity(Role.class).list();

        Page<Role> page = new Page<Role>();
        page.setBasePageUrl(baseUrl);
        page.setPageSize(PAGE_SIZE);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(roleList);
        page.init();

        return page;
    }

    @Override
    public List<User> getUserListByRoleCode(String roleCode) {
        String hql = "from Role where roleCode = ?";
        Object[] params = {roleCode};
        List<Role> roleList = roleDao.findByHql(hql, params);

        if (!roleList.isEmpty()) {
            return roleList.get(0).getUsers();
        } else {
            return null;
        }
    }
}
