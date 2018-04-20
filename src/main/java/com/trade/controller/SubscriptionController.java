package com.trade.controller;

import com.trade.dao.AdminPrivilegeDao;
import com.trade.dao.UserDao;
import com.trade.model.AdminPrivilege;
import com.trade.model.OptionsConstants;
import com.trade.model.User;
import com.trade.service.UserService;
import com.trade.util.CountryUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubscriptionController extends LoginRegistrationBaseController {
    private static final Logger logger = Logger.getLogger(SubscriptionController.class);

    @Autowired
    public UserService userService;

    @Autowired
    public UserDao userDao;

    @Autowired
    public AdminPrivilegeDao adminPrivilegeDao;

//This can remain like a POC of how to send object with values to UI using both ModelAndView as well as Model
    @RequestMapping(value = "/manageSubscription", method = RequestMethod.POST)
    public String manageSubscription(HttpServletRequest request, HttpServletResponse response,Model model) {
        String selectAdminPrivilegeId = request.getParameter("selectAdminPrivilegeId");
        logger.debug("Entry into manageSubscription with selectAdminPrivilegeId "+ selectAdminPrivilegeId);
        AdminPrivilege adminPrivilege = adminPrivilegeDao.findAdminPrivilegeByAdminPrivilegeId(selectAdminPrivilegeId);
        if(adminPrivilege != null){
            logger.debug("Retrieved adminPrivilege with id "+ adminPrivilege.getAdminPrivilegeId());
            User adminUser = userService.findUserByUserId(adminPrivilege.getUserId());
            adminPrivilege.setUser(adminUser);
        }
        ModelAndView mav = new ModelAndView("manageSubscription");
//        mav.addObject("user", userService.findUserByUsername(request.getParameter("username")));
//        mav.addObject("adminPrivilege",adminPrivilege);
        model.addAttribute("adminPrivilege",adminPrivilege);
//        model.addAttribute("user", userService.findUserByUsername(request.getParameter("username")));
        model.addAttribute("countries", CountryUtil.findAllCountries());
//        request.setAttribute("adminPrivilege",adminPrivilege);

        return "manageSubscription";
    }

//    @RequestMapping(value = "/manageSubscription", method = RequestMethod.POST)
//    public ModelAndView manageSubscription(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("adminPrivilege") AdminPrivilege adminPrivilege) {
//        String selectAdminPrivilegeId = request.getParameter("selectAdminPrivilegeId");
//        logger.debug("Entry into manageSubscription with selectAdminPrivilegeId "+ selectAdminPrivilegeId);
//        adminPrivilege = adminPrivilegeDao.findAdminPrivilegeByAdminPrivilegeId(selectAdminPrivilegeId);
//        if(adminPrivilege != null){
//            logger.debug("Retrieved adminPrivilege with id "+ adminPrivilege.getAdminPrivilegeId());
//            User adminUser = userService.findUserByUsername(adminPrivilege.getUsername());
//            adminPrivilege.setUser(adminUser);
//        }
//        ModelAndView mav = new ModelAndView("manageSubscription");
//        mav.addObject("user", userService.findUserByUsername(request.getParameter("username")));
//        mav.addObject("countries", CountryUtil.findAllCountries());
//        mav.addObject("adminPrivilege",adminPrivilege);
//        return mav;
//    }

    @RequestMapping(value = "/saveSubscription", method = RequestMethod.POST)
    public ModelAndView saveSubscription(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("adminPrivilege") AdminPrivilege adminPrivilege) {
        logger.debug("Entry into saveSubscription");
        String selectAdminPrivilegeId = adminPrivilege.getAdminPrivilegeId();
        logger.debug("Entry into saveSubscription with selectAdminPrivilegeId "+ selectAdminPrivilegeId);
        AdminPrivilege existAdminPrivilege = null;
        if(selectAdminPrivilegeId != null && !selectAdminPrivilegeId.equals("")) {
            User uiUser = adminPrivilege.getUser();
            logger.debug("firstname "+uiUser.getFirstName() + " lastname "+ uiUser.getLastName() + " zip "+ uiUser.getZip());
            existAdminPrivilege = adminPrivilegeDao.findAdminPrivilegeByAdminPrivilegeId(selectAdminPrivilegeId);
            if(existAdminPrivilege != null){
                //TODO these need to be made editable on UI and then the following can be removed
                adminPrivilege.setTrialPeriodEndDate(existAdminPrivilege.getTrialPeriodEndDate());
                adminPrivilegeDao.updateAdminPrivilege(adminPrivilege);
                logger.debug("updated adminprivilege with id "+ adminPrivilege.getAdminPrivilegeId());
                User existUser = userService.findUserByUserId(existAdminPrivilege.getUserId());
                existUser.setPhoneNumber(uiUser.getPhoneNumber());
                existUser.setFirstName(uiUser.getFirstName());
                existUser.setLastName(uiUser.getLastName());
                existUser.setCountryName(uiUser.getCountryName());
                existUser.setState(uiUser.getState());
                existUser.setCity(uiUser.getCity());
                existUser.setEmail(uiUser.getEmail());
                existUser.setAddressLine1(uiUser.getAddressLine1());
                existUser.setAddressLine2(uiUser.getAddressLine2());
                existUser.setZip(uiUser.getZip());
                userDao.updateUser(existUser);
                logger.debug("updated user with id "+ existUser.getUserId());
            }

        }
        else{
            userService.createAdmin(adminPrivilege.getUser(), OptionsConstants.ROLE_ADMIN,adminPrivilege);
            logger.debug("created adminprivilege with id "+ adminPrivilege.getAdminPrivilegeId());
        }

        return findModelForWelcomeSuperAdmin(request);
    }

    @RequestMapping(value = "/welcomeSAdmin", method = RequestMethod.POST)
    public ModelAndView cancelSave(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("adminPrivilege") AdminPrivilege adminPrivilege) {

        return findModelForWelcomeSuperAdmin(request);

    }



    @RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
    public String addSubscription(HttpServletRequest request, HttpServletResponse response,Model model) {
        AdminPrivilege adminPrivilege = new AdminPrivilege();
        adminPrivilege.setUser(new User());
        String username = request.getParameter("username");
        adminPrivilege.setUsername(username);
        logger.debug("addSubscription username from req is "+ username);
        ModelAndView mav = new ModelAndView("manageSubscription");
        model.addAttribute("adminPrivilege",adminPrivilege);
        model.addAttribute("user", userService.findUserByUsername(request.getParameter("username")));
        model.addAttribute("countries", CountryUtil.findAllCountries());

        return "manageSubscription";
    }
}
