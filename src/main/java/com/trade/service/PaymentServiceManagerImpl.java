package com.trade.service;

import com.trade.dao.PaymentServiceDao;
import com.trade.dao.UserPaymentServiceDao;
import com.trade.model.PaymentService;
import com.trade.model.UserPaymentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghu.anahosur on 8/2/2017.
 */
public class PaymentServiceManagerImpl implements  PaymentServiceManager {

    private static final Logger logger = Logger.getLogger(PaymentServiceManagerImpl.class);

    @Autowired
    private PaymentServiceDao paymentServiceDao;

    @Autowired
    private UserPaymentServiceDao userPaymentServiceDao;

    public List<PaymentService> findPaymentServiceToBeRegistered(String username) {
        List<UserPaymentService> userPaymentServiceList = userPaymentServiceDao.findPaymentServiceByUsername(username);
        List<PaymentService> unregisteredPaymentServiceList = new ArrayList<PaymentService>();
        List<PaymentService> paymentServiceList = paymentServiceDao.findPaymentService();
        for(PaymentService paymentService : paymentServiceList){
            boolean registered = false;
            for(UserPaymentService userPaymentService : userPaymentServiceList){
                if(userPaymentService.getPaymentServiceId().equals(paymentService.getPaymentServiceId())){
                    registered = true;
                    break;
                }
            }
            if(!registered){
                unregisteredPaymentServiceList.add(paymentService);
            }
        }
        return unregisteredPaymentServiceList;
    }

    public UserPaymentService registerPaymentService(UserPaymentService userPaymentService) {
        logger.debug("Entry into registerPaymentService");
        userPaymentService = userPaymentServiceDao.createPaymentService(userPaymentService);
        logger.debug("Exit from registerPaymentService");
        return userPaymentService;
    }

    public List<UserPaymentService> findPaymentServiceRegistered(String username) {
        return userPaymentServiceDao.findPaymentServiceByUsername(username);
    }
}

