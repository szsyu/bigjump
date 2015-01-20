package com.ect.bigjump.controller;

import com.ect.bigjump.domain.Organization;
import com.ect.bigjump.domain.OrganizationManager;
import com.ect.bigjump.domain.User;
import com.ect.bigjump.service.OrganizationManagerService;
import com.ect.bigjump.service.OrganizationService;
import com.ect.bigjump.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 组织前端控制类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2015-01-09
 */
@Controller
@RequestMapping("/Organization")
public class OrganizationController extends BaseController {

    @Resource(name = "organizationService")
    private OrganizationService organizationService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "organizationManagerService")
    private OrganizationManagerService organizationManagerService;

    /**
     * 所有Master Organization列表页面
     *
     * @return
     */
    @RequestMapping(value = "/showMasterOrganizationList", method = RequestMethod.GET)
    public ModelAndView showMasterOrganizationList() {
        Map model = new HashMap<>();
        List<Organization> masterOrganizationList = organizationService.getMasterOrganizationList();
        model.put("masterOrganizationList", masterOrganizationList);
        return new ModelAndView("/Organization/showMasterOrganizationList", model);
    }

    /**
     * 新增Master Organization
     *
     * @param organization
     * @return
     */
    @RequestMapping(value = "/addMasterOrganization", method = RequestMethod.POST)
    public ModelAndView addMasterOrganization(Organization organization) {
        organization.setIsMaster("Y");
        try {
            organizationService.add(organization);
            return new ModelAndView("redirect:/Organization/showOrganizationTree?masterOrganizationId=" + organization.getId());
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.getMessage());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * 更新Master Organization
     *
     * @param organization
     * @return
     */
    @RequestMapping(value = "updateMasterOrganization", method = RequestMethod.POST)
    public ModelAndView updateMasterOrganization(Organization organization) {
        try {
            organizationService.update(organization);
            return new ModelAndView("redirect:/Organization/showMasterOrganizationList");
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.getMessage());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * 查看组织树形结构页面
     *
     * @param masterOrganizationId
     * @return
     */
    @RequestMapping(value = "/showOrganizationTree", method = RequestMethod.GET)
    public ModelAndView showOrganizationTree(Long masterOrganizationId) {
        Map model = new HashMap<>();
        String treeStr = organizationService.getOrganizationTree(masterOrganizationId);
        model.put("treeStr", treeStr);
        return new ModelAndView("/Organization/showOrganizationTree", model);
    }

    /**
     * 新增组织
     *
     * @param parentOrganizationId
     * @param masterOrganizationId
     * @param organization
     * @return
     */
    @RequestMapping(value = "addOrganization", method = RequestMethod.POST)
    public ModelAndView addOrganization(@RequestParam("parentOrganizationId") Long parentOrganizationId,
                                        @RequestParam("masterOrganizationId") Long masterOrganizationId,
                                        Organization organization) {
        Organization parentOrganization = organizationService.get(parentOrganizationId);
        organization.setParentOrganization(parentOrganization);
        try {
            organizationService.add(organization);
            return new ModelAndView("redirect:/Organization/showOrganizationTree?masterOrganizationId=" + masterOrganizationId);
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.toString());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * 删除Organization
     *
     * @param organizationId
     * @param masterOrganizationId
     * @return
     */
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public ModelAndView deleteOrganization(@RequestParam("organizationId") Long organizationId,
                                           @RequestParam("masterOrganizationId") Long masterOrganizationId) {

        Organization organization = organizationService.get(organizationId);
        if (organization.getChildOrganizationList().size() > 0) {
            Map model = new HashMap<>();
            model.put("exception", "This organization has child organization, delete failure!");
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        } else {
            try {
                organizationService.delete(organization);
                return new ModelAndView("redirect:/Organization/showOrganizationTree?masterOrganizationId=" + masterOrganizationId);
            } catch (Exception e) {
                Map model = new HashMap<>();
                model.put("exception", e.getMessage());
                return new ModelAndView("redirect:/Common/exceptionOccurs", model);
            }
        }
    }

    /**
     * Organization编辑页面
     *
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "/editOrganization", method = RequestMethod.GET)
    public ModelAndView editOrganization(@RequestParam("organizationId") Long organizationId) {
        Map<String, Object> model = new HashMap<>();
        Organization organization = organizationService.get(organizationId);
        List<User> activeUserList = userService.getAll();
        model.put("organization", organization);
        model.put("activeUserList", activeUserList);
        return new ModelAndView("/Organization/editOrganization", model);
    }

    /**
     * Organization更新操作
     *
     * @param organization
     * @return
     */
    @RequestMapping(value = "/updateOrganization", method = RequestMethod.POST)
    public ModelAndView updateOrganization(Organization organization) {
        try {
            organizationService.update(organization);
            return new ModelAndView("redirect:/Organization/editOrganization?organizationId=" + organization.getId());
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.getMessage());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * 新增Organization Manager操作
     *
     * @param organizationId
     * @param userId
     * @param isActive
     * @return
     */
    @RequestMapping(value = "/addOrgManager", method = RequestMethod.POST)
    public ModelAndView addOrgManager(@RequestParam("organizationId") Long organizationId,
                                      @RequestParam("userId") Long userId,
                                      String isActive) {
        Organization organization = organizationService.get(organizationId);
        User user = userService.get(userId);
        OrganizationManager organizationManager = new OrganizationManager();
        organizationManager.setIsActive(isActive);
        organizationManager.setManager(user);
        organizationManager.setOrganization(organization);
        try {
            organizationManagerService.add(organizationManager);
            return new ModelAndView("redirect:/Organization/editOrganization?organizationId=" + organization.getId());
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.getMessage());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * Disable or Enable Organization Manager
     *
     * @param orgManagerId
     * @return
     */
    @RequestMapping(value = "/addOrgManager", method = RequestMethod.GET)
    public ModelAndView diableEnableOrgManager(@RequestParam("orgManagerId") Long orgManagerId) {
        OrganizationManager organizationManager = organizationManagerService.get(orgManagerId);
        String isActive = "Y".equals(organizationManager.getIsActive()) ? "N" : "Y";
        organizationManager.setIsActive(isActive);
        try {
            organizationManagerService.update(organizationManager);
            return new ModelAndView("redirect:/Organization/editOrganization?organizationId=" + organizationManager.getOrganization().getId());
        } catch (Exception e) {
            Map model = new HashMap<>();
            model.put("exception", e.getMessage());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);
        }
    }

    /**
     * 删除Organization Manager操作
     *
     * @param orgManagerId
     * @return
     */
    public ModelAndView deleteOrgManager(@RequestParam("orgManagerId") Long orgManagerId) {
        OrganizationManager organizationManager = organizationManagerService.get(orgManagerId);
        Long organizationId = organizationManager.getOrganization().getId();
        try {
            organizationManagerService.delete(organizationManager);
            return new ModelAndView("redirect:/Organization/editOrganization?organizationId=" + organizationId);
        } catch (Exception e) {
            /*Map model = new HashMap<>();
            model.put("exception", e.getMessage());
            return new ModelAndView("redirect:/Common/exceptionOccurs", model);*/
            return redirectException(e);
        }
    }
}
