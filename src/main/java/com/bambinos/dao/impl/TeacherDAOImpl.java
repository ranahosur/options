package com.bambinos.dao.impl;

import com.bambinos.dao.TeacherDAO;
import com.bambinos.model.Teacher;
import com.bambinos.model.Teacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.bambinos.model.BambinoConstants.EMPLOYEE_ID_START;

public class TeacherDAOImpl extends BaseDaoImpl implements TeacherDAO {

    private static final Logger logger = Logger.getLogger(TeacherDAOImpl.class);

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public Teacher createTeacher(Teacher teacher) {
        String sql = "insert into teacher (teacher_id, first_name, last_name, employee_id, sex, dob, employment_type," +
                " qualification, medical_qualification, linked_in_profile, cost_per_hour, created_by ) " +
                " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        teacher.setTeacherId(generateId());
        teacher.setEmployeeId(getEmployeeId());
        logger.debug("Teacher created with id "+ teacher.getTeacherId());
        jdbcTemplate.update(sql,  teacher.getTeacherId(), teacher.getFirstName(),teacher.getLastName(),teacher.getEmployeeId(),
                teacher.getSex(),teacher.getDob(),teacher.getEmploymentType(), teacher.getQualification(),teacher.getMedicalQualification(),
                teacher.getLinkedInProfile(),teacher.getCostPerHour(),teacher.getCreatedBy());
        return teacher;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        String sql = "update  teacher  set  first_name  = ? , last_name  = ? , employee_id  = ? , " +
                "sex  = ? , dob  = ? , employment_type  = ? ," +
                " qualification  = ? , medical_qualification  = ? , linked_in_profile  = ? ," +
                " cost_per_hour  = ? , created_by  = ?   where teacher_id = ? " ;

         logger.debug("Teacher created with id "+ teacher.getTeacherId());
        jdbcTemplate.update(sql,  teacher.getFirstName(),teacher.getLastName(),teacher.getEmployeeId(),
                teacher.getSex(),teacher.getDob(),teacher.getEmploymentType(), teacher.getQualification(),teacher.getMedicalQualification(),
                teacher.getLinkedInProfile(),teacher.getCostPerHour(),teacher.getCreatedBy(),teacher.getTeacherId());
        return teacher;
    }

    @Override
    public void deleteTeacher(String teacherId) {
        String sql = "Delete from teacher  WHERE teacher_id = ?";
        jdbcTemplate.update(sql, teacherId);

    }

    @Override
    public Teacher findTeacherByTeacherId(String teacherId) {
        String sql = " select * from teacher where teacher_id = '"+teacherId+"'";
        List<Teacher> centers = jdbcTemplate.query(sql, new TeacherDAOImpl.TeacherMapper());
        return centers.get(0);
    }

    @Override
    public List<Teacher> findTeachersAll() {
        String sql = " select * from teacher ";

        List<Teacher> centers = jdbcTemplate.query(sql, new TeacherDAOImpl.TeacherMapper());
        return centers;
    }

    class TeacherMapper implements RowMapper<Teacher> {

        public Teacher mapRow(ResultSet rs, int arg1) throws SQLException {
            Teacher teacher = new Teacher();
            teacher.setTeacherId(rs.getString("teacher_id"));
            teacher.setFirstName(rs.getString("first_name"));
            teacher.setLastName(rs.getString("last_name"));
            teacher.setEmployeeId(rs.getLong("employee_id"));
            teacher.setSex(rs.getString("sex"));
            teacher.setDob(rs.getDate("dob"));
            teacher.setEmploymentType(rs.getString("employment_type"));
            teacher.setQualification(rs.getString("qualification"));
            teacher.setMedicalQualification(rs.getString("medical_qualification"));
            teacher.setLinkedInProfile(rs.getString("linked_in_profile"));
            teacher.setCostPerHour(rs.getBigDecimal("cost_per_hour"));
            teacher.setCreatedBy(rs.getString("created_by"));
            teacher.setCreateDate(rs.getTimestamp("create_date"));
            return teacher;
        }
    }

    //TODO improve this for race condition
    public Long getEmployeeId() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
        String sql = "SELECT max(employee_id) FROM teacher ";

        String maxEmployeeId = jdbcTemplate.queryForObject(
                sql, new Object[] {  }, String.class);
        if(maxEmployeeId == null){
            return EMPLOYEE_ID_START;
        }
        return Long.parseLong(maxEmployeeId)+1;
    }
}
