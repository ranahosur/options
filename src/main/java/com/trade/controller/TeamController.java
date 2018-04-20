package com.trade.controller;

import com.trade.dao.AdminPrivilegeDao;
import com.trade.dao.TeamDao;
import com.trade.dao.UserDao;
import com.trade.dao.UserRoleDao;
import com.trade.model.AdminPrivilege;
import com.trade.model.OptionsConstants;
import com.trade.model.Team;
import com.trade.model.User;
import com.trade.service.EmailService;
import com.trade.service.UserService;
import com.trade.util.CountryUtil;
import com.trade.util.DAOUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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
public class TeamController extends  LoginRegistrationBaseController{

    private static final Logger logger = Logger.getLogger(TeamController.class);

    @Autowired
    public UserService userService;

    @Autowired
    public UserDao userDao;

    @Autowired
    public AdminPrivilegeDao adminPrivilegeDao;

    @Autowired
    public TeamDao teamDao;

    @Autowired
    EmailService emailService;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserRoleDao userRoleDao;

    @RequestMapping(value = "/addTeams", method = RequestMethod.POST)
    public String addTeam(HttpServletRequest request, HttpServletResponse response, Model model) {
        AdminPrivilege adminPrivilege = new AdminPrivilege();
        adminPrivilege.setUser(new User());
        String username = (String)request.getSession().getAttribute("username");
        adminPrivilege.setUsername(username);
        logger.debug("addSubscription username from req is "+ username);
        model.addAttribute("team",new Team());
        return "manageTeams";
    }

    @RequestMapping(value = "/saveTeam", method = RequestMethod.POST)
    public ModelAndView saveTeam(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("team") Team team) {
        String teamId = team.getTeamId();
        String username = (String)request.getSession().getAttribute("username");
        User adminUser = userService.findUserByUsername(username);
        if(teamId == null || teamId.equals("")){
            logger.debug(" team name is "+ team.getTeamName() + " email is "+ team.getTeamLogin().getEmail());
            team.setAdminId(adminUser.getUserId());
            User teamUser = new User();
            teamUser.setFirstName(team.getTeamName());
            //TODO check if email already registered and error in case
            teamUser.setEmail(team.getTeamLogin().getEmail());
            teamUser.setVerificationToken(DAOUtil.generateId());
            team.setTeamLogin(teamUser);
            userService.createTeam(team);

            sendActivationEmail( request,teamUser);
            return findModelForWelcomeAdmin(adminUser);
        }
        else{
            teamDao.updateTeam(team);
            return findModelForWelcomeAdmin(adminUser);
        }
    }

    @RequestMapping(value = "/manageTeam", method = RequestMethod.POST)
    public ModelAndView manageTeam(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("team") Team team) {

        String teamId = request.getParameter("selectTeamId");
        team = userService.findTeamByTeamId(teamId);
        ModelAndView mav = new ModelAndView("manageTeams");
        mav.addObject("team",team);

        return mav;

    }

    @RequestMapping(value = "/welcomeAdmin", method = RequestMethod.POST)
    public ModelAndView cancelTeamSave(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("team") Team team) {

        User user = userDao.findUser((String)request.getSession().getAttribute("username"));
        return findModelForWelcomeAdmin(user);

    }


    private void sendActivationEmail(HttpServletRequest request,User user) {
        String[] tokens = new String[3];
        tokens[0] = user.getFirstName();

        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String host = url.substring(0, url.indexOf(uri));
        logger.debug("sendActivationEmail to "+user.getEmail());
        logger.debug("url is "+ url);
        logger.debug("uri is "+ uri);
        logger.debug("host is "+ host);
        String link = host +  request.getContextPath()+"/registernew?token="+user.getVerificationToken();
        logger.debug("Verification URL generated is "+ link);
        tokens[1] = link;
        //TODO remove hardcoding
        tokens[2] = "ranahosur@gmail.com";
        emailService.sendEmail(mailSender,user, OptionsConstants.EMAIL_TYPE_REGISTRATION_INVITE,tokens);
    }
}
