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
    private static final String mmddyyyy = "MM/dd/yyyy";


    public static java.sql.Date parseYYYYMMdd(String s)
    {
        return parseDateString(s,yyyymmdd);
    }

    public static java.sql.Date parseDateString(String s,String format)
    {
        java.sql.Date retDate = null;
        try {
            Date date = new SimpleDateFormat(format).parse(s);
            retDate = new java.sql.Date(date.getTime());

        } catch (ParseException e) {
            logger.error("Exception while parsing the date in DateFromat yyyymmdd" + e.getMessage());
            retDate = null;
        }
        return retDate;
    }

    public static java.sql.Date parseMMDDYYYY(String s)
    {

        Date date = parseDateString(s,mmddyyyy);
        if(date != null){
            return new java.sql.Date (date.getTime());
        }
        else{
            return null;
        }
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

    public static java.sql.Date currentDate(){
        Calendar utilDate = Calendar.getInstance();
        utilDate.set(Calendar.HOUR,0);
        utilDate.set(Calendar.MINUTE,0);
        utilDate.set(Calendar.SECOND,0);
        return new java.sql.Date(utilDate.getTimeInMillis());
    }

    public static String formatDate(java.sql.Date date, String format){
        if (date != null) {
            Date utilDate = new Date(date.getTime());
            return formatDate(utilDate,format);
        }
        return null;
    }

    public static String formatDate(Date date, String format){
        if(date != null){
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(format);
            return DATE_FORMAT.format(date);
        }
        return null;
    }

}
