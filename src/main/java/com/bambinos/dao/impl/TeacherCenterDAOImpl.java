package com.bambinos.dao.impl;

import com.bambinos.dao.TeacherCenterDAO;
import com.bambinos.model.TeacherCenter;
import com.bambinos.model.TeacherCenter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeacherCenterDAOImpl extends BaseDaoImpl implements TeacherCenterDAO {

    private static final Logger logger = Logger.getLogger(TeacherCenterDAOImpl.class);

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public TeacherCenter createTeacherCenter(TeacherCenter teacherCenter) {
        String sql = "insert into Teacher_Center (Teacher_Center_id, teacher_id, center_id, created_by )" +
                "values( ?, ?, ?, ? )";

        teacherCenter.setTeacherCenterId(generateId());
        logger.debug("TeacherCenter created with id "+ teacherCenter.getTeacherCenterId());
        jdbcTemplate.update(sql,  teacherCenter.getTeacherCenterId(), teacherCenter.getTeacherId(),
                teacherCenter.getCenterId(),teacherCenter.getCreatedBy());
        return teacherCenter;
    }

    @Override
    public void deleteTeacherCenter(String teacherCenterId) {
        String sql = "Delete from Teacher_Center  WHERE Teacher_Center_id = ?";
        jdbcTemplate.update(sql, teacherCenterId);

    }

    @Override
    public List<TeacherCenter> findTeacherCentersByTeacherId(String teacherId) {
        String sql = "select * from teacher_center where teacher_id = '"+teacherId+"'";

        List<TeacherCenter> centers = jdbcTemplate.query(sql, new TeacherCenterDAOImpl.TeacherCenterMapper());
        return centers;
    }

    class TeacherCenterMapper implements RowMapper<TeacherCenter> {

        public TeacherCenter mapRow(ResultSet rs, int arg1) throws SQLException {
            TeacherCenter teacherCenter = new TeacherCenter();
            teacherCenter.setTeacherId(rs.getString("teacher_id"));
            teacherCenter.setTeacherCenterId(rs.getString("center_admin_id"));
            teacherCenter.setCenterId(rs.getString("center_id"));
            teacherCenter.setCreatedBy(rs.getString("created_by"));
            teacherCenter.setCreateDate(rs.getTimestamp("create_date"));
            return teacherCenter;
        }
    }
}
