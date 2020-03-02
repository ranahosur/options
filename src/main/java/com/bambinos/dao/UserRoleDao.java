package com.bambinos.dao;

import com.bambinos.model.UserRole;

/**
 * Created by raghu.anahosur on 7/30/2017.
 */
public interface UserRoleDao {

    void createUserRole(UserRole userRole);
    UserRole findUserRoleByUserId(String userId);
    void deleteUserRole(String userId);

}
