package com.trade.controller;

import com.trade.dao.AdminPrivilegeDao;
import com.trade.dao.TeamDao;
import com.trade.dao.UserDao;
import com.trade.dao.UserRoleDao;
import com.trade.model.*;
import com.trade.service.ParticipantService;
import com.trade.service.UserService;
import com.trade.util.CountryUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LoginRegistrationBaseController {


    private static final Logger logger = Logger.getLogger(LoginRegistrationBaseController.class);

    @Autowired
    UserService userService;

    @Autowired
    public UserDao userDao;

    @Autowired
    public AdminPrivilegeDao adminPrivilegeDao;

    @Autowired
    public UserRoleDao userRoleDao;

    @Autowired
    public ParticipantService participantService;

    protected ModelAndView showWelcomePage(HttpServletRequest request,User user) {
        UserRole userRole = userRoleDao.findUserRoleByUserId(user.getUserId());
        user.setUserRole(userRole);
        ModelAndView mav = null;
        request.getSession().setAttribute("username",user.getUsername());
        if(OptionsConstants.ROLE_SUPER_ADMIN.equals(user.getUserRole().getRole())) {
            mav = findModelForWelcomeSuperAdmin(request);
        }
        else if(OptionsConstants.ROLE_ADMIN.equals(user.getUserRole().getRole())) {
            mav = findModelForWelcomeAdmin(user);
        }
        else {
            mav = findModelForWelcomeParticipant(user,request);
        }
        mav.addObject("user",user);
        mav.addObject("firstname", user.getFirstName());
        mav.addObject("lastname", user.getLastName());
        mav.addObject("houseNo", user.getAddressLine1());
        mav.addObject("street", user.getAddressLine2());
        mav.addObject("city", user.getCity());
        mav.addObject("username", user.getUsername());
        return mav;
    }

    protected ModelAndView findModelForWelcomeSuperAdmin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("welcomeSAdmin");
        mav.addObject("user", userService.findUserByUsername( ""+request.getSession().getAttribute("username")));
        mav.addObject("countries", CountryUtil.findAllCountries());

        List<AdminPrivilege> adminPrivileges = userService.findAllSubscriptions();
        if(adminPrivileges == null){
            logger.debug("There are no existing subscriptions found");
            adminPrivileges = new ArrayList<AdminPrivilege>();
        }
        else{
            for(AdminPrivilege adminPriv : adminPrivileges){
                User adminUser = userDao.findUser(adminPriv.getUsername());
                adminPriv.setUser(adminUser);
            }
            logger.debug("There are  existing subscriptions found = count is "+ adminPrivileges.size());
        }

        mav.addObject("countries",CountryUtil.findAllCountries());
        mav.addObject("adminPrivileges",adminPrivileges);
        return mav;
    }

    protected ModelAndView findModelForWelcomeAdmin(User user) {
        ModelAndView mav = new ModelAndView("welcomeAdmin");
        AdminPrivilege adminPrivilege = adminPrivilegeDao.findAdminPrivilegeByUserId(user.getUserId());
        List<Team> teams = userService.findTeamsByAdminId(user.getUserId());
        if(teams == null){
            teams = new ArrayList<Team>();
        }
        mav.addObject("adminPrivilege",adminPrivilege);
        mav.addObject("teams",teams);
        return mav;
    }

    protected ModelAndView findModelForWelcomeParticipant(HttpServletRequest request) {
        String username = (String)request.getSession().getAttribute("username");
        User user = userService.findUserByUsername(username);
        return findModelForWelcomeParticipant(user,request);

    }

    protected ModelAndView findModelForWelcomeParticipant(User user,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("welcomeParticipant");
        Participant participant = participantService.findParticipantTransactions(user.getUserId());
        logger.debug("participant id is "+  participant.getParticipantId());
        List<ParticipantTransaction> participantTransactions = participant.getParticipantTransactions();
        if(participantTransactions == null){
            participantTransactions = new ArrayList<ParticipantTransaction>();
        }
        else{
            logger.debug("Number of transactions is "+participantTransactions.size());
        }
        mav.addObject("participant",participant);
        mav.addObject("participantTransactions",participantTransactions);
        mav.addObject("screenMode",request.getParameter("screenMode"));
        return mav;
    }

}
