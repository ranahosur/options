package com.bambinos.dao;

import com.bambinos.model.ExpiryDate;
import com.bambinos.model.OptionDetail;
import com.bambinos.model.Stock;

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
