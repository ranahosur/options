package com.trade.service;

import com.trade.model.AdminPrivilege;
import com.trade.model.Login;
import com.trade.model.Team;
import com.trade.model.User;

import java.util.List;

public interface UserService {

  void createAdmin(User user);

  void createAdmin(User user, String userRoleType);

  public void createAdmin(User user, String userRoleType,AdminPrivilege adminPrivilege);

  User validateUser(Login login);

  User findUserByUsername(String username);

  void modifyUser(User user);

  void updatePassword(String username, String password);

  void createTeam(Team team);

  List<AdminPrivilege> findAllSubscriptions();

  User findUserByUserId(String userId);

}
