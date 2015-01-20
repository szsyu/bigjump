package com.ect.bigjump.controller;

import com.ect.bigjump.domain.LookupType;
import com.ect.bigjump.domain.LookupValue;
import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.User;
import com.ect.bigjump.service.LookupTypeService;
import com.ect.bigjump.service.LookupValueService;
import com.ect.bigjump.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Lookup 前端控制类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-09
 */
@Controller
@RequestMapping("/Lookup")
public class LookupController extends BaseController {

    @Resource(name = "lookupTypeService")
    private LookupTypeService lookupTypeService;

    @Resource(name = "lookupValueService")
    private LookupValueService lookupValueService;

    @Resource(name = "userService")
    private UserService userService;

    /**
     * Lookup Type分页显示
     *
     * @return
     */
    @RequestMapping(value = "/showLookupTypeList")
    public ModelAndView showLookupTypeList(HttpServletRequest request) {
        Map model = new HashMap<>();

        String page = request.getParameter("page");
        String lookupTypeCodeLike = request.getParameter("lookupTypeCodeLike");
        int currentPage = (page == null || "".equals(page)) ? 1 : Integer.parseInt(page);
        lookupTypeCodeLike = lookupTypeCodeLike == null || "".equals(lookupTypeCodeLike) ? null : lookupTypeCodeLike;

        Page<LookupType> lookupTypePage = lookupTypeService.queryForPage(currentPage, lookupTypeCodeLike);
        model.put("lookupTypePage", lookupTypePage);
        model.put("dataTypeList", lookupValueService.getDataTypes());
        return new ModelAndView("/Lookup/showLookupTypeList", model);
    }

    /**
     * 保存新增的Lookup Type,并跳转到Lookup Value页面
     *
     * @param lookupType
     * @return
     */
    @RequestMapping(value = "/saveLookupType", method = RequestMethod.POST)
    public String saveLookupType(LookupType lookupType) {
        //当Value Source为User时,清除Data Type内容
        if ("U".equals(lookupType.getValueSource())) {
            lookupType.setDataType(null);
        }
        try {
            lookupTypeService.add(lookupType);
            return "redirect:/Lookup/showLookupValueList?lookupTypeCode=" + lookupType.getLookupTypeCode();
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * 更新Lookup Type
     *
     * @param lookupType
     * @return
     */
    @RequestMapping(value = "/updateLookupType", method = RequestMethod.POST)
    public String updateLookupType(LookupType lookupType) {
        try {
            lookupTypeService.update(lookupType);
            return "redirect:/Lookup/showLookupTypeList?lookupTypeCodeLike=" + lookupType.getLookupTypeCode();
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * Lookup 分页显示
     *
     * @param lookupTypeCode
     * @return
     */
    @RequestMapping(value = "/showLookupValueList")
    public ModelAndView showLookupValueList(@RequestParam("lookupTypeCode") String lookupTypeCode, HttpServletRequest request) {
        Map model = new HashMap<>();

        String page = request.getParameter("page");
        String lookupCodeLike = request.getParameter("lookupTypeCodeLike");
        int currentPage = (page == null || "".equals(page)) ? 1 : Integer.parseInt(page);
        lookupCodeLike = lookupCodeLike == null || "".equals(lookupCodeLike) ? null : lookupCodeLike;

        Page<LookupValue> lookupValuePage = lookupValueService.queryForPage(currentPage, lookupTypeCode, lookupCodeLike);
        model.put("lookupValuePage", lookupValuePage);

        LookupType lookupType = lookupTypeService.getByCode(lookupTypeCode);
        model.put("lookupType", lookupType);

        if ("U".equals(lookupType.getValueSource())) {
            model.put("activeUserList", userService.getAll());
        }

        return new ModelAndView("/Lookup/showLookupValueList", model);
    }

    /**
     * 保存Lookup Value
     *
     * @param lookupValue
     * @return
     */
    @RequestMapping(value = "/saveLookupValue", method = RequestMethod.POST)
    public String saveLookupValue(LookupValue lookupValue, @RequestParam("lookupTypeCode") String lookupTypeCode, Long userId) {
        LookupType lookupType = lookupTypeService.getByCode(lookupTypeCode);
        lookupValue.setLookupType(lookupType);

        if ("U".equals(lookupType.getValueSource())) {
            User user = userService.get(userId);
            lookupValue.setUser(user);
            lookupValue.setValue(null);
        } else if ("D".equals(lookupType.getValueSource())) {
            lookupValue.setUser(null);
        }

        try {
            lookupValueService.add(lookupValue);
            return "redirect:/Lookup/showLookupValueList?lookupTypeCode=" + lookupTypeCode;
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * 更新Lookup Value
     * @param lookupValue
     * @param lookupTypeCode
     * @param userId
     * @return
     */
    @RequestMapping(value = "/updateLookupValue", method = RequestMethod.POST)
    public String updateLookupValue(LookupValue lookupValue,@RequestParam("lookupTypeCode") String lookupTypeCode, Long userId){
        LookupType lookupType = lookupTypeService.getByCode(lookupTypeCode);
        lookupValue.setLookupType(lookupType);

        if ("U".equals(lookupType.getValueSource())) {
            User user = userService.get(userId);
            lookupValue.setUser(user);
            lookupValue.setValue(null);
        } else if ("D".equals(lookupType.getValueSource())) {
            lookupValue.setUser(null);
        }

        try {
            lookupValueService.add(lookupValue);
            return "redirect:/Lookup/showLookupValueList?lookupTypeCode=" + lookupTypeCode;
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * 删除Lookup Value
     * @param lookupValueId
     * @return
     */
    @RequestMapping(value = "/deleteLookupValue")
    public String deleteLookupValue(@RequestParam("lookupValueId") Long lookupValueId){
        LookupValue lookupValue = lookupValueService.get(lookupValueId);
        String lookupTypeCode = lookupValue.getLookupType().getLookupTypeCode();

        try{
            lookupValueService.delete(lookupValue);
            return "redirect:/Lookup/showLookupValueList?lookupTypeCode=" + lookupTypeCode;
        }catch (Exception e){
            return "/Common/error";
        }
    }
}
