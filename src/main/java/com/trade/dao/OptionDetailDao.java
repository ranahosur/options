package com.trade.dao;

import com.trade.model.ExpiryDate;
import com.trade.model.OptionDetail;
import com.trade.model.Stock;

import java.sql.Date;
import java.util.List;

public interface OptionDetailDao {

    void createOptionDetail(OptionDetail optionDetail);

    void updateOptionDetail(OptionDetail optionDetail);

    List<OptionDetail> findOptionDetailBySymbol(String symbol);

    void deleteOptionDetail(String optionId);

    List<OptionDetail> findOptionDetailAll();

    List<Stock> findAllStocks();

    List<ExpiryDate> findAllExpiryDates(String symbol);

    List<OptionDetail> findOptionDetailBySymbolExpiryDate(String symbol,Date expiryDate);


}
