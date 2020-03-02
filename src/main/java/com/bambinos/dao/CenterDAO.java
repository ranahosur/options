package com.bambinos.dao;

import com.bambinos.model.Center;

import java.util.List;

public interface CenterDAO {

    Center createCenter(Center center);
    Center updateCenter(Center center);
    void deleteCenter(String centerId);
    List<Center> findCenters();
}
