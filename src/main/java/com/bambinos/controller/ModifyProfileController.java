package com.bambinos.controller;

import com.bambinos.model.User;
import com.bambinos.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by raghu.anahosur on 7/27/2017.
 */
@Controller
public class ModifyProfileController {

    private static final Logger logger = Logger.getLogger(ModifyProfileController.class);
    @Autowired
    UserService userService;

    @RequestMapping(value = "/modifyProfile", method = RequestMethod.POST)
    public ModelAndView modifyProfile(HttpServletRequest request, HttpServletResponse response,
                                        @ModelAttribute("user") User user) {
        logger.debug("Entry into modifyProfile");
        ModelAndView mav = null;
        String sessionUsername = request.getSession().getAttribute("username").toString();
        logger.debug("Username retrieved from session is "+ sessionUsername);
        logger.debug("Username retrieved from request model is "+ user.getUsername());
        user = userService.findUserByUsername(user.getUsername());

        if (null != user) {
            logger.debug("User is validated , redirecting to Welcome for modifyProfile "+ user.getFirstName() + " lastname "+ user.getLastName() + " houseNo "+ user.getAddressLine1() + " street "+ user.getAddressLine2() + " city "+ user.getCity());
            mav = new ModelAndView("modifyProfile");
            mav.addObject("user",user);

        } else {
            mav = new ModelAndView("loginnew");
            logger.warn("User authentication failed, redirecting login page");
            mav.addObject("message", "Username or Password is wrong!!");
        }

        logger.debug("Exit from modifyProfile");
        return mav;
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public ModelAndView updateProfile(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("user") User user) {
        logger.debug("Entry into updateProfile");
        ModelAndView mav = null;
        String sessionUsername = request.getSession().getAttribute("username").toString();
        logger.debug("Username retrieved from session is "+ sessionUsername);
        logger.debug("Username retrieved from request model is "+ user.getUsername());
        userService.modifyUser(user);


        mav = new ModelAndView("welcome");
        mav.addObject("user",user);
        mav.addObject("message","Profile Update Successfully");
        mav.addObject("firstname", user.getFirstName());
        mav.addObject("lastname", user.getLastName());
        mav.addObject("houseNo", user.getAddressLine1());
        mav.addObject("street", user.getAddressLine2());
        mav.addObject("city", user.getCity());
        mav.addObject("username", user.getUsername());

        logger.debug("Exit from updateProfile");
        return mav;
    }

}
