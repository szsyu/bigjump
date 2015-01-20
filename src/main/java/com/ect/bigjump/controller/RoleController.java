package com.ect.bigjump.controller;


import com.ect.bigjump.domain.Document;
import com.ect.bigjump.domain.Page;
import com.ect.bigjump.domain.Role;
import com.ect.bigjump.service.DocumentService;
import com.ect.bigjump.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色前端控制类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-03
 */
@Controller
@RequestMapping("/Role")
public class RoleController extends BaseController {

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "documentService")
    private DocumentService documentService;

    /**
     * 默认角色Home Page,角色List
     *
     * @return
     */
    @RequestMapping(value = "/showRoleList")
    public ModelAndView showRoleList(HttpServletRequest request) {
        Map model = new HashMap<>();

        String page = request.getParameter("page");
        String documentCode = request.getParameter("documentCode");
        int pageNo = (page == null || "".equals(page)) ? 1 : Integer.parseInt(page);
        documentCode = documentCode == null || "".equals(documentCode) ? null : documentCode;

        Page<Role> rolePage = roleService.queryForPage(pageNo, documentCode);
        model.put("rolePage", rolePage);

        List<Document> activeDocumentList = documentService.getActiveDocuments();
        model.put("activeDocumentList", activeDocumentList);

        return new ModelAndView("/Role/showRoleList", model);
    }

    /**
     * 新增角色页面
     *
     * @return
     */
    @RequestMapping(value = "/addRole")
    public ModelAndView addRole() {
        Map model = new HashMap<>();
        List<Document> activeDocumentList = documentService.getActiveDocuments();
        model.put("activeDocumentList", activeDocumentList);
        return new ModelAndView("/Role/addRole", model);
    }

    /**
     * 保存新增的Role
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public String saveRole(Role role, @RequestParam("documentCodeNew") String documentCodeNew) {
        Document document = documentService.getByCode(documentCodeNew);
        role.setDocument(document);
        try {
            roleService.add(role);
            return "redirect:/Role/showRoleList";
        } catch (Exception e) {
            return "/Common/error";
        }
    }

    /**
     * 更改Role
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public String updateRole(Role role) {
        try {
            roleService.update(role);
            return "redirect:/Role/showRoleList";
        } catch (Exception e) {
            return "/Common/error";
        }
    }
}
