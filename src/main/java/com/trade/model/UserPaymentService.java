package com.trade.model;

/**
 * Created by raghu.anahosur on 8/2/2017.
 */
public class UserPaymentService {
    private String userPaymentServiceId;
    private String userId;
    private String paymentServiceId;
    private String status;
    private String externalId;
    private String paymentServiceName;
    private String username;

    public String getUserPaymentServiceId() {
        return userPaymentServiceId;
    }

    public void setUserPaymentServiceId(String userPaymentServiceId) {
        this.userPaymentServiceId = userPaymentServiceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentServiceId() {
        return paymentServiceId;
    }

    public void setPaymentServiceId(String paymentServiceId) {
        this.paymentServiceId = paymentServiceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getPaymentServiceName() {
        return paymentServiceName;
    }

    public void setPaymentServiceName(String paymentServiceName) {
        this.paymentServiceName = paymentServiceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
