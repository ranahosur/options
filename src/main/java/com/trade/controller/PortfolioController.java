/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.trade.controller;

import com.trade.model.Portfolio;
import com.trade.model.PortfolioPosition;
import com.trade.model.Trade;
import com.trade.service.PortfolioService;
import com.trade.service.TradeService;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;


@Controller
public class PortfolioController {

	private static final Logger logger = Logger.getLogger(PortfolioController.class);

	private final PortfolioService portfolioService;

	private final TradeService tradeService;


	@Autowired
	public PortfolioController(PortfolioService portfolioService, TradeService tradeService) {
		this.portfolioService = portfolioService;
		this.tradeService = tradeService;
	}

	@SubscribeMapping("/positions")
	public List<PortfolioPosition> getPositions() {
		logger.debug("Positions for paulson"  );
		Portfolio portfolio = this.portfolioService.findPortfolio("paulson");
		return portfolio.getPositions();
	}

	@MessageMapping("/trade")
	public void executeTrade(Trade trade, Principal principal) {
		trade.setUsername(principal.getName());
		logger.debug("Trade: " + trade);
		this.tradeService.executeTrade(trade);
	}

	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

}
