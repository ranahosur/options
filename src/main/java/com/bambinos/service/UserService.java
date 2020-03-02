package com.bambinos.service;

import com.bambinos.model.AdminPrivilege;
import com.bambinos.model.Login;
import com.bambinos.model.Team;
import com.bambinos.model.User;

import java.util.List;

public interface UserService {

  void createAdmin(User user);

  void createAdmin(User user, String userRoleType);

  public void createAdmin(User user, String userRoleType,AdminPrivilege adminPrivilege);

  Login validateUser(Login login);

  User findUserByUsername(String username);

  void modifyUser(User user);

  void updatePassword(String username, String password);

  void createTeam(Team team);

  List<AdminPrivilege> findAllSubscriptions();

  User findUserByUserId(String userId);

  User findUserByEmail(String email);

  void registerAdmin(User user);

  User findUserByVerificationToken(String token);

  List<Team> findTeamsByAdminId(String adminId);

  Team findTeamByTeamId(String teamid);

}
