package com.trade.controller;

import com.trade.model.Login;
import com.trade.model.User;
import com.trade.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
public class ModifyPasswordController {
    private static final Logger logger = Logger.getLogger(ModifyPasswordController.class);
    @Autowired
    UserService userService;

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    public ModelAndView modifyProfile(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("user") User user) {
        logger.debug("Entry into modifyProfile");
        ModelAndView mav = null;
        String sessionUsername = request.getSession().getAttribute("username").toString();
        logger.debug("Username retrieved from session is "+ sessionUsername);
        logger.debug("Username retrieved from request model is "+ user.getUsername());
        Login login = new Login();
        String newPassword = user.getNewpassTxt();


        if(StringUtils.hasText(newPassword)) {
            login.setUsername(sessionUsername);
            login.setPassword(user.getPassword());
            user = userService.validateUser(login);
            if (null != user) {
                logger.debug("User is validated , updating password " + user.getUsername());
                userService.updatePassword(login.getUsername(), newPassword);
                mav = new ModelAndView("welcome");
                mav.addObject("user", user);
                mav.addObject("message", "Password has been changed successfully");
            }
            else{
                mav = new ModelAndView("modifyPassword");
                logger.warn("Password does not match existing one");
                mav.addObject("message", "Password does not match existing one");

            }

        } else {
            //this is just  the first entry to controller, so display the screen with no messaging
            mav = new ModelAndView("modifyPassword");
            mav.addObject("user", userService.findUserByUsername(sessionUsername));

        }

        logger.debug("Exit from modifyProfile");
        return mav;
    }
}
