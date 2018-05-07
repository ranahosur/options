package com.trade.service;

import com.trade.model.OptionDetail;

import java.util.List;

public interface OptionsPortfolioService {

    List<OptionDetail> findPortfolio();
}
