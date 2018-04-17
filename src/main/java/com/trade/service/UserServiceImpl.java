package com.trade.service;

import com.trade.dao.*;
import com.trade.model.*;
import com.trade.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.log4j.Logger;

import javax.swing.text.html.Option;
import java.sql.Date;


public class UserServiceImpl implements UserService {

  private static final Logger logger = Logger.getLogger(UserServiceImpl.class);


  @Autowired
  public UserDao userDao;

  @Autowired
  public UserRoleDao userRoleDao;

  @Autowired
  public RoleDao roleDao;

  @Autowired
  public TeamDao teamDao;

  @Autowired
  public TeamMemberDao teamMemberDao;

  @Autowired
  public AdminPrivilegeDao adminPrivilegeDao;

  @Autowired
  public PaymentServiceManager paymentServiceManager;

  public void createAdmin(User user) {
    createAdmin(user, OptionsConstants.ROLE_SUPER_ADMIN);
  }

  public void createAdmin(User user, String userRoleType){
    createAdmin(user,userRoleType,null);
  }
  public void createAdmin(User user, String userRoleType,AdminPrivilege adminPrivilege) {
    logger.debug("Information received: username "+ user.getUsername() + " firstname "+ user.getFirstName() + " lastname "+ user.getLastName() + " location "+ user.getLocation());
    userDao.register(user);
    Role role = roleDao.findRole(userRoleType);
    UserRole userRole = new UserRole();
    userRole.setUserId(user.getUserId());
    userRole.setRoleId(role.getRoleId());
    userRoleDao.createUserRole(userRole);
    logger.debug("Created user role with "+role.getRole() + " pk generated "+ userRole.getUserRoleId());

    if(adminPrivilege != null && userRoleType.equals(OptionsConstants.ROLE_ADMIN)){
      adminPrivilege.setUserId(user.getUserId());
      adminPrivilege.setActive(true);
      if(adminPrivilege.getMaxTeamCount() == 0){
        adminPrivilege.setMaxTeamCount(OptionsConstants.DEFAULT_TEAM_COUNT);
      }
      if(adminPrivilege.getMaxTeamMemberCount() == 0){
        adminPrivilege.setMaxTeamMemberCount(OptionsConstants.DEFAULT_TEAM_SIZE);
      }
      if(adminPrivilege.getTrialPeriodEndDate() == null){
        adminPrivilege.setTrialPeriodEndDate(DateUtil.addNumberOfDaysToCurrentdate(OptionsConstants.DEFAULT_TRIAL_PERIOD));
      }
      adminPrivilegeDao.createAdminPrivilege(adminPrivilege);
    }


  }

  public void createTeam(Team team) {

    User teamLogin = team.getTeamLogin();
    userDao.register(teamLogin);
    team.setActive(true);
    team.setUserId(teamLogin.getUserId());
    teamDao.createTeam(team);
    if(team.getTeamMembers() != null && team.getTeamMembers().size() > 0){
      for(TeamMember teamMember : team.getTeamMembers()){
        User user = teamMember.getUser();
        userDao.register(user);
        teamMember.setTeamId(team.getTeamId());
        teamMember.setActive(true);
        teamMember.setUserId(user.getUserId());
        teamMemberDao.createTeamMember(teamMember);
      }
    }

  }

  public User validateUser(Login login) {
    logger.debug("Entry into validateUser with usename "+login.getUsername());
    User user =  userDao.validateUser(login);
    if(user != null) {
      user.setUserPaymentServiceList(paymentServiceManager.findPaymentServiceRegistered(user.getUsername()));
    }
    return user;
  }

  public User findUser(String username) {
    logger.debug("Entry into findUser with usename "+username);
    return userDao.findUser(username);

  }

  public void modifyUser(User user) {
    logger.debug("Updating user "+ user.getUsername());
    userDao.updateUser(user);
    logger.debug("Updated user "+ user.getUsername());
  }

  public void updatePassword(String username, String password) {
    logger.debug("Updating user's password for "+ username);
    userDao.updatePassword(username, password);
    logger.debug("Updated user's password for "+ username);
  }
}
