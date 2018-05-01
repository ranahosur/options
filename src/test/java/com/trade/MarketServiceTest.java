package com.trade;

import com.trade.service.MarketDataService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@ContextConfiguration(locations = { "classpath:/billpayment/config/user-beans.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MarketServiceTest {

    @Autowired
    MarketDataService marketDataService;

    @Test
    public void testMarketUpdate() {
        marketDataService.uploadMarketData("C:\\my projects\\tradewins\\market_nse_data_new.csv");
    }
}
