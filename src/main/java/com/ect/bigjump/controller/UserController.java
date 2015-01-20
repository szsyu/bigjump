package com.ect.bigjump.controller;

import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.Role;
import com.ect.bigjump.domain.User;
import com.ect.bigjump.service.RoleService;
import com.ect.bigjump.service.UserService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户前端控制类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-03
 */
@Controller
@RequestMapping(value = "/User")
public class UserController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    /**
     * 用户列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/showUserList")
    public ModelAndView showUseList(HttpServletRequest request) {
        Map model = new HashMap<>();

        String page = request.getParameter("page");
        String nameLike = request.getParameter("nameLike");
        int pageNo = (page == null || "".equals(page)) ? 1 : Integer.parseInt(page);
        nameLike = nameLike == null || "".equals(nameLike) ? null : nameLike;

        Page<User> userPage = userService.queryForPage(pageNo, nameLike);
        model.put("userPage", userPage);

        return new ModelAndView("/User/showUserList", model);
    }

    /**
     * 新增用户页面
     *
     * @return
     */
    @RequestMapping(value = "/addUser")
    public ModelAndView addUser() {
        return new ModelAndView("/User/addUser");
    }

    /**
     * 保存用户页面
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(User user) {
        try {
            userService.add(user);
            return "/User/addRoleToUser";
        } catch (Exception e) {
            return "/common/error";
        }
    }

    /**
     * 编辑用户页面
     *
     * @param userId
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(@RequestParam("userId") Long userId) {
        Map model = new HashedMap();
        model.put("user", userService.get(userId));
        return new ModelAndView("/User/editUser", model);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(User user) {
        Map model = new HashedMap<>();
        try {
            userService.update(user);
            return "redirect:/User/RoleForUser?userId" + user.getId();
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * Ajax重置用户密码,并发送邮件给用户
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/resetPasswordAjax", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> resetPasswordAjax(@RequestParam("userId") Long userId) {
        Map map = new HashMap<>();
        try {
            map = userService.resetPassword(userId);
            map.put("status", "SUCCESS");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "ERROR");
            return map;
        }

    }

    /**
     * 用户的角色列表
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/showRoles4User", method = RequestMethod.GET)
    public ModelAndView showRoles4User(@RequestParam("userId") Long userId) {
        Map model = new HashMap<>();
        User user = userService.get(userId);
        model.put("user", user);
        model.put("roleList", roleService.getAll());
        return new ModelAndView("/User/showRoles4User", model);
    }

    /**
     * 增加角色给指定用户
     *
     * @param userId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/addRole2User", method = RequestMethod.POST)
    public ModelAndView addRole2User(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        User user = userService.get(userId);
        Role role = roleService.get(roleId);
        user.getRoles().add(role);
        try {
            userService.update(user);
            return new ModelAndView("redirect:/User/showRoles4User?userId=" + userId);
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.toString());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * 移除用户拥有的指定角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/removeRole4User", method = RequestMethod.GET)
    public ModelAndView removeRole4User(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        User user = userService.get(userId);
        Role role = roleService.get(roleId);
        user.getRoles().remove(role);
        try {
            userService.update(user);
            return new ModelAndView("redirect:/User/showRoles4User?userId=" + userId);
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.toString());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * 判断用户是否已经拥有此角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/checkRoleExist4User")
    @ResponseBody
    public Map<String, Object> checkRoleExist4User(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
        Map<String, Object> model = new HashMap<String, Object>();
        boolean isExist = userService.isRoleExist4User(userId, roleId);
        if (isExist) {
            model.put("status", "EXIST");
            return model;
        } else {
            model.put("status", "NOT_EXIST");
            return model;
        }
    }
}
