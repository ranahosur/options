package com.bambinos.dao.impl;

import com.bambinos.util.DAOUtil;

/**
 * Created by raghu.anahosur on 7/26/2017.
 */
public class BaseDaoImpl {

    protected String generateId() {

        return DAOUtil.generateId();

    }

    protected java.sql.Timestamp getCurrentTimeStamp(){
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    protected String convertBoolean(boolean active){
       return active ? "Y":"N";
    }



}
