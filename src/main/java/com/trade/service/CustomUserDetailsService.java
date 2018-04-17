package com.trade.service;

import com.trade.dao.RoleDao;
import com.trade.dao.UserDao;
import com.trade.dao.UserRoleDao;
import com.trade.model.OptionsConstants;
import com.trade.model.Role;
import com.trade.model.User;
import com.trade.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by raghu.anahosur on 7/30/2017.
 */
@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserDao userdao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {

        User domainUser = userdao.findUser(login);
        UserRole userRole = userRoleDao.findUserRoleByUserId(domainUser.getUserId());
        Role role = roleDao.findRole(userRole.getRoleId());
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(
                domainUser.getUsername(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(role.getRole())
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    public List<String> getRoles(String role) {

        List<String> roles = new ArrayList<String>();

        if (role.equals(OptionsConstants.ROLE_ADMIN)) {
            roles.add("ROLE_ADMIN");
        } else if (role.equals(OptionsConstants.ROLE_PARTICIPANT)){
            roles.add("ROLE_CUSTOMER");
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}