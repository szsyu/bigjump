package com.ect.bigjump.service;

import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.User;

import java.util.Map;

/**
 * User服务层接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
public interface UserService extends BaseService<User, Long> {

    /**
     * 用户分页查询
     *
     * @param currentPage
     * @param nameLike
     * @return
     */
    Page<User> queryForPage(int currentPage, String nameLike);

    /**
     * 重置密码
     *
     * @param userId
     * @return
     * @throws Exception
     */
    Map<String, String> resetPassword(Long userId) throws Exception;

    /**
     * 根据User Name获取用户对象
     *
     * @param userName
     * @return
     */
    User getByUserName(String userName);

    /**
     * 根据User Name和Password获取用户对象,主要用于登录验证
     *
     * @param userName
     * @param password
     * @return
     */
    User getByUserNamePassword(String userName, String password);

    /**
     * 判断Role是否已经存在于该用户
     *
     * @param userId
     * @param roleId
     * @return
     */
    boolean isRoleExist4User(Long userId, Long roleId);
}
