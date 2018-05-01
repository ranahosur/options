package com.trade.dao.impl;

import com.trade.dao.ParticipantTransactionDao;
import com.trade.model.ParticipantTransaction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParticipantTransactionDaoImpl extends BaseDaoImpl implements ParticipantTransactionDao {

    private static final Logger logger = Logger.getLogger(ParticipantTransactionDaoImpl.class);
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createParticipantTransaction(ParticipantTransaction participantTransaction) {
        String sql = "insert into participant_Transaction (participant_transaction_id,participant_id,option_detail_id,option_type,funds_invested,entry_price,exit_price,lot_count,status,result,exercise_date,linked_transaction_id " +
                " ) values (?, ? , ?, ?, ? , ?, ?, ? , ?, ?, ? , ? )";

        participantTransaction.setParticipantTransactionId(generateId());
        logger.debug("ParticipantTransaction created with id "+ participantTransaction.getParticipantTransactionId());
        jdbcTemplate.update(sql, participantTransaction.getParticipantTransactionId(), participantTransaction.getParticipantId(), participantTransaction.getOptionDetailId(), participantTransaction.getOptionType(), participantTransaction.getFundsInvested(),participantTransaction.getEntryPrice(),participantTransaction.getExitPrice(),
                participantTransaction.getLotCount(), participantTransaction.getStatus(), participantTransaction.getResult(), participantTransaction.getExcerciseDate(), participantTransaction.getLinkedTransactionId());

    }

    public void updateParticipantTransaction(ParticipantTransaction participantTransaction) {

        String sql = "update participant_Transaction set option_type = ?,funds_invested = ?, entry_price = ? , exit_price = ?, lot_count = ?,status = ?,result = ?,exercise_date = ?,linked_transaction_id  = ? " +
                " where participant_transaction_id = ? ";

         logger.debug("ParticipantTransaction updated with id "+ participantTransaction.getParticipantTransactionId());
        jdbcTemplate.update(sql,  participantTransaction.getOptionType(), participantTransaction.getFundsInvested(),participantTransaction.getEntryPrice(),participantTransaction.getExitPrice(),
                participantTransaction.getLotCount(), participantTransaction.getStatus(), participantTransaction.getResult(), participantTransaction.getExcerciseDate(), participantTransaction.getLinkedTransactionId(), participantTransaction.getParticipantTransactionId());

    }

    public List<ParticipantTransaction> findParticipantTransactionByParticipantId(String participantId) {
        String sql = "select * from participant_transaction pt " +
                " join option_detail od on od.option_detail_id = pt.option_detail_id" +
                " where participant_id = '" + participantId + "'";
        return jdbcTemplate.query(sql, new ParticipantTransactionMapper());

    }

    public void deleteParticipantTransaction(String transcationId) {
        String sql = "delete from participant_transaction  " +
                " where participant_transaction_id = ? ";

        logger.debug("ParticipantTransaction updated with id "+transcationId);
        jdbcTemplate.update(sql, transcationId);

    }

    class ParticipantTransactionMapper implements RowMapper<ParticipantTransaction> {

        public ParticipantTransaction mapRow(ResultSet rs, int arg1) throws SQLException {
            ParticipantTransaction participantTransaction = new ParticipantTransaction();
            participantTransaction.setParticipantId(rs.getString("participant_id"));
            participantTransaction.setParticipantTransactionId(rs.getString("participant_transaction_id"));
            participantTransaction.setFundsInvested(rs.getBigDecimal("funds_invested"));
            participantTransaction.setExcerciseDate(rs.getDate("exercise_date"));
            participantTransaction.setLinkedTransactionId(rs.getString("linked_transaction_id"));
            participantTransaction.setLotCount(rs.getInt("lot_count"));
            participantTransaction.setCreateDate(rs.getTimestamp("create_date"));
            participantTransaction.setStatus(rs.getString("status"));
            participantTransaction.setResult(rs.getString("result"));
            participantTransaction.setOptionDetailId(rs.getString("option_detail_id"));
            participantTransaction.setOptionType(rs.getString("option_type"));
            participantTransaction.setCurrentMarketPrice(rs.getBigDecimal("stock_price"));
            participantTransaction.setStockName(rs.getString("name"));
            participantTransaction.setSymbol(rs.getString("symbol"));
            participantTransaction.setEntryPrice(rs.getBigDecimal("entry_price"));
            participantTransaction.setExitPrice(rs.getBigDecimal("exit_price"));
            participantTransaction.setExpiryDate(rs.getDate("expiry_date"));
            participantTransaction.setStrikePrice(rs.getBigDecimal("strike_price"));
            participantTransaction.setCallAskPrice(rs.getBigDecimal("call_ask_price"));
            participantTransaction.setCallBidPrice(rs.getBigDecimal("call_bid_price"));
            participantTransaction.setPutAskPrice(rs.getBigDecimal("put_ask_price"));
            participantTransaction.setPutBidPrice(rs.getBigDecimal("put_bid_price"));

            return participantTransaction;
        }
    }
}
