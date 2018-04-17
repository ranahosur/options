package com.trade.dao;

import com.trade.model.PaymentService;

import java.util.List;

/**
 * Created by raghu.anahosur on 8/2/2017.
 */
public interface PaymentServiceDao {

    List<PaymentService> findPaymentService();
}
