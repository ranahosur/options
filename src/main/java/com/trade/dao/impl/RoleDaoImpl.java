package com.trade.dao.impl;

import com.trade.dao.RoleDao;
import com.trade.model.Role;
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
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public com.trade.model.Role findRole(String role) {
        String sql = "select * from role where role='" + role +"'";
        List<Role> roles = jdbcTemplate.query(sql, new RoleMapper());
        return roles.size() > 0 ? roles.get(0) : null;
    }

    public Role findRoleByRoleId(String roleId) {
        String sql = "select * from role where role_id ='" + roleId +"'";
        List<Role> roles = jdbcTemplate.query(sql, new RoleMapper());
        return roles.size() > 0 ? roles.get(0) : null;
    }
}

class RoleMapper implements RowMapper<Role> {

    public Role mapRow(ResultSet rs, int arg1) throws SQLException {
        Role role = new Role();
        role.setRoleId(rs.getString("role_id"));;
        role.setRole(rs.getString("role"));
        return role;
    }
}
