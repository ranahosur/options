package com.bambinos.dao;

import com.bambinos.model.Login;

public interface LoginDAO {

  Login create(Login login);

  Login validateLogin(Login login);

  Login findLogin(String Loginname);

  void updateLogin(Login Login);

  void updatePassword(String Loginname, String password);

  void deleteLogin(String LoginId);

  Login findLoginByLoginId(String LoginId);

//  Login findLoginByEmail(String email);

//  void registerLogin(Login Login);

//  Login findLoginByVerificationToken(String token);

  void updateLoginActive(Login Login);

}
