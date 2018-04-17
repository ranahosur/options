package com.trade.service;

import com.trade.model.PaymentService;
import com.trade.model.UserPaymentService;

import java.util.List;

/**
 * Created by raghu.anahosur on 8/2/2017.
 */
public interface PaymentServiceManager {

    List<PaymentService> findPaymentServiceToBeRegistered(String username);

    UserPaymentService registerPaymentService(UserPaymentService userPaymentService);

    List<UserPaymentService> findPaymentServiceRegistered(String username);

}
