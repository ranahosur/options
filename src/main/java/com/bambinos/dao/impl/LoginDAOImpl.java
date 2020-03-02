package com.bambinos.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.bambinos.dao.LoginDAO;
import com.bambinos.model.Login;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bambinos.model.Login;


public class LoginDAOImpl extends BaseDaoImpl implements LoginDAO {

  private static final Logger logger = Logger.getLogger(LoginDAOImpl.class);
  @Autowired
  DataSource datasource;

  @Autowired
  JdbcTemplate jdbcTemplate;

  public Login create(Login login) {

    String sql = "insert into Login(Login_id, username, password, role_type, login_type, parent_table, parent_id,  active )" +
            "values( ?, ?, ?, ?, ?, ?, ?, ? )";

    login.setLoginId(generateId());
    logger.debug("Login created with Loginid "+login.getLoginId() + " for username "+ login.getUsername());
    jdbcTemplate.update(sql,  login.getLoginId(),login.getUsername(), login.getPassword(), login.getRoleType(),
        login.getLoginType(), login.getParentTable(), login.getParentId(),login.getActive());
    return login;
  }

  public Login validateLogin(Login login) {

    String sql = "select * from Login where email='" + login.getUsername() + "' and password='" + login.getPassword()
        + "'";

    List<Login> logins = jdbcTemplate.query(sql, new LoginMapper());

    return logins.size() > 0 ? logins.get(0) : null;
  }

  public Login findLogin(String loginname) {

    String sql = "select * from Login where username='" + loginname +"'";

    List<Login> logins = jdbcTemplate.query(sql, new LoginMapper());

    return logins.size() > 0 ? logins.get(0) : null;
  }

    public Login findLoginByLoginId(String LoginId) {
        String sql = "select * from Login where login_Id = '" + LoginId +"'";

        List<Login> logins = jdbcTemplate.query(sql, new LoginMapper());

        return logins.size() > 0 ? logins.get(0) : null;
    }

//  public Login findLoginByEmail(String email) {
//    String sql = "select * from Login where email='" + email +"'";
//
//    List<Login> logins = jdbcTemplate.query(sql, new LoginMapper());
//
//    return logins.size() > 0 ? logins.get(0) : null;
//  }

//  public Login findLoginByVerificationToken(String token) {
//    String sql = "select * from Login where verification_token ='" + token +"'";
//
//    List<Login> logins = jdbcTemplate.query(sql, new LoginMapper());
//
//    return logins.size() > 0 ? logins.get(0) : null;
//
//  }

  public void updateLogin(Login login) {
    String sql = "UPDATE Login SET username = ? , password = ? , role_type = ? , login_type, = ?  parent_table = ? , parent_id = ? ,   active = ?  WHERE Login_id = ?";
    jdbcTemplate.update(sql,  login.getUsername(), login.getPassword(), login.getRoleType(),
            login.getLoginType(), login.getParentTable(), login.getParentId(),login.getActive(), login.getLoginId());

  }

  public void updatePassword(String Loginname, String password) {
    String sql = "UPDATE Login SET password = ?  WHERE username = ?";
    jdbcTemplate.update(sql, password,Loginname);
  }

//  public void registerLogin(Login Login) {
//    String sql = "UPDATE Login SET password = ?  , Loginname = ?, verification_token = ? where email = ? ";
//    jdbcTemplate.update(sql, login.getPassword(),login.getLoginname(),login.getVerificationToken(), login.getEmail());
//  }

  public void updateLoginActive(Login login) {
    String sql = "UPDATE Login SET active = ? where username = ? ";
    jdbcTemplate.update(sql, login.getActive(), login.getUsername());
  }

  public void deleteLogin(String LoginId) {
    String sql = "Delete from Login  WHERE Login_id = ?";
    jdbcTemplate.update(sql, LoginId);
  }
}

class LoginMapper implements RowMapper<Login> {

  public Login mapRow(ResultSet rs, int arg1) throws SQLException {
    Login login = new Login();
    login.setLoginId(rs.getString("Login_id"));;
    login.setUsername(rs.getString("username"));
    login.setPassword(rs.getString("password"));
    login.setRoleType(rs.getString("role_type"));
    login.setLoginType(rs.getString("login_type"));
    login.setParentTable(rs.getString("parent_table"));
    login.setParentId(rs.getString("parent_id"));
    login.setActive(rs.getString("active"));
    login.setCreateDate(rs.getTimestamp("create_date"));
    return login;
  }
}