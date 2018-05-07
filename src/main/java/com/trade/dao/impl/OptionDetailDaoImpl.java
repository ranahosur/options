package com.trade.dao.impl;

import com.trade.dao.OptionDetailDao;
import com.trade.model.ExpiryDate;
import com.trade.model.OptionDetail;
import com.trade.model.Stock;
import com.trade.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.trade.model.OptionsConstants.DATE_FORMAT_DDMONYYYY;
import static com.trade.model.OptionsConstants.DATE_FORMAT_YMD;

public class OptionDetailDaoImpl extends BaseDaoImpl implements OptionDetailDao {

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger(OptionDetailDaoImpl.class);

    public void createOptionDetail(OptionDetail optionDetail) {
        String sql = "insert into option_detail (option_detail_id,name,symbol,stock_price,strike_price, expiry_date,call_bid_price, call_ask_price," +
                " call_volume, call_open_int, call_open_int_change, call_ask_qty, call_bid_qty,call_last_traded_price,call_implied_volatility,call_net_change, put_bid_price, put_ask_price, put_volume, put_open_int, " +
                " put_open_int_change, put_ask_qty, put_bid_qty ,put_last_traded_price,put_implied_volatility,put_net_change) values (?, ? , ?, ? ,?, ? , ?, ?, ? , ?, ? ,?, ? , ?, ?, ? , ?, ? ,?, ?, ?, ? , ?, ? ,?, ? )";
        optionDetail.setOptionDetailId(generateId());
        logger.debug("OptionDetail created with optionDetail id "+ optionDetail.getOptionDetailId());
        jdbcTemplate.update(sql, new Object[] {
                optionDetail.getOptionDetailId(), optionDetail.getName(), optionDetail.getSymbol(), optionDetail.getStockPrice(), optionDetail.getStrikePrice(), optionDetail.getExpiryDate(), optionDetail.getCallBidPrice(), optionDetail.getCallAskPrice(),
                optionDetail.getCallVolume(), optionDetail.getCallOpenInt(), optionDetail.getCallOpenIntChange(), optionDetail.getCallAskQty(), optionDetail.getCallBidQty(), optionDetail.getCallLastTradedPrice(),optionDetail.getCallImpliedVolatility(),optionDetail.getCallNetChange(), optionDetail.getPutBidPrice(), optionDetail.getPutAskPrice(),
                optionDetail.getPutVolume(), optionDetail.getPutOpenInt(), optionDetail.getPutOpenIntChange(), optionDetail.getPutAskQty(), optionDetail.getPutBidQty(), optionDetail.getPutLastTradedPrice(),optionDetail.getPutImpliedVolatility(),optionDetail.getPutNetChange()
        });

    }

    public void updateOptionDetail(OptionDetail optionDetail) {
        String sql = "update option_detail set name = ?,symbol   = ?,stock_price  = ?,strike_price  = ?, expiry_date  = ?,call_bid_price  = ?, call_ask_price  = ?," +
                " call_volume  = ?, call_open_int  = ?, call_open_int_change  = ?, call_ask_qty  = ?, call_bid_qty  = ?,call_last_traded_price = ?,call_implied_volatility = ?,call_net_change = ?,  put_bid_price  = ?, put_ask_price  = ?, put_volume  = ?, put_open_int  = ?, " +
                " put_open_int_change = ?, put_ask_qty = ?, put_bid_qty = ? ,put_last_traded_price = ? ,put_implied_volatility = ? ,put_net_change = ?  where option_detail_id = ? ";
        optionDetail.setOptionDetailId(generateId());
        logger.debug("OptionDetail created with optionDetail id "+ optionDetail.getOptionDetailId());
        jdbcTemplate.update(sql, new Object[] {
                optionDetail.getName(), optionDetail.getSymbol(), optionDetail.getStockPrice(), optionDetail.getStrikePrice(), optionDetail.getExpiryDate(), optionDetail.getCallBidPrice(), optionDetail.getCallAskPrice(),
                optionDetail.getCallVolume(), optionDetail.getCallOpenInt(), optionDetail.getCallOpenIntChange(), optionDetail.getCallAskQty(), optionDetail.getCallBidQty(), optionDetail.getCallLastTradedPrice(),optionDetail.getCallImpliedVolatility(),optionDetail.getCallNetChange(), optionDetail.getPutBidPrice(), optionDetail.getPutAskPrice(),
                optionDetail.getPutVolume(), optionDetail.getPutOpenInt(), optionDetail.getPutOpenIntChange(), optionDetail.getPutAskQty(), optionDetail.getPutBidQty(), optionDetail.getPutLastTradedPrice(),optionDetail.getPutImpliedVolatility(),optionDetail.getPutNetChange(), optionDetail.getOptionDetailId()
        });

    }

    public List<OptionDetail> findOptionDetailBySymbol(String symbol) {
        String sql = " select * from option_detail op " +
                " where op.symbol = '" + symbol + "'";
        return jdbcTemplate.query(sql, new OptionMapper());
    }

    public List<OptionDetail> findOptionDetailBySymbolExpiryDate(String symbol, Date expiryDate) {
        String sql = " select * from option_detail op " +
                " where op.symbol = ? and op.expiry_date = ?";
        return jdbcTemplate.query(sql, new Object[] { symbol,expiryDate }, new OptionMapper());
    }

    public List<OptionDetail> findOptionDetailAll() {
        String sql = " select * from option_detail op " ;
        return jdbcTemplate.query(sql, new OptionMapper());
    }

    public List<Stock> findAllStocks() {
        String sql = " select distinct symbol from option_detail op " ;
        return jdbcTemplate.query(sql, new StockMapper());
    }

    public List<ExpiryDate> findAllExpiryDates(String symbol) {
        String sql = " select distinct expiry_date from option_detail op " ;
        return jdbcTemplate.query(sql, new ExpiryDateMapper());
    }

    public void deleteOptionDetail(String optionId) {
        String sql = "delete from option_detail where option_detail_id = ?";

        jdbcTemplate.update(sql, new Object[] {
                optionId
        });
    }

    class OptionMapper implements RowMapper<OptionDetail> {

        public OptionDetail mapRow(ResultSet rs, int arg1) throws SQLException {
            OptionDetail optionDetail = new OptionDetail();
            optionDetail.setOptionDetailId(rs.getString("option_detail_id"));
            optionDetail.setCallAskPrice(rs.getBigDecimal("call_ask_price"));
            optionDetail.setCallAskQty(rs.getLong("call_ask_qty"));
            optionDetail.setCallBidPrice(rs.getBigDecimal("call_bid_price"));
            optionDetail.setCallBidQty(rs.getLong("call_bid_qty"));
            optionDetail.setCallOpenInt(rs.getLong("call_open_int"));
            optionDetail.setCallOpenIntChange(rs.getLong("call_open_int_change"));
            optionDetail.setCallVolume(rs.getLong("call_volume"));
            optionDetail.setCallLastTradedPrice(rs.getBigDecimal("call_last_traded_price"));
            optionDetail.setCallImpliedVolatility(rs.getBigDecimal("call_implied_volatility"));
            optionDetail.setCallNetChange(rs.getBigDecimal("call_net_change"));
            optionDetail.setExpiryDate(rs.getDate("expiry_date"));
            optionDetail.setName(rs.getString("name"));
            optionDetail.setStockPrice(rs.getBigDecimal("stock_price"));
            optionDetail.setStrikePrice(rs.getBigDecimal("strike_price"));
            optionDetail.setPutAskPrice(rs.getBigDecimal("Put_ask_price"));
            optionDetail.setPutAskQty(rs.getLong("Put_ask_qty"));
            optionDetail.setPutBidPrice(rs.getBigDecimal("Put_bid_price"));
            optionDetail.setPutBidQty(rs.getLong("Put_bid_qty"));
            optionDetail.setPutOpenInt(rs.getLong("Put_open_int"));
            optionDetail.setPutOpenIntChange(rs.getLong("Put_open_int_change"));
            optionDetail.setPutVolume(rs.getLong("Put_volume"));
            optionDetail.setSymbol(rs.getString("symbol"));
            optionDetail.setPutLastTradedPrice(rs.getBigDecimal("put_last_traded_price"));
            optionDetail.setPutImpliedVolatility(rs.getBigDecimal("put_implied_volatility"));
            optionDetail.setPutNetChange(rs.getBigDecimal("put_net_change"));
            optionDetail.setCreateDate(rs.getTimestamp("create_date"));

            return optionDetail;
        }
    }

    class StockMapper implements RowMapper<Stock> {
        public Stock mapRow(ResultSet rs, int arg1) throws SQLException {
            Stock stock = new Stock();
            stock.setSymbol(rs.getString("symbol"));
            return stock;
        }

    }

    class ExpiryDateMapper implements RowMapper<ExpiryDate> {
        public ExpiryDate mapRow(ResultSet rs, int arg1) throws SQLException {
            ExpiryDate expiryDate = new ExpiryDate();
            expiryDate.setExpiryDate(DateUtil.formatDate(rs.getDate("expiry_Date"),DATE_FORMAT_YMD));
            expiryDate.setExpiryDateLabel(DateUtil.formatDate(rs.getDate("expiry_Date"),DATE_FORMAT_DDMONYYYY).toUpperCase());
            return expiryDate;
        }

    }
}
