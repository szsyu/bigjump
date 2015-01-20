package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.UserDao;
import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.User;
import com.ect.bigjump.service.UserService;
import com.ect.bigjump.utility.RandomPass;
import org.hibernate.SQLQuery;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户服务类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-05
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private static final int PAGE_SIZE = 10;

    private static final String ORDER_BY = " order by userName";

    private static final String MAIL_FROM = "emr_shawn@163.com";

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "mailSender")
    private MailSender mailSender;

    @Override
    public BaseDao<User, Long> getDao() {
        return userDao;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<User> queryForPage(int currentPage, String nameLike) {

        String sql = "select * from BJ_USER where 1 = 1";
        SQLQuery query;
        String baseUrl;
        if (nameLike != null && !"".equals(nameLike)) {
            Object[] params = {nameLike};
            sql = sql + " and userName like ?" + ORDER_BY;
            query = userDao.createSQLQuery(sql, params);
            baseUrl = "../User/showUserList?nameLike=" + nameLike + "&page=";
        } else {
            sql = sql + ORDER_BY;
            query = userDao.createSQLQuery(sql);
            baseUrl = "../User/showUserList?page=";
        }

        int allRow = query.list().size();
        int totalPage = Page.countTotalPage(PAGE_SIZE, allRow);
        int offset = Page.countOffset(PAGE_SIZE, currentPage);

        query.setFirstResult(offset);
        query.setMaxResults(PAGE_SIZE);
        List<User> userList = query.addEntity(User.class).list();

        Page<User> page = new Page<User>();
        page.setBasePageUrl(baseUrl);
        page.setPageSize(PAGE_SIZE);
        page.setCurrentPage(currentPage);
        page.setAllRow(allRow);
        page.setTotalPage(totalPage);
        page.setList(userList);
        page.init();

        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, String> resetPassword(Long userId) throws Exception {
        Map map = new HashMap<>();

        User user = get(userId);
        String newRandomPass = RandomPass.getRandomPassword(8);//获取随机密码
        user.setPassword(newRandomPass);
        update(user);
        map.put("newPassword", newRandomPass);
        //如果邮件地址部位空,发送给用户相关密码重置的信息
        if (user.getEmail() != null && !"".equals(user.getEmail())) {
            map.put("email", user.getEmail());
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getEmail());
            mail.setFrom(MAIL_FROM);
            mail.setSubject("Password Reset For Big Jump");
            mail.setText("Your password of Big Jump has been reset to: " + newRandomPass);
            mailSender.send(mail);
        }
        return map;
    }

    @Override
    public User getByUserName(String userName) {
        String hql = "from User where userName = ?";
        Object[] params = {userName};
        List<User> userList = userDao.findByHql(hql, params);

        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getByUserNamePassword(String userName, String password) {
        String hql = "from User where userName = ? and password = ?";
        Object[] params = {userName, password};
        List<User> userList = userDao.findByHql(hql, params);

        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean isRoleExist4User(Long userId, Long roleId) {
        String sql = "select * from BJ_USER_ROLE where userId= ? and roleId = ?";
        Object[] params = {userId, roleId};
        SQLQuery sqlQuery = userDao.createSQLQuery(sql, params);
        int size = sqlQuery.list().size();
        return size > 0 ? true : false;
    }

}
