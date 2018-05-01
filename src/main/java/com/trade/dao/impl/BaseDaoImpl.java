package com.trade.dao.impl;

import com.trade.util.DAOUtil;

import java.util.UUID;

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
