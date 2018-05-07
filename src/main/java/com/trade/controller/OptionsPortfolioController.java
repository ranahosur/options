package com.trade.controller;

import com.trade.model.OptionDetail;
import com.trade.service.MarketDataService;
import com.trade.service.OptionsPortfolioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OptionsPortfolioController {

    private static final Logger logger = Logger.getLogger(OptionsPortfolioController.class);


    OptionsPortfolioService optionsPortfolioService;
    @Autowired
    public OptionsPortfolioController(OptionsPortfolioService optionsPortfolioService) {
        this.optionsPortfolioService = optionsPortfolioService;
    }

    @SubscribeMapping("/optionPositions")
    public List<OptionDetail> getOptionsPortfolio() {
        logger.debug("Positions "  );
        return optionsPortfolioService.findPortfolio();
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
