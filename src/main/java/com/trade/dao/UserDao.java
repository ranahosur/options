package com.trade.dao;

import com.trade.model.Login;
import com.trade.model.User;

public interface UserDao {

  void register(User user);

  User validateUser(Login login);

  User findUser(String username);

  void updateUser(User user);

  void updatePassword(String username, String password);

  void deleteUser(String userId);

  User findUserByUserId(String userId);

}
