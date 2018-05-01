package com.trade.dao.impl;

import com.trade.dao.UserPaymentServiceDao;
import com.trade.model.UserPaymentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by raghu.anahosur on 8/2/2017.
 */
public class UserPaymentServiceDaoImpl extends BaseDaoImpl implements UserPaymentServiceDao {

    private static final Logger logger = Logger.getLogger(UserPaymentServiceDaoImpl.class);
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserPaymentService createPaymentService(UserPaymentService userPaymentService) {
        String sql = "insert into user_payment_service(user_payment_service_id, user_id, payment_service_id, external_id, status )" +
                " values (?, ?, ?, ?, ? )";

        userPaymentService.setUserPaymentServiceId(generateId());
        logger.debug("User created with id "+userPaymentService.getUserPaymentServiceId());
        jdbcTemplate.update(sql, new Object[] {userPaymentService.getUserPaymentServiceId(), userPaymentService.getUserId(),userPaymentService.getPaymentServiceId(),userPaymentService.getExternalId(),userPaymentService.getStatus()});
        return  userPaymentService;
    }

    public List<UserPaymentService> findPaymentServiceByUsername(String username) {
        String sql = " select ps.payment_service_name,ups.* from user_payment_service ups " +
                " join payment_service ps on ps.payment_service_id = ups.payment_service_id " +
                " join user u on u.user_id = ups.user_id " +
                " where u.username = ? ";
        return jdbcTemplate.query(sql, new UserPaymentServiceMapper(),username);
    }
}

class UserPaymentServiceMapper implements RowMapper<UserPaymentService> {

    public UserPaymentService mapRow(ResultSet rs, int arg1) throws SQLException {
        UserPaymentService userPaymentService = new UserPaymentService();
        userPaymentService.setUserId(rs.getString("user_id"));
        userPaymentService.setUserPaymentServiceId(rs.getString("user_payment_service_id"));
        userPaymentService.setPaymentServiceId(rs.getString("payment_service_id"));
        userPaymentService.setExternalId(rs.getString("external_id"));
        userPaymentService.setPaymentServiceName(rs.getString("payment_service_name"));
        return userPaymentService;
    }

}
