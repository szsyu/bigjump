package com.ect.bigjump.controller;

import com.ect.bigjump.utility.DateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    /**
     * 基础前段控制类,包括通用程序，如数据自动绑定等
     *
     * @param request 请求
     * @param binder  绑定
     * @throws Exception 异常
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // 对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * 异常处理
     *
     * @param e
     * @return
     */
    protected ModelAndView redirectException(Exception e) {
        Map<String, Object> model = new HashMap<>();
        model.put("exception", e.getMessage());
        return new ModelAndView("redirect:/Common/exceptionOccurs", model);
    }
}
