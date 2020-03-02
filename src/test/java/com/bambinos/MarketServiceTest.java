package com.bambinos;

import com.bambinos.service.MarketDataService;
import com.bambinos.service.MarketDataUpdateService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@ContextConfiguration(locations = { "classpath:/bambinos/config/user-beans.xml" })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MarketServiceTest {

//    @Autowired
//    MarketDataService marketDataService;
//
//    @Autowired
//    MarketDataUpdateService marketDataUpdateService;


//    @Ignore
//    @Test
//    public void testMarketUpdate() {
//        marketDataService.uploadMarketData("C:\\my projects\\tradewins\\market_nse_data_new.csv");
//    }
//
//    @Test
//    public void testUpdateRandom(){
//        marketDataUpdateService.updateMarketData();
//    }
}
