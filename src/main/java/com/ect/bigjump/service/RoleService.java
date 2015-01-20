package com.ect.bigjump.service;

import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.Role;
import com.ect.bigjump.domain.User;

import java.util.List;
import java.util.Set;

/**
 * Role服务层接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
public interface RoleService extends BaseService<Role, Long> {

    /**
     * Role分页查询
     *
     * @param currentPage
     * @param documentCode
     * @return
     */
    Page<Role> queryForPage(int currentPage, String documentCode);

    /**
     * 获取拥有指定Role Code的用户列表
     *
     * @param roleCode
     * @return
     */
    List<User> getUserListByRoleCode(String roleCode);
}
