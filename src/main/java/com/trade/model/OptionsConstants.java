package com.trade.model;

import java.util.List;

/**
 * Created by raghu.anahosur on 7/30/2017.
 */
public class OptionsConstants {

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_PARTICIPANT = "participant";
    public static final String ROLE_TEAM = "team";
    public static final String ROLE_SUPER_ADMIN = "superadmin";
    public static final String SERVICE_STATUS_REGISTERED = "REGISTERED";
    public static final String SERVICE_STATUS_APPROVED = "APPROVED";
    public static final String SERVCIE_STATUS_DISABLED = "DISABLED";
    public static final Integer DEFAULT_TEAM_COUNT = 5;
    public static final Integer DEFAULT_TEAM_SIZE = 5;
    public static final Integer DEFAULT_TRIAL_PERIOD = 30;

    public static final String EMAIL_TYPE_REGISTRATION = "REGISTRATION";
    public static final String EMAIL_TYPE_REGISTRATION_INVITE = "REGISTRATION_INVITE";
    public static final String EMAIL_TYPE_FORGOT_PASSWORD = "FORGOT_PASSWORD";

    public static final String TEAM_STATUS_ACTIVE = "Active";
    public static final String TEAM_STATUS_SUSPENDED = "Suspended";
    public static final String TEAM_STATUS_NOT_REGISTERED = "Not Registered";

    public static final String OPTION_TYPE_CALL = "Call";
    public static final String OPTION_TYPE_PUT = "Put";

    public static final String TRANSACTION_STATUS_NEW = "New";

    public static final String TRANSACTION_RESULT_LOSS = "Loss";

    public static  enum MARKET_CSV_MAPPING {
        SYMBOL,STOCK_NAME,STOCK_PRICE,EXPIRY_DATE,CALL_OPEN_INT,CALL_OPEN_INT_CHANGE,CALL_VOLUME,CALL_IMPLIED_VOLATILITY,CALL_LAST_TRADED_PRICE,CALL_NET_CHANGE,
        CALL_BID_QTY,CALL_BID_PRICE,CALL_ASK_PRICE,CALL_ASK_QTY,STRIKE_PRICE,PUT_BID_QTY,PUT_BID_PRICE,PUT_ASK_PRICE,PUT_ASK_QTY,PUT_NET_CHANGE,PUT_LAST_TRADED_PRICE,
        PUT_IMPLIED_VOLATILITY,PUT_VOLUME,PUT_OPEN_INT_CHANGE,PUT_OPEN_INT
    };

    public static final String DATE_FORMAT_YMD = "yyyyMMdd";
    public static final String DATE_FORMAT_DDMONYYYY = "ddMMMYYYY";



}
