package com.trade.model;

/**
 * Created by raghu.anahosur on 8/2/2017.
 */
public class PaymentService {

    private String paymentServiceId;
    private String paymentServiceName;
    private String paymentServiceCode;

    public String getPaymentServiceId() {
        return paymentServiceId;
    }

    public void setPaymentServiceId(String paymentServiceId) {

        this.paymentServiceId = paymentServiceId;
    }

    public String getPaymentServiceName() {

        return paymentServiceName;
    }

    public void setPaymentServiceName(String paymentServiceName) {

        this.paymentServiceName = paymentServiceName;
    }

    public String getPaymentServiceCode() {
        return paymentServiceCode;
    }

    public void setPaymentServiceCode(String paymentServiceCode) {
        this.paymentServiceCode = paymentServiceCode;
    }
}
