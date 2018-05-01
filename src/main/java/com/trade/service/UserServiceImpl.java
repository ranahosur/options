package com.trade.service;

import com.trade.dao.*;
import com.trade.model.*;
import com.trade.util.DAOUtil;
import com.trade.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


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
    logger.debug("Information received: username "+ user.getUsername() + " firstname "+ user.getFirstName() + " lastname "+ user.getLastName());
    user.setActive("N");
    userDao.create(user);
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
    userDao.create(teamLogin);
    team.setActive(true);
    team.setUserId(teamLogin.getUserId());
    teamDao.createTeam(team);
    Role role = roleDao.findRole(OptionsConstants.ROLE_TEAM);
    UserRole userRole = new UserRole();
    userRole.setUserId(teamLogin.getUserId());
    userRole.setRoleId(role.getRoleId());
    userRoleDao.createUserRole(userRole);
    if(team.getTeamMembers() != null && team.getTeamMembers().size() > 0){
      for(TeamMember teamMember : team.getTeamMembers()){
        User user = teamMember.getUser();
        userDao.create(user);
        teamMember.setTeamId(team.getTeamId());
        teamMember.setActive(true);
        teamMember.setUserId(user.getUserId());
        teamMemberDao.createTeamMember(teamMember);
      }
    }

  }

  public List<AdminPrivilege> findAllSubscriptions() {
    return adminPrivilegeDao.findAdminPrivilegeAll();
  }

  public User validateUser(Login login) {
    logger.debug("Entry into validateUser with usename "+login.getUsername());
    User user =  userDao.validateUser(login);
    setRole(user);

    return user;
  }

  private void setRole(User user){
    if(user != null) {
      UserRole userRole = userRoleDao.findUserRoleByUserId(user.getUserId());
      user.setUserRole(userRole);
    }
  }

  public User findUserByUsername(String username) {
    logger.debug("Entry into findUserByUsername with usename "+username);

    User user =  userDao.findUser(username);
    setRole(user);
    return user;
  }

  public User findUserByUserId(String userId) {
    logger.debug("Entry into findUserByUsername with userId "+userId);
    User user =  userDao.findUserByUserId(userId);
    setRole(user);
    return user;
  }

  public User findUserByEmail(String email) {
    logger.debug("Entry into findUserByEmail with email "+email);

    User user =  userDao.findUserByEmail(email);
    setRole(user);
    return user;
  }

  public User findUserByVerificationToken(String token) {
    logger.debug("Entry into findUserByVerificationToken with token "+token);
    return userDao.findUserByVerificationToken(token);
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

  public void registerAdmin(User user) {
    logger.debug("Updating user's password/username for "+ user.getEmail());
    user.setVerificationToken(DAOUtil.generateId());
    userDao.registerUser(user);
    logger.debug("Updated user's password/username for "+ user.getEmail());
  }

    public List<Team> findTeamsByAdminId(String adminId) {
        List<Team> teams = teamDao.findTeamByAdminId(adminId);
        if(teams == null){
            teams = new ArrayList<Team>();
        }
        for(Team team : teams){
            populateTeamDetails(team);
        }

        return teams;
    }

    public Team findTeamByTeamId(String teamid){
      Team team = teamDao.findTeamByTeamId(teamid);
      populateTeamDetails(team);
      return team;
    }

    private void populateTeamDetails(Team team) {
      User teamUser = userDao.findUserByUserId(team.getUserId());
      if(teamUser.getUsername() != null && ("Y").equals(teamUser.getActive())){
        team.setStatus(OptionsConstants.TEAM_STATUS_ACTIVE);
      }
      else if(teamUser.getUsername() != null && ("N").equals(teamUser.getActive())){
        team.setStatus(OptionsConstants.TEAM_STATUS_SUSPENDED);
      }
      else {
        team.setStatus(OptionsConstants.TEAM_STATUS_NOT_REGISTERED);
      }
      team.setEmail(teamUser.getEmail());
      team.setTeamLogin(teamUser);
      List<TeamMember> teamMembers = teamMemberDao.findTeamMemberByTeamId(team.getTeamId());
      if(teamMembers == null){
        teamMembers = new ArrayList<TeamMember>();
      }
      team.setTeamCount(teamMembers.size());
      team.setTeamMembers(teamMembers);
    }
}
