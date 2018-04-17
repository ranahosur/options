package com.trade.dao;

import com.trade.model.Role;
import com.trade.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by raghu.anahosur on 7/30/2017.
 */
public class UserRoleDaoImpl extends BaseDaoImpl implements UserRoleDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createUserRole(UserRole userRole) {
        String sql = "insert into user_role(user_role_id,user_id, role_id )" +
                "values(?, ?, ? )";

        userRole.setUserRoleId(generateId());

        jdbcTemplate.update(sql, new Object[] { userRole.getUserRoleId(),  userRole.getUserId(),userRole.getRoleId()});
    }

    public UserRole findUserRoleByUserId(String userId) {
        String sql = "select * from user_role where user_id ='" + userId +"'";
        List<UserRole> roles = jdbcTemplate.query(sql, new UserRoleMapper());
        return roles.size() > 0 ? roles.get(0) : null;
    }

    public void deleteUserRole(String userId) {
        String sql = "delete from user_role WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }
}

class UserRoleMapper implements RowMapper<UserRole> {

    public UserRole mapRow(ResultSet rs, int arg1) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setRoleId(rs.getString("role_id"));
        userRole.setUserRoleId(rs.getString("user_role_id"));
        userRole.setUserId(rs.getString("user_id"));
        return userRole;
    }
}

