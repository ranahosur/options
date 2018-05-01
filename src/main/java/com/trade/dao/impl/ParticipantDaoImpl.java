package com.trade.dao.impl;

import com.trade.dao.ParticipantDao;
import com.trade.model.Participant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParticipantDaoImpl extends BaseDaoImpl implements ParticipantDao {

    private static final Logger logger = Logger.getLogger(ParticipantDaoImpl.class);
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createParticipant(Participant participant) {
        String sql = "insert into participant (participant_id,user_id,total_funds) values (?, ? , ?)";

        participant.setParticipantId(generateId());
        logger.debug("Participant created with id "+participant.getParticipantId());
        jdbcTemplate.update(sql,  participant.getParticipantId(),participant.getUserId(), participant.getTotalFunds());

    }

    public void updateParticipant(Participant participant) {
        String sql = "update  participant set total_funds = ? where participant_id = ? ";

        logger.debug("Participant updated with id "+participant.getParticipantId());
        jdbcTemplate.update(sql,  participant.getTotalFunds(),participant.getParticipantId());


    }

    public Participant findParticipantByUserId(String userId) {
        String sql = "select * from participant where user_id = '" + userId + "'";
        List<Participant> participants =  jdbcTemplate.query(sql, new ParticipantMapper());
        return participants.size() > 0 ? participants.get(0) : null;
    }

    public void deleteParticipant(String participantId) {
        String sql = "delete from  participant where participant_id = ? ";
        logger.debug("Participant deleted with id "+ participantId);
        jdbcTemplate.update(sql,  participantId);

    }

    class ParticipantMapper implements RowMapper<Participant> {

        public Participant mapRow(ResultSet rs, int arg1) throws SQLException {
            Participant user = new Participant();
            user.setParticipantId(rs.getString("participant_id"));
            user.setUserId(rs.getString("user_id"));
            user.setTotalFunds(rs.getBigDecimal("total_funds"));
            user.setCreateDate(rs.getTimestamp("create_date"));
            return user;
        }
    }
}
