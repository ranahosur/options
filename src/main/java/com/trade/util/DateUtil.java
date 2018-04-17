package com.trade.util;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by raghu.anahosur on 7/26/2017.
 */
public class DateUtil {
    private static final Logger logger = Logger.getLogger(DateUtil.class);
    private static final String yyyymmdd = "yyyyMMdd";
    public static Date parseYYYYMMdd(String s)
    {
        Date retDate = null;
        try {
            retDate = new SimpleDateFormat(yyyymmdd).parse(s);
            // return s == null ? null : yyyymmdd.parse(s);
        } catch (ParseException e) {
            logger.error("Exception while parsing the date in DateFromat yyyymmdd" + e.getMessage());
            retDate = null;
        }
        return retDate;
    }

    public static java.sql.Date convertToSqlDate(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }

    public static java.sql.Date addNumberOfDays(java.sql.Date date,int noOfDays){
        Calendar utilDate = Calendar.getInstance();
        utilDate.setTimeInMillis(date.getTime());
        utilDate.set(Calendar.HOUR,0);
        utilDate.set(Calendar.MINUTE,0);
        utilDate.set(Calendar.SECOND,0);
        utilDate.add(Calendar.DAY_OF_YEAR,noOfDays);
        return new java.sql.Date(utilDate.getTimeInMillis());

    }

    public static java.sql.Date addNumberOfDaysToCurrentdate(int noOfDays){
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        return addNumberOfDays(currentDate,noOfDays);
    }

}
