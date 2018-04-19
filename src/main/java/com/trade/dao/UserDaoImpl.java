package com.trade.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.trade.model.Login;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.trade.model.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

  private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
  @Autowired
  DataSource datasource;

  @Autowired
  JdbcTemplate jdbcTemplate;

  public void register(User user) {

    String sql = "insert into user(user_id, username, password, first_name, last_name, email, address_line1, address_line2, " +
            " city,  state, zip, country_code,country_name,phone_number )" +
            "values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    user.setUserId(generateId());
    logger.debug("User created with userid "+user.getUserId() + " for username "+ user.getUsername());
    jdbcTemplate.update(sql, new Object[] { user.getUserId(),user.getUsername(), user.getPassword(), user.getFirstName(),
        user.getLastName(), user.getEmail(), user.getAddressLine1(),user.getAddressLine2(),user.getCity(),  user.getState(),
            user.getZip(),user.getCountryCode(), user.getCountryName(),  user.getPhoneNumber()});
  }

  public User validateUser(Login login) {

    String sql = "select * from user where username='" + login.getUsername() + "' and password='" + login.getPassword()
        + "'";

    List<User> users = jdbcTemplate.query(sql, new UserMapper());

    return users.size() > 0 ? users.get(0) : null;
  }

  public User findUser(String username) {

    String sql = "select * from user where username='" + username +"'";

    List<User> users = jdbcTemplate.query(sql, new UserMapper());

    return users.size() > 0 ? users.get(0) : null;
  }

    public User findUserByUserId(String userId) {
        String sql = "select * from user where user_Id='" + userId +"'";

        List<User> users = jdbcTemplate.query(sql, new UserMapper());

        return users.size() > 0 ? users.get(0) : null;
    }

    public void updateUser(User user) {
    String sql = "UPDATE user SET first_name =?, last_name = ? , email = ?, address_line1 = ?, "
            + "address_line2 = ?,  city = ? , zip = ? , country_code = ? , country_name = ? , phone_number = ?  WHERE user_id = ?";
    jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddressLine1(), user.getAddressLine2(),
            user.getCity(), user.getZip(),user.getCountryCode(),user.getCountryName(), user.getPhoneNumber(), user.getUserId());

  }

  public void updatePassword(String username, String password) {
    String sql = "UPDATE user SET password = ?  WHERE username = ?";
    jdbcTemplate.update(sql, password,username);
  }

  public void deleteUser(String userId) {
    String sql = "Delete from user  WHERE user_id = ?";
    jdbcTemplate.update(sql, userId);
  }
}

class UserMapper implements RowMapper<User> {

  public User mapRow(ResultSet rs, int arg1) throws SQLException {
    User user = new User();
    user.setUserId(rs.getString("user_id"));;
    user.setUsername(rs.getString("username"));
    user.setPassword(rs.getString("password"));
    user.setFirstName(rs.getString("first_name"));
    user.setLastName(rs.getString("last_name"));
    user.setEmail(rs.getString("email"));
    user.setAddressLine1(rs.getString("address_line1"));
    user.setAddressLine2(rs.getString("address_line2"));
    user.setState(rs.getString("state"));
    user.setCountryCode(rs.getString("country_code"));
    user.setCountryName(rs.getString("country_name"));
    user.setCity(rs.getString("city"));
    user.setZip(rs.getString("zip"));
    user.setPhoneNumber(rs.getString("phone_number"));


    return user;
  }
}