package com.bambinos.service;

import com.bambinos.model.OptionDetail;

import java.sql.Date;
import java.util.List;

public interface MarketDataService {

    void uploadMarketData(String fileName);

    List<OptionDetail> findOptionDetailsAll();

    List<OptionDetail> findOptionDetailBySymbol(String symbol);

    List<OptionDetail> findOptionDetailBySymbolExpiryDate(String symbol,Date expiryDate);

    void updateOptionDetail(OptionDetail optionDetail);
}
