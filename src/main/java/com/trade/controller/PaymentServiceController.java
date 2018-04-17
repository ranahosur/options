package com.trade.controller;

import com.trade.model.PaymentService;
import com.trade.model.User;
import com.trade.model.UserPaymentService;
import com.trade.model.WaterPaymentUser;
import com.trade.service.PaymentServiceManager;
import com.trade.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.trade.model.OptionsConstants.SERVICE_STATUS_REGISTERED;

/**
 * Created by raghu.anahosur on 8/2/2017.
 */
@Controller
public class PaymentServiceController {

    private static final Logger logger = Logger.getLogger(PaymentServiceController.class);
    @Autowired
    PaymentServiceManager paymentServiceManager;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/selectService", method = RequestMethod.POST)
    public ModelAndView selectService(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Entry into selectService");
        ModelAndView mav = null;
        String sessionUsername = request.getSession().getAttribute("username").toString();
        logger.debug("Username retrieved from session is "+ sessionUsername);
        List<PaymentService> paymentServiceList = paymentServiceManager.findPaymentServiceToBeRegistered(sessionUsername);

        mav = new ModelAndView("selectService");
        mav.addObject("paymentServiceList",paymentServiceList);
        UserPaymentService userPaymentService = new UserPaymentService();
        userPaymentService.setUsername(sessionUsername);
        mav.addObject("userPaymentService",userPaymentService);

        logger.debug("Exit from selectService");
        return mav;
    }


    @RequestMapping(value = "/waterPayment", method = RequestMethod.POST)
    public ModelAndView addWaterBill(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Entry into addWaterBill");
        ModelAndView mav = null;
        String sessionUsername = request.getSession().getAttribute("username").toString();
        logger.debug("Username retrieved from session is "+ sessionUsername);
        String paymentServiceId = request.getParameter("paymentServiceId");
        String userAction = request.getParameter("userAction");
        WaterPaymentUser waterPaymentUser = new WaterPaymentUser();
        waterPaymentUser.setUsername(sessionUsername);
        waterPaymentUser.setPaymentServiceId(paymentServiceId);
        if(StringUtils.isBlank(userAction)){

            mav = new ModelAndView("addWaterPayment");
            mav.addObject("waterPaymentUser",waterPaymentUser);
        }
        else {
            if(userAction.equals("submit")) {
                String consumerNo = request.getParameter("consumerNo");
                if(isConsumerNoValid(consumerNo)){
                    mav = new ModelAndView("addWaterPayment");
                    UserPaymentService userPaymentService = new UserPaymentService();
                    userPaymentService.setPaymentServiceId(paymentServiceId);
                    User user = userService.findUser(sessionUsername);
                    userPaymentService.setUserId(user.getUserId());
                    userPaymentService.setExternalId(consumerNo);
                    userPaymentService.setStatus(SERVICE_STATUS_REGISTERED);
                    userPaymentService = paymentServiceManager.registerPaymentService(userPaymentService);
                    mav.addObject("waterPaymentUser",waterPaymentUser);
                    logger.debug("Created new service registration with consumer no "+ consumerNo+ " PK gen "+ userPaymentService.getUserPaymentServiceId());
                    return mav;
                }
                else{

                }
                mav = new ModelAndView("addWaterPayment");
                mav.addObject("waterPaymentUser",waterPaymentUser);
                mav.addObject("message","Consumer Number does not exist");
            }
        }

        logger.debug("Exit from addWaterBill");
        return mav;
    }

    //TODO replace this with REST call
    private boolean isConsumerNoValid(String consumerNo){
        if("123456789".equals(consumerNo) || "111111111".equals(consumerNo) || "222222222".equals(consumerNo)){
            logger.debug("valid consumer no");
            return true;
        }
        else{
            logger.debug("invalid consumer no");
            return false;
        }
    }
}
