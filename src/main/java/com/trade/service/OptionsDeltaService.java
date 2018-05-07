package com.trade.service;

import com.trade.model.OptionDelta;
import com.trade.model.OptionDetail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class OptionsDeltaService  implements ApplicationListener<BrokerAvailabilityEvent> {

    private static final Logger logger = Logger.getLogger(OptionsDeltaService.class);

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    MarketDataService marketDataService;

    private  OptionDeltaGenerator quoteGenerator;

    private AtomicBoolean brokerAvailable = new AtomicBoolean();


    @Autowired
    public OptionsDeltaService(MessageSendingOperations<String> messagingTemplate,MarketDataService marketDataService) {
        this.messagingTemplate = messagingTemplate;
        quoteGenerator = new OptionDeltaGenerator(marketDataService);
    }

    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        this.brokerAvailable.set(event.isBrokerAvailable());
    }

    @Scheduled(fixedDelay=2000)
    public void sendQuotes() {
        quoteGenerator = new OptionDeltaGenerator(marketDataService);
        //TODO change this so that the for loop is for the original list so that changing the price happens in the loop
        for (OptionDelta quote : this.quoteGenerator.generateQuotes()) {
            if (logger.isTraceEnabled()) {
                logger.trace("Sending quote " + quote);
            }
            if (this.brokerAvailable.get()) {
                this.messagingTemplate.convertAndSend("/topic/price.option." + quote.getOptionDetailId(), quote);
            }
        }
    }


    private static class OptionDeltaGenerator {



        private static final MathContext mathContext = new MathContext(2);

        private final Random random = new Random();

        private List<OptionDetail> options = new ArrayList<>();


        public OptionDeltaGenerator(MarketDataService marketDataService) {
            options = marketDataService.findOptionDetailsAll().subList(0,1);
            logger.debug("number of options is "+ options.size());
        }

        public Set<OptionDelta> generateQuotes() {
            Set<OptionDelta> quotes = new HashSet<>();
            for (OptionDetail optionDetail : options) {

                OptionDelta optionDelta = new OptionDelta();
                optionDelta.setOptionDetailId(optionDetail.getOptionDetailId());
                optionDelta.setStockPrice(getPrice(optionDetail.getStockPrice()));
                optionDelta.setCallAskPrice(getPrice(optionDetail.getCallAskPrice()));
                optionDelta.setCallBidPrice(getPrice(optionDetail.getCallBidPrice()));
                optionDelta.setPutAskPrice(getPrice(optionDetail.getPutAskPrice()));
                optionDelta.setPutBidPrice(getPrice(optionDetail.getPutBidPrice()));
                //TODO add other fields
                quotes.add(optionDelta);
            }
            return quotes;
        }

        private BigDecimal getPrice(BigDecimal seedPrice) {
//            BigDecimal seedPrice = new BigDecimal(this.prices.get(ticker), mathContext);
            if(seedPrice != null) {
                double range = seedPrice.multiply(new BigDecimal(0.02)).doubleValue();
                BigDecimal priceChange = new BigDecimal(String.valueOf(this.random.nextDouble() * range), mathContext);
                return seedPrice.add(priceChange);
            }
            return new BigDecimal("0.0");
        }

    }

}
