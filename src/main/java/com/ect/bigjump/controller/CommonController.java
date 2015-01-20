package com.ect.bigjump.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用前端控制类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-25
 */
@Controller
@RequestMapping(value = "/Common")
public class CommonController extends BaseController {

    /**
     * 没有权限，拒绝访问
     *
     * @return
     */
    @RequestMapping(value = "/accessDeny")
    public ModelAndView accessDeny(){
        return new ModelAndView("/Common/accessDeny");
    }

    /**
     * 异常产生
     * @return
     */
    @RequestMapping(value = "exceptionOccurs")
    public ModelAndView exceptionOccurs(String exception){
        Map model = new HashMap<>();
        model.put("exception",exception);
        return new ModelAndView("/Common/exceptionOccurs",model);
    }
}
