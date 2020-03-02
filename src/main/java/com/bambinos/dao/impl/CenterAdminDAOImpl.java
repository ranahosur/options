package com.bambinos.dao.impl;

import com.bambinos.dao.CenterAdminDAO;
import com.bambinos.model.Center;
import com.bambinos.model.CenterAdmin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CenterAdminDAOImpl extends BaseDaoImpl implements CenterAdminDAO {


    private static final Logger logger = Logger.getLogger(CenterAdminDAOImpl.class);

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public CenterAdmin createCenterAdmin(CenterAdmin centerAdmin) {
        String sql = "insert into center_admin (center_admin_id, center_id, admin_id, role_type,created_by,active )" +
                "values( ?, ?, ?, ?,? , ? )";

        centerAdmin.setCenterAdminId(generateId());
        logger.debug("CenterAdmin created with id "+ centerAdmin.getCenterAdminId());
        jdbcTemplate.update(sql,  centerAdmin.getCenterAdminId(), centerAdmin.getCenterId(),
                centerAdmin.getAdminId(),centerAdmin.getRoleType(),centerAdmin.getCreatedBy(),centerAdmin.getActive());
        return centerAdmin;
    }

    @Override
    public CenterAdmin findCenterAdminByCenterAdminId(String centerAdminId) {
        String sql = "select * from center_admin where center_admin_id = '"+centerAdminId+"'";

        List<CenterAdmin> centers = jdbcTemplate.query(sql, new CenterAdminDAOImpl.CenterAdminMapper());
        if(centers != null && centers.size() > 0){
            return centers.get(0);
        };
        return null;
    }

    @Override
    public List<CenterAdmin> findCenterAdminByCenterId(String centerId) {
        String sql = "select * from center_admin where center_id = '"+centerId+"'";

        List<CenterAdmin> centers = jdbcTemplate.query(sql, new CenterAdminDAOImpl.CenterAdminMapper());
        return centers;
    }

    class CenterAdminMapper implements RowMapper<CenterAdmin> {

        public CenterAdmin mapRow(ResultSet rs, int arg1) throws SQLException {
            CenterAdmin center = new CenterAdmin();
            center.setCenterId(rs.getString("center_id"));
            center.setCenterAdminId(rs.getString("center_admin_id"));
            center.setAdminId(rs.getString("admin_id"));
            center.setRoleType(rs.getString("role_type"));
            center.setActive(rs.getString("active"));
            center.setCreatedBy(rs.getString("created_by"));
            center.setCreateDate(rs.getTimestamp("create_date"));
            return center;
        }
    }
}
