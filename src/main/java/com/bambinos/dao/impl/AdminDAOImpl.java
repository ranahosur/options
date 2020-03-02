package com.bambinos.dao.impl;

import com.bambinos.dao.AdminDAO;
import com.bambinos.model.Admin;
import com.bambinos.model.Admin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDAOImpl extends BaseDaoImpl implements AdminDAO {

    private static final Logger logger = Logger.getLogger(AdminDAOImpl.class);

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public Admin findAdminByAdminId(String adminId) {
        String sql = "select * from admin where admin_id = '"+adminId+"'";

        List<Admin> admins = jdbcTemplate.query(sql, new AdminDAOImpl.AdminMapper());
        return admins.get(0);
    }

    class AdminMapper implements RowMapper<Admin> {

        public Admin mapRow(ResultSet rs, int arg1) throws SQLException {
            Admin admin = new Admin();
            admin.setFirstName(rs.getString("first_name"));
            admin.setAdminId(rs.getString("center_admin_id"));
            admin.setLastName(rs.getString("last_name"));
            admin.setCreateDate(rs.getTimestamp("create_date"));
            return admin;
        }
    }
}
