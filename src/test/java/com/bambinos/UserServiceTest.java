package com.bambinos;

import static org.junit.Assert.assertEquals;

import com.bambinos.dao.*;
import com.bambinos.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bambinos.service.UserService;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@ContextConfiguration(locations = { "classpath:/bambinos/config/user-beans.xml" })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

  @Autowired
  UserService userService;

  @Autowired
  public LoginDAO loginDAO;

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


  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testCreateSuperAdmin() {

    String username = "superadmin";
//    User dUser = loginDAO.findUser(username);
//    if(dUser != null) {
//      loginDAO.deleteUser(dUser.getUserId());
//    }
    User user = new User();
    System.out.println("test");
    user.setEmail("superadmin@test.com");
    user.setFirstName("Raghuram");
    user.setLastName("R");
    user.setUsername(username);
    user.setPassword("test123");

    userService.createAdmin(user);

    User dbUser = null;//loginDAO.findUser(username);
    dbUser.setAddressLine1("London Parkway");
    dbUser.setAddressLine2("House 27");
    dbUser.setCity("Dublin");
    dbUser.setState("QS");
    dbUser.setCountryCode("UK");
    dbUser.setCountryName("United Kingdom");
    dbUser.setPhoneNumber("239054344");
//    loginDAO.updateUser(dbUser);



  }

  @Test
  public void testFindAll() {
    List<AdminPrivilege> adminPrivileges =  userService.findAllSubscriptions();
    for (AdminPrivilege adminPrivilege : adminPrivileges){
      System.out.println(adminPrivilege.getMaxTeamCount());
    }
  }

  @Test
  public void testCreateAdmin() {

    String username  = "admin002";
    User dUser = userService.findUserByUsername(username);
    if(dUser != null) {
      AdminPrivilege adminPrivilege = adminPrivilegeDao.findAdminPrivilegeByUserId(dUser.getUserId());
      if (adminPrivilege != null) {
        adminPrivilegeDao.deleteAdminPrivilege(adminPrivilege);
      }
      userRoleDao.deleteUserRole(dUser.getUserId());
//      loginDAO.deleteUser(dUser.getUserId());
    }
    User user = new User();

    user.setEmail(username+"@test.com");
    user.setFirstName("John");
    user.setLastName("McDonald");
    user.setUsername(username);
    user.setPassword("anahosur");
    user.setAddressLine1("207 ashford parkway");
    user.setAddressLine2("apt 7");
    user.setCity("Atlanta");
    user.setState("GA");
    user.setCountryCode("US");
    user.setCountryName("USA");
    user.setPhoneNumber("735999034");

    userService.createAdmin(user, OptionsConstants.ROLE_ADMIN,new AdminPrivilege());


  }

  @Test
  public void testCreateAdminWithTeam() {

    String username  = "admin003";
    User dUser = userService.findUserByUsername(username);
    if(dUser != null) {
      AdminPrivilege adminPrivilege = adminPrivilegeDao.findAdminPrivilegeByUserId(dUser.getUserId());
      if (adminPrivilege != null) {
        adminPrivilegeDao.deleteAdminPrivilege(adminPrivilege);
      }
      userRoleDao.deleteUserRole(dUser.getUserId());
//      loginDAO.deleteUser(dUser.getUserId());
      List<Team> teams = teamDao.findTeamByAdminId(dUser.getUserId());
      if(teams != null && teams.size() > 0 ){
        for(Team team : teams) {
          List<TeamMember> teamMembers = teamMemberDao.findTeamMemberByTeamId(team.getTeamId());
          if(teamMembers != null && teamMembers.size() > 0){
            for(TeamMember teamMember : teamMembers){
              teamMemberDao.deleteTeamMember(teamMember);
            }
          }
        }
      }

    }
    User user = new User();

    user.setEmail(username+"@test.com");
    user.setFirstName("Cindy");
    user.setLastName("Crawford");
    user.setUsername(username);
    user.setPassword("test008");
    user.setAddressLine1("217 ashford parkway");
    user.setAddressLine2("apt 17");
    user.setCity("Dublin");
    user.setState("GA");
    user.setCountryCode("US");
    user.setCountryName("USA");
    user.setPhoneNumber("262004024");
    userService.createAdmin(user, OptionsConstants.ROLE_ADMIN,new AdminPrivilege());

    Team team = new Team();
    team.setAdminId(user.getUserId());
    team.setTeamName("team001");
    User teamLogin = new User();
    teamLogin.setUsername(team.getTeamName());
    teamLogin.setPassword(team.getTeamName());
    teamLogin.setFirstName(team.getTeamName());
    team.setTeamLogin(teamLogin);

    TeamMember teamMember1 = new TeamMember();
    User tMUser = new User();
    String tm = "teammember001";
    tMUser.setEmail(tm+"@test.com");
    tMUser.setUsername(tm);
    tMUser.setFirstName(tm);
    teamMember1.setUser(tMUser);

    TeamMember teamMember2 = new TeamMember();
    User tm2User = new User();
    String tm2 = "teammember002";
    tm2User.setEmail(tm2+"@test.com");
    teamMember2.setUser(tm2User);
    tm2User.setFirstName(tm2);
    team.setTeamMembers(new ArrayList<TeamMember>());
    team.getTeamMembers().add(teamMember1);
    team.getTeamMembers().add(teamMember2);
    userService.createTeam(team);

    team.setTeamName("newtest002");
    teamDao.updateTeam(team);
    teamMember1.setActive(false);
    teamMemberDao.updateTeamMember(teamMember1);
    AdminPrivilege adminPrivilege = adminPrivilegeDao.findAdminPrivilegeByUserId(user.getUserId());
    adminPrivilege.setMaxTeamCount(10);
    adminPrivilege.setMaxTeamMemberCount(8);
    adminPrivilege.setTrialPeriodEndDate(new Date(System.currentTimeMillis()));
    adminPrivilegeDao.updateAdminPrivilege(adminPrivilege);


  }

  @Test
  public void testValidateUser() {

    Login login = new Login();
//    login.setEmail("raghu");
    login.setPassword("anahosur");

//    User user = userService.validateUser(login);
//    String rawId = UUID.randomUUID().toString();
//    System.out.println(rawId.replace("-", ""));
//    rawId = UUID.randomUUID().toString();
//    System.out.println(rawId.replace("-", ""));
//
//    assertEquals("raghu", user.getFirstName());
  }

}
