package com.trade.dao;

import com.trade.model.AdminPrivilege;

import java.util.List;

public interface AdminPrivilegeDao {

    AdminPrivilege createAdminPrivilege(AdminPrivilege adminPrivilege);

    public AdminPrivilege findAdminPrivilegeByUserId(String teamId);

    public void updateAdminPrivilege(AdminPrivilege teamMember);

    public void deleteAdminPrivilege(AdminPrivilege teamMember);

    public List<AdminPrivilege> findAdminPrivilegeAll();

    public AdminPrivilege findAdminPrivilegeByAdminPrivilegeId(String adminPrivilegeId);

}
