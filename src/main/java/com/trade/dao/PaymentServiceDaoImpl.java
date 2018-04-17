package com.trade.dao;

import com.trade.model.PaymentService;
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
public class PaymentServiceDaoImpl extends BaseDaoImpl implements  PaymentServiceDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<PaymentService> findPaymentService() {
        String sql = "select * from payment_service ";
        return  jdbcTemplate.query(sql, new PaymentServiceMapper());
    }
}

class PaymentServiceMapper implements RowMapper<PaymentService> {

    public PaymentService mapRow(ResultSet rs, int arg1) throws SQLException {
        PaymentService paymentService = new PaymentService();
        paymentService.setPaymentServiceId(rs.getString("payment_service_id"));;
        paymentService.setPaymentServiceName(rs.getString("payment_service_name"));
        paymentService.setPaymentServiceCode(rs.getString("payment_service_code"));
        return paymentService;
    }
}
