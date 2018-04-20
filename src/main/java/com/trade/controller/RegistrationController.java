package com.trade.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trade.dao.UserDao;
import com.trade.dao.UserRoleDao;
import com.trade.model.Login;
import com.trade.model.OptionsConstants;
import com.trade.model.UserRole;
import com.trade.service.EmailService;
import com.trade.util.DAOUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.trade.model.User;
import com.trade.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static com.trade.model.OptionsConstants.*;

@Controller
public class RegistrationController extends LoginRegistrationBaseController  {

  private static final Logger logger = Logger.getLogger(RegistrationController.class);
  @Autowired
  public UserService userService;

  @Autowired
  public UserDao userDao;

  @Autowired
  public UserRoleDao userRoleDao;

  @Autowired
  EmailService emailService;

  @Autowired
  JavaMailSender mailSender;


  @RequestMapping(value = "/registernew", method = RequestMethod.POST)
  public ModelAndView showRegisterNew(HttpServletRequest request, HttpServletResponse response) {
    return showRegister(request,response);
  }

  @RequestMapping(value = "/registernew", method = RequestMethod.GET)
  public ModelAndView showRegisterNewGet(HttpServletRequest request, HttpServletResponse response) {
    return showRegister(request,response);
  }

  @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
  public ModelAndView forgotPasswordPost(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("user") User user) {
    return showForgotPassword(request,response,user);
  }

  @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
  public ModelAndView forgotPasswordGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
    return showForgotPassword(request,response,user);
  }

  @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
  public ModelAndView modifyPasswordPost(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("user") User user) {
    return showModifyPassword(request,response,user);
  }

  @RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
  public ModelAndView modifyPasswordGet(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
    return showModifyPassword(request,response,user);
  }

  private ModelAndView showForgotPassword(HttpServletRequest request, HttpServletResponse response,User user) {
    ModelAndView mav = new ModelAndView("forgotPassword");

      if(user.getEmail() == null || user.getEmail().equals("")) {
        mav.addObject("user", new User());
        return mav;
      }
      else{
        User existUser = userDao.findUserByEmail(user.getEmail());
        if(existUser == null){
          mav.addObject("message", "Email is not registered");
          return mav;
        }
        else if(existUser.getUsername() == null){
          mav.addObject("message", "Click on New User to register");
          return mav;
        }
        else{
          mav.addObject("user", existUser);
          mav.addObject("verified","true");
          mav.addObject("message", "Email has been sent with instructions on resetting password");
          existUser.setVerificationToken(DAOUtil.generateId());
          userDao.updateUser(existUser);
          sendForgotPasswordEmail(request,existUser);
          return mav;
        }
      }


  }

  private ModelAndView showModifyPassword(HttpServletRequest request, HttpServletResponse response,User user) {
    ModelAndView mav = new ModelAndView("modifyPassword");
    String verificationToken = request.getParameter("token");
    if(user.getNewpassTxt() != null && !user.getNewpassTxt().equals("")){
      user.setPassword(user.getNewpassTxt());
      userDao.updatePassword(user.getUsername(),user.getPassword());
      mav.addObject("passwordChanged", "true");
      mav.addObject("message","Password successfully changed");
      return mav;
    }
    else {
      if (verificationToken == null || verificationToken.equals("")) {
        return redirectToLogin("Invalid Forgot Password Request", "failed");
      } else {
        User existUser = userDao.findUserByVerificationToken(verificationToken);
        if (existUser == null) {
          return redirectToLogin("Invalid Forgot Password Request", "failed");
        } else {
          mav.addObject("user", existUser);
          mav.addObject("verified", "true");
          return mav;
        }
      }
    }
  }


  private ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("registernew");
    String verificationToken = request.getParameter("token");
    if(verificationToken == null || verificationToken.equals("")) {
      mav.addObject("user", new User());
    }
    else{
      User existUser = userDao.findUserByVerificationToken(verificationToken);
      if(existUser == null){
        return redirectToLogin("Invalid Registration Request","failed");
      }
      else{
        if(existUser.getUsername() != null && "Y".equals(existUser.getActive()) ){
          return redirectToLogin("Verification already done, Please login","complete");
        }
        else{
          //No more activation needed via link
          existUser.setActive("Y");
          userDao.updateUserActive(existUser);
          mav.addObject("user", existUser);
          mav.addObject("verified","true");
          return mav;

        }
      }
    }

    return mav;
  }

  @RequestMapping(value = "/welcome", method = RequestMethod.GET)
  public ModelAndView showDashboardGet(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("welcome");
    User user = userService.findUserByUsername(request.getSession().getAttribute("username").toString());
    mav.addObject("user", user);
    return mav;
  }

  @RequestMapping(value = "/welcome", method = RequestMethod.POST)
  public ModelAndView showDashboardPost(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("welcome");
    User user = userService.findUserByUsername(request.getSession().getAttribute("username").toString());
    mav.addObject("user", user);
    return mav;
  }


  @RequestMapping(value = "/registerProcessNew", method = RequestMethod.POST)
  public ModelAndView addUserNew(HttpServletRequest request, HttpServletResponse response,
                              @ModelAttribute("user") User user) {

    logger.debug("Entry into addUserNew with username "+user.getUsername() + " email "+ user.getEmail());
    //If username is empty, the user just entered email and submitted
    if(user.getUsername() == null || user.getUsername().equals("")){
      User existUser = userService.findUserByEmail(user.getEmail());
      if(existUser != null ){
        if(existUser.getUsername() != null){
          //already registered
          logger.error("User already registered with username "+ existUser.getUsername());
          return redirectWithError(user,"Already registered, please login");
        }
        else{
          logger.debug("User has not registered with email "+ existUser.getEmail()+ " redirecting for registration");
          ModelAndView mav = new ModelAndView("registernew");
          mav.addObject("user", existUser);
          mav.addObject("verified","true");
          return mav;
        }
      }
      else{
        logger.error("User not in system with email "+ user.getEmail());
        return redirectWithError(user,"Please contact us to register");
      }

    }
    else{

      User existUser = userService.findUserByUsername(user.getUsername());
      if(existUser != null) {
        logger.error("username already exists " + user.getUsername());
        ModelAndView mav = new ModelAndView("registernew");
        mav.addObject("user", user);
        mav.addObject("verified","true");
        mav.addObject("message", "Username already exists, please choose another");

        return mav;
        }
      }
//for registration the password entered online is stored on a readonly variable
      user.setPassword(user.getNewpassTxt());

    userService.registerAdmin(user);
    user = userService.findUserByEmail(user.getEmail());

    if(!"Y".equals(user.getActive())) {
      sendActivationEmail(request, user);
      ModelAndView mav = new ModelAndView("registernew");
      mav.addObject("user", user);
      mav.addObject("verified","true");
      mav.addObject("registered","true");
      mav.addObject("message", "Registration successful, as last step, please activate account from the email sent");
      return mav;
    }
    else{
      return showWelcomePage(request,user);
    }


  }

  @RequestMapping(value = "/verifyRegistration", method = RequestMethod.GET)
  public ModelAndView verifyRegistration(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = null;

    String token = request.getParameter("token");
    if(token == null || token.equals("")){
      //error message
     return redirectToLogin("Invalid Verification Request for registration","failed");
    }
    else{
      User user = userService.findUserByVerificationToken(token);

      if(user != null) {
        if("Y".equals(user.getActive())){
          //already activated once,
          return redirectToLogin("Verification already done, Please login","complete");

        }
        user.setActive("Y");
        userDao.updateUserActive(user);

        return showWelcomePage(request,user);

      }
      else{
        //failed token verification
        return redirectToLogin("Invalid Verification Request for registration","failed");
      }

    }

  }

  private ModelAndView redirectWithError(User user,String message) {
    ModelAndView mav = new ModelAndView("registernew");
    mav.addObject("user", user);
    mav.addObject("message",message);//);
    return mav;
  }

  private ModelAndView redirectToLogin(String message, String verificationResult) {
    ModelAndView mav = new ModelAndView("loginnew");
    mav.addObject("message", message);
    mav.addObject("verification",verificationResult);
    mav.addObject("login", new Login());
    return mav;
  }

  private void sendActivationEmail(HttpServletRequest request,User user) {
    String[] tokens = new String[4];
    tokens[0] = user.getFirstName();
    tokens[1] = user.getLastName();
    StringBuffer url = request.getRequestURL();
    String uri = request.getRequestURI();
    String host = url.substring(0, url.indexOf(uri));
    logger.debug("url is "+ url);
    logger.debug("uri is "+ uri);
    logger.debug("host is "+ host);
    String link = host +  request.getContextPath()+"/verifyRegistration?token="+user.getVerificationToken();
    logger.debug("Verification URL generated is "+ link);
    tokens[2] = link;
    //TODO remove hardcoding
    tokens[3] = "ranahosur@gmail.com";
    emailService.sendEmail(mailSender,user, OptionsConstants.EMAIL_TYPE_REGISTRATION,tokens);
  }

  private void sendForgotPasswordEmail(HttpServletRequest request,User user) {
    String[] tokens = new String[3];
    tokens[0] = user.getEmail();

    StringBuffer url = request.getRequestURL();
    String uri = request.getRequestURI();
    String host = url.substring(0, url.indexOf(uri));

    String link = host +  request.getContextPath()+"/modifyPassword?token="+user.getVerificationToken();
    logger.debug("Verification URL generated is "+ link);
    tokens[1] = link;
    //TODO remove hardcoding
    tokens[2] = "ranahosur@gmail.com";
    emailService.sendEmail(mailSender,user, OptionsConstants.EMAIL_TYPE_FORGOT_PASSWORD,tokens);
  }
}
