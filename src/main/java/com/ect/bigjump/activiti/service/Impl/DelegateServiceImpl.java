package com.ect.bigjump.activiti.service.Impl;

import com.ect.bigjump.activiti.service.DelegateService;
import com.ect.bigjump.domain.Organization;
import com.ect.bigjump.domain.OrganizationManager;
import com.ect.bigjump.domain.User;
import com.ect.bigjump.service.*;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 任务委派 Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Service("delegateService")
public class DelegateServiceImpl implements DelegateService {

    @Resource(name = "lookupValueService")
    private LookupValueService lookupValueService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "organizationService")
    private OrganizationService organizationService;

    @Resource(name = "documentMasterService")
    private DocumentMasterService documentMasterService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delegateByLookup(DelegateTask task, String lookupTypeCode, String lookupCode) throws Exception {
        User user = lookupValueService.getUserByTypeCodeAndCode(lookupTypeCode, lookupCode);

        if (user != null) {
            task.setAssignee(user.getId().toString());
            //documentMasterService.setCurrentAssigneeByProcInstId(task.getProcessInstanceId(), user.getId().toString());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delegateByRole(DelegateTask task, String roleCode) throws Exception {
        List<User> userList = roleService.getUserListByRoleCode(roleCode);

        //当查询出唯一用户时,直接委派任务
        if (!userList.isEmpty()) {
            task.setAssignee(getUserIdsWithComma(userList));

            // documentMasterService.setCurrentCandiatesByProcInstId(task.getProcessInstanceId(), userIdSet);
        } else{ //当未设置User时,进行相关处理
            //Todo section
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delegateByUser(DelegateTask task, String userName) throws Exception {
        User user = userService.getByUserName(userName);
        if (user != null) {
            task.setAssignee(user.getId().toString());
            //documentMasterService.setCurrentAssigneeByProcInstId(task.getProcessInstanceId(), user.getId().toString());
        } else {
            //TODO
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delegateToOrgManager(DelegateTask task, String orgCode) throws Exception {

        Organization organization = organizationService.getByCode(orgCode);
        if (organization != null) {
            List<OrganizationManager> organizationManagerList = organization.getActiveOrgManager();
            if (organizationManagerList.isEmpty()) {
                //todo
            } else {
                Set<User> userSet = new HashSet<>();
                for(OrganizationManager organizationManager:organizationManagerList){
                    userSet.add(organizationManager.getManager());
                }
                task.setAssignee(getUserIdsWithComma(userSet));
            }
        } else {
            //todo
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delegateToUserManager(DelegateTask task, String userId) throws Exception {
        Long userIdLong;
        try {
            userIdLong = Long.parseLong(userId);
            User user = userService.get(userIdLong);
            List<OrganizationManager> organizationManagerList = user.getOrganization().getActiveOrgManager();
            if (organizationManagerList.isEmpty()) {
                //todo
            } else {
                Set<User> userSet = new HashSet<>();
                for(OrganizationManager organizationManager:organizationManagerList){
                    userSet.add(organizationManager.getManager());
                }
                task.setAssignee(getUserIdsWithComma(userSet));
            }
        } catch (Exception e) {
            //todo
        }

    }

    /**
     * 将User Id以逗号隔开连接成String
     *
     * @param users
     * @return
     */
    public String getUserIdsWithComma(Collection<User> users) {
        if (!users.isEmpty()) {
            String userIds = "";
            for (User user : users) {
                if ("".equals(userIds)) {
                    userIds = user.getId().toString();
                } else {
                    userIds = userIds + "," + user.getId().toString();
                }
            }
            return userIds;
        } else {
            return null;
        }
    }
}
