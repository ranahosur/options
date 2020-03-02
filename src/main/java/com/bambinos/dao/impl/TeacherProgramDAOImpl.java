package com.bambinos.dao.impl;

import com.bambinos.dao.TeacherDAO;
import com.bambinos.dao.TeacherProgramDAO;
import com.bambinos.model.TeacherProgram;
import com.bambinos.model.TeacherProgram;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeacherProgramDAOImpl extends BaseDaoImpl implements TeacherProgramDAO {

    private static final Logger logger = Logger.getLogger(TeacherProgramDAOImpl.class);

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public TeacherProgram createTeacherProgram(TeacherProgram teacherProgram) {
        String sql = "insert into teacher_program (teacher_program_id, teacher_id, program_id, created_by )" +
                "values( ?, ?, ?, ? )";

        teacherProgram.setTeacherProgramId(generateId());
        logger.debug("TeacherProgram created with id "+ teacherProgram.getTeacherProgramId());
        jdbcTemplate.update(sql,  teacherProgram.getTeacherProgramId(), teacherProgram.getTeacherId(),
                teacherProgram.getProgramId(),teacherProgram.getCreatedBy());
        return teacherProgram;
    }

    @Override
    public void deleteTeacherProgram(String teacherProgramId) {
        String sql = "Delete from teacher_program  WHERE teacher_program_id = ?";
        jdbcTemplate.update(sql, teacherProgramId);
    }

    @Override
    public List<TeacherProgram> findTeacherProgramsByTeacherId(String teacherId) {
        String sql = "select * from teacher_program where teacher_id = '"+teacherId+"'";

        List<TeacherProgram> centers = jdbcTemplate.query(sql, new TeacherProgramDAOImpl.TeacherProgramMapper());
        return centers;
    }

    class TeacherProgramMapper implements RowMapper<TeacherProgram> {

        public TeacherProgram mapRow(ResultSet rs, int arg1) throws SQLException {
            TeacherProgram teacherProgram = new TeacherProgram();
            teacherProgram.setTeacherId(rs.getString("teacher_id"));
            teacherProgram.setTeacherProgramId(rs.getString("center_admin_id"));
            teacherProgram.setProgramId(rs.getString("program_id"));
            teacherProgram.setCreatedBy(rs.getString("created_by"));
            teacherProgram.setCreateDate(rs.getTimestamp("create_date"));
            return teacherProgram;
        }
    }
}
