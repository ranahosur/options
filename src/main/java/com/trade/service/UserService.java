package com.trade.service;

import com.trade.model.AdminPrivilege;
import com.trade.model.Login;
import com.trade.model.Team;
import com.trade.model.User;

public interface UserService {

  void createAdmin(User user);

  void createAdmin(User user, String userRoleType);

  public void createAdmin(User user, String userRoleType,AdminPrivilege adminPrivilege);

  User validateUser(Login login);

  User findUser(String username);

  void modifyUser(User user);

  void updatePassword(String username, String password);

  void createTeam(Team team);

}
