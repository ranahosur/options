package com.bambinos.dao;

import com.bambinos.model.UserPaymentService;

import java.util.List;

/**
 * Created by raghu.anahosur on 8/2/2017.
 */
public interface UserPaymentServiceDao {

    UserPaymentService createPaymentService(UserPaymentService userPaymentService);
    List<UserPaymentService> findPaymentServiceByUsername(String username);
}
