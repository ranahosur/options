package com.bambinos.dao.impl;

import com.bambinos.dao.CenterProgramDAO;
import com.bambinos.model.CenterProgram;
import com.bambinos.model.CenterProgram;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CenterProgramDAOImpl extends BaseDaoImpl implements CenterProgramDAO {

    private static final Logger logger = Logger.getLogger(CenterProgramDAOImpl.class);

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public CenterProgram createCenterProgram(CenterProgram centerProgram) {
        String sql = "insert into center_program (center_program_id, center_id, program_id, created_by )" +
                "values( ?, ?, ?, ? )";

        centerProgram.setCenterProgramId(generateId());
        logger.debug("CenterProgram created with id "+ centerProgram.getCenterProgramId());
        jdbcTemplate.update(sql,  centerProgram.getCenterProgramId(), centerProgram.getCenterId(),
                centerProgram.getProgramId(),centerProgram.getCreatedBy());
        return centerProgram;
    }


    @Override
    public void deleteCenterProgram(String centerProgramId) {
        String sql = "Delete from center_program  WHERE center_program_id = ?";
        jdbcTemplate.update(sql, centerProgramId);
    }

    @Override
    public List<CenterProgram> findCenterProgramsByCenterId(String centerId) {
        String sql = "select * from center_program where center_id = '"+centerId+"'";

        List<CenterProgram> centers = jdbcTemplate.query(sql, new CenterProgramDAOImpl.CenterProgramMapper());
        return centers;
    }

    class CenterProgramMapper implements RowMapper<CenterProgram> {

        public CenterProgram mapRow(ResultSet rs, int arg1) throws SQLException {
            CenterProgram centerProgram = new CenterProgram();
            centerProgram.setCenterId(rs.getString("center_id"));
            centerProgram.setCenterProgramId(rs.getString("center_program_id"));
            centerProgram.setProgramId(rs.getString("program_id"));
            centerProgram.setCreatedBy(rs.getString("created_by"));
            centerProgram.setCreateDate(rs.getTimestamp("create_date"));
            return centerProgram;
        }
    }
}
