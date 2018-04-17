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

    String sql = "insert into user(user_id, username, password, first_name, last_name, date_of_birth, gender, email, house_no, street, location" +
            ", city,  state, pin_code, residence_phone, mobile_phone, office_phone, category )" +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    user.setUserId(generateId());
    logger.debug("User created with userid "+user.getUserId() + " for username "+ user.getUsername());
    jdbcTemplate.update(sql, new Object[] { user.getUserId(),user.getUsername(), user.getPassword(), user.getFirstName(),
        user.getLastName(), user.getDateOfBirth(),user.getGender(),user.getEmail(), user.getHouseNo(),user.getStreet(),user.getLocation(),user.getCity(),  user.getState(),
            user.getPinCode(),user.getResidencePhone(),user.getMobilePhone(),user.getOfficePhone(),user.getCategory()});
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

  public void updateUser(User user) {
    String sql = "UPDATE user SET first_name =?, last_name = ? , email = ?, house_no = ?, "
            + "street = ?, location = ? , city = ? , pin_code = ? , residence_phone = ? , mobile_phone = ? , office_phone = ?  WHERE username = ?";
    jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getHouseNo(), user.getStreet(),
           user.getLocation(), user.getCity(), user.getPinCode(), user.getResidencePhone(), user.getMobilePhone(), user.getOfficePhone(), user.getUsername());

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
    user.setHouseNo(rs.getString("house_no"));
    user.setStreet(rs.getString("street"));
    user.setState(rs.getString("state"));
    user.setLocation(rs.getString("location"));
    user.setCity(rs.getString("city"));
    user.setPinCode(rs.getString("pin_code"));
    user.setResidencePhone(rs.getString("residence_phone"));
    user.setMobilePhone(rs.getString("mobile_phone"));
    user.setOfficePhone(rs.getString("office_phone"));

    return user;
  }
}