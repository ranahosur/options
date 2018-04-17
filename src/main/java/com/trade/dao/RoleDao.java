package com.trade.dao;

import com.trade.model.Role;

/**
 * Created by raghu.anahosur on 7/30/2017.
 */
public interface RoleDao {
    Role findRole(String role);
    Role findRoleByRoleId(String roleId);
}
