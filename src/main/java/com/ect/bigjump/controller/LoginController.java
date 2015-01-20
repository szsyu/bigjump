package com.ect.bigjump.controller;

import com.ect.bigjump.domain.User;
import com.ect.bigjump.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录前端控制器
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-23
 */
@Controller
@RequestMapping("/Login")
public class LoginController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("/Login/login");
    }

    /**
     * 登录操作
     *
     * @param userName
     * @param password
     * @return
     */
    /**
     @RequestMapping(value = "/submitLogin", method = RequestMethod.POST)
     public ModelAndView submitLogin(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request) {
     String rememberMe = request.getParameter("rememberMe");
     User user = userService.getByUserNamePassword(userName, password);
     try {
     // 如果登陆成功
     if (user != null) {
     UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
     if (!StringUtils.isBlank(rememberMe)) {
     token.setRememberMe(true);
     }
     Subject subject = SecurityUtils.getSubject();
     subject.login(token);
     }
     } catch (Exception e) {
     e.printStackTrace();
     }
     return new ModelAndView("redirect:/Task/showMyTaskList");
     }
     */
    /**
     * 登录预处理
     *
     * @param j_username
     * @param j_password
     * @return
     */
    @RequestMapping(value = "/preLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> preLogin(@RequestParam("j_username") String j_username, @RequestParam("j_password") String j_password) {
        Map<String, Object> model = new HashMap<String, Object>();
        User user1 = userService.getByUserName(j_username);
        if (user1 == null) {
            model.put("loginStatus", "F");
            model.put("resultCode", "NO_USER");
            return model;
        }
        User user2 = userService.getByUserNamePassword(j_username, j_password);
        if (user2 == null) {
            model.put("loginStatus", "F");
            model.put("resultCode", "ERROR_PASS");
            return model;
        }
        model.put("loginStatus", "S");
        return model;
    }
}
