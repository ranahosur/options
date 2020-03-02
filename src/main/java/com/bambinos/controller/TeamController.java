package com.bambinos.controller;

import com.bambinos.dao.AdminPrivilegeDao;
import com.bambinos.dao.TeamDao;
import com.bambinos.dao.LoginDAO;
import com.bambinos.dao.UserRoleDao;
import com.bambinos.model.AdminPrivilege;
import com.bambinos.model.OptionsConstants;
import com.bambinos.model.Team;
import com.bambinos.model.User;
import com.bambinos.service.EmailService;
import com.bambinos.service.UserService;
import com.bambinos.util.DAOUtil;
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


public class TeamController extends  LoginRegistrationBaseController{


}
