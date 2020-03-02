package com.bambinos.model;

/**
 * Created by raghu.anahosur on 8/3/2017.
 */
public class WaterPaymentUser {
    private String waterPaymentUserId;
    private String userId;
    private String userPaymentServiceId;
    private String paymentServiceId;
    private String consumerNo;
    private String username;
    private String userAction;

    public String getWaterPaymentUserId() {
        return waterPaymentUserId;
    }

    public void setWaterPaymentUserId(String waterPaymentUserId) {
        this.waterPaymentUserId = waterPaymentUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPaymentServiceId() {
        return userPaymentServiceId;
    }

    public void setUserPaymentServiceId(String userPaymentServiceId) {
        this.userPaymentServiceId = userPaymentServiceId;
    }

    public String getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(String consumerNo) {
        this.consumerNo = consumerNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public String getPaymentServiceId() {
        return paymentServiceId;
    }

    public void setPaymentServiceId(String paymentServiceId) {
        this.paymentServiceId = paymentServiceId;
    }
}
