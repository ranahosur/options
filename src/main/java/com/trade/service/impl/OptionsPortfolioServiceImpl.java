package com.trade.service.impl;

import com.trade.model.OptionDetail;
import com.trade.service.MarketDataService;
import com.trade.service.OptionsPortfolioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionsPortfolioServiceImpl implements OptionsPortfolioService {

    private static final Logger logger = Logger.getLogger(OptionsPortfolioServiceImpl.class);

    @Autowired
    MarketDataService marketDataService;

    public OptionsPortfolioServiceImpl(MarketDataService marketDataService) {
        logger.debug("entry into OptionsPortfolioServiceImpl() marketDataService is " + marketDataService);
        this.marketDataService = marketDataService;
    }

    @Override
    public List<OptionDetail> findPortfolio() {
        logger.debug("entry into findPortfolio marketDataService is " + marketDataService);
        return marketDataService.findOptionDetailsAll().subList(0,1);

    }
}
