package com.bambinos.dao;

import com.bambinos.model.Center;
import com.bambinos.model.CenterAdmin;

import java.util.List;

public interface CenterAdminDAO {

    CenterAdmin createCenterAdmin(CenterAdmin centerAdmin);
//    Center updateCenter(Center center);
//    void deleteCenter(String centerId);
    CenterAdmin findCenterAdminByCenterAdminId(String centerAdminId);
    List<CenterAdmin> findCenterAdminByCenterId(String centerId);

}
