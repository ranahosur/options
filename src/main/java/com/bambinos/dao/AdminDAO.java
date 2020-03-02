package com.bambinos.dao;

import com.bambinos.model.Admin;

import java.util.List;

public interface AdminDAO {

    //not needed for now
    //TODO implement later
//    Admin createAdmin(Admin admin);
//    Admin updateAdmin(Admin admin);

    Admin findAdminByAdminId(String adminId);
}
