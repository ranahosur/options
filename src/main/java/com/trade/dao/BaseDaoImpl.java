package com.trade.dao;

import java.util.UUID;

/**
 * Created by raghu.anahosur on 7/26/2017.
 */
public class BaseDaoImpl {

    protected String generateId() {

        String rawId = UUID.randomUUID().toString();
        return rawId.replace("-", "");

    }

    protected java.sql.Timestamp getCurrentTimeStamp(){
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    protected String convertBoolean(boolean active){
       return active ? "Y":"N";
    }

}
