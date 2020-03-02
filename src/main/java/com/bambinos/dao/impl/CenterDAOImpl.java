package com.bambinos.dao.impl;

import com.bambinos.dao.CenterDAO;
import com.bambinos.model.Center;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CenterDAOImpl extends BaseDaoImpl implements CenterDAO {

    private static final Logger logger = Logger.getLogger(CenterDAOImpl.class);

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    
    @Override
    public Center createCenter(Center center) {
        String sql = "insert into center (center_id, center_code, landmark,  map_location, hospital_number, ambulance_number, created_by )" +
                "values( ?, ?, ?, ?, ?, ?, ? )";

        center.setCenterId(generateId());
        logger.debug("Center created with id "+ center.getCenterId());
        jdbcTemplate.update(sql,  center.getCenterId(), center.getCenterCode(),
                center.getLandMark(), center.getMapLocation(), center.getHospitalNumber(),
                center.getAmbulanceNumber(),center.getCreatedBy());
        return center;
    }

    @Override
    public Center updateCenter(Center center) {
        String sql = "update  center set  center_code = ?, landmark = ?,  map_location = ?, hospital_number = ?, ambulance_number = ?, created_by = ? where center_id = ?)" ;


        logger.debug("Center created with id "+ center.getCenterId());
        jdbcTemplate.update(sql,  center.getCenterCode(),
                center.getLandMark(), center.getMapLocation(), center.getHospitalNumber(),
                center.getAmbulanceNumber(),center.getCreatedBy() ,center.getCenterId());
        return center;
    }

    @Override
    public void deleteCenter(String centerId) {

        String sql = "Delete from center  WHERE center_id = ?";
        jdbcTemplate.update(sql, centerId);

    }

    @Override
    public List<Center> findCenters() {
        String sql = "select * from center ";

        List<Center> centers = jdbcTemplate.query(sql, new CenterDAOImpl.CenterMapper());
        return centers;
    }

    class CenterMapper implements RowMapper<Center> {

        public Center mapRow(ResultSet rs, int arg1) throws SQLException {
            Center center = new Center();
            center.setCenterId(rs.getString("center_id"));
            ;
            center.setCenterCode(rs.getString("center_code"));
            center.setLandMark(rs.getString("landmark"));
            center.setMapLocation(rs.getString("map_location"));
            center.setHospitalNumber(rs.getString("hospital_number"));
            center.setAmbulanceNumber(rs.getString("ambulance_number"));
            center.setCreatedBy(rs.getString("created_by"));
            center.setCreateDate(rs.getTimestamp("create_date"));
            return center;
        }
    }
}
