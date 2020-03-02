package com.bambinos.controller;

import com.bambinos.dao.AdminDAO;
import com.bambinos.dao.AdminPrivilegeDao;
import com.bambinos.dao.LoginDAO;
import com.bambinos.dao.UserRoleDao;
import com.bambinos.model.*;
import com.bambinos.service.ParticipantService;
import com.bambinos.service.UserService;
import com.bambinos.util.CountryUtil;
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
    public LoginDAO loginDAO;

    @Autowired
    public AdminPrivilegeDao adminPrivilegeDao;

    @Autowired
    public UserRoleDao userRoleDao;

    @Autowired
    public ParticipantService participantService;

    @Autowired
    public AdminDAO adminDAO;

    protected ModelAndView showWelcomePage(HttpServletRequest request,Login login) {

        ModelAndView mav = null;
        Admin admin = null;
        request.getSession().setAttribute("username",login.getUsername());
        if(OptionsConstants.ROLE_ADMIN.equals(login.getRoleType())) {
            mav = findModelForWelcomeSuperAdmin(request);
            admin = adminDAO.findAdminByAdminId(login.getParentId());
            mav.addObject("admin",admin);
            mav.addObject("firstname", admin.getFirstName());
            mav.addObject("lastname", admin.getLastName());
        }
//        else if(OptionsConstants.ROLE_TEACHER.equals(login.getRoleType())) {
//            mav = findModelForWelcomeAdmin(user);
//        }
//        else if(OptionsConstants.ROLE_PARENT.equals(user.getUserRole().getRole())) {
//            mav = findModelForWelcomeParticipant(user,request);
//        }
//        else if(OptionsConstants.ROLE_CENTER_MANAGER.equals(user.getUserRole().getRole())) {
//            mav = findModelForWelcomeParticipant(user,request);
//        }
        else{
            //TODO no role assigned
            mav = new ModelAndView();
        }

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
//                User adminUser = loginDAO.findLogin(adminPriv.getUsername());
//                adminPriv.setUser(adminUser);
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
//        user.setParticipant(participant);
        mav.addObject("participant",participant);
        mav.addObject("participantTransactions",participantTransactions);
        mav.addObject("screenMode",request.getParameter("screenMode"));
        return mav;
    }

}
