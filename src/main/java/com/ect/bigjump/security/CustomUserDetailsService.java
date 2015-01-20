package com.ect.bigjump.security;

import com.ect.bigjump.domain.Role;
import com.ect.bigjump.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户信息服务类,Spring Security接口,用于加载用户权限
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-20
 */
@Component(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.ect.bigjump.domain.User cuser = null;
        try {
            cuser = userService.getByUserName(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserDetails user = null;
        if (cuser == null) {
            throw new UsernameNotFoundException("User:" + s + " not exist!");
        }
        if (cuser != null) {
            user = new org.springframework.security.core.userdetails.User(s, cuser.getPassword(), true,
                    true,
                    true,
                    true, findUserAuthorities(cuser));
        }

        return user;
    }

    /**
     * 获取用户的权限
     *
     * @param user
     * @return
     */
    @SuppressWarnings("deprecation")
    public Collection<GrantedAuthority> findUserAuthorities(com.ect.bigjump.domain.User user) {
        List<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();
        List<Role> roles = user.getRoles();
        System.out.println(roles.size());
        for (Role role : roles) {
            autthorities.add(new GrantedAuthorityImpl(role.getRoleCode()));
        }
        return autthorities;
    }

}
