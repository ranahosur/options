package com.bambinos.dao.impl;

import com.bambinos.dao.ProgramDAO;
import com.bambinos.model.Program;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProgramDAOImpl extends BaseDaoImpl implements ProgramDAO {

    private static final Logger logger = Logger.getLogger(ProgramDAOImpl.class);
    
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public Program createProgram(Program program) {
        String sql = "insert into program (program_id, program_name, program_code,  description, price, capacity, assessment_period, created_by )" +
                "values( ?, ?, ?, ?, ?, ?, ?, ? )";

        program.setProgramId(generateId());
        logger.debug("Program created with id "+ program.getProgramId());
        jdbcTemplate.update(sql,  program.getProgramId(), program.getProgramName(), program.getProgramCode(),
                program.getDescription(), program.getPrice(), program.getCapacity(),
                program.getAssessmentPeriod(),program.getCreatedBy());
        return program;
    }

    @Override
    public Program updateProgram(Program program) {
        String sql = "update program set program_name = ? , program_code = ? ,  description = ? , price = ? , capacity = ? , assessment_period = ? , created_by = ?  where program_id = ? " ;
        logger.debug("Program updated with id "+ program.getProgramId());
        jdbcTemplate.update(sql,   program.getProgramName(), program.getProgramCode(),
                program.getDescription(), program.getPrice(), program.getCapacity(),
                program.getAssessmentPeriod(),program.getCreatedBy(),program.getProgramId());
        return program;
    }

    @Override
    public void deleteProgram(String programId) {
        String sql = "Delete from program  WHERE program_id = ?";
        jdbcTemplate.update(sql, programId);

    }

    @Override
    public List<Program> findPrograms() {
        String sql = "select * from program ";

        List<Program> programs = jdbcTemplate.query(sql, new ProgramDAOImpl.ProgramMapper());
        return programs;
    }

    class ProgramMapper implements RowMapper<Program> {

        public Program mapRow(ResultSet rs, int arg1) throws SQLException {
            Program program = new Program();
            program.setProgramId(rs.getString("program_id"));
            ;
            program.setProgramCode(rs.getString("program_code"));
            program.setProgramName(rs.getString("program_name"));
            program.setDescription(rs.getString("description"));
            program.setPrice(rs.getBigDecimal("price"));
            program.setCapacity(rs.getInt("capacity"));
            program.setAssessmentPeriod(rs.getInt("assessment_period"));
            program.setCreatedBy(rs.getString("created_by"));
            program.setCreateDate(rs.getTimestamp("create_date"));
            return program;
        }
    }
}
