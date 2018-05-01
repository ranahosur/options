package com.trade;

import com.trade.dao.OptionDetailDao;
import com.trade.model.ExpiryDate;
import com.trade.model.OptionDetail;
import com.trade.model.Stock;
import com.trade.util.DateUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

@Ignore
@ContextConfiguration(locations = { "classpath:/billpayment/config/user-beans.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class DAOTest {

    @Autowired
    OptionDetailDao optionDetailDao;

    @Test
    public void testExpiryDates() {
        List<ExpiryDate> expiryDates = optionDetailDao.findAllExpiryDates("SBIN");
        for(ExpiryDate expiryDate : expiryDates){
            System.out.println(expiryDate.getExpiryDate() + " / " +expiryDate.getExpiryDateLabel());
        }

    }
    @Test
    public void testStocks() {
        List<Stock> expiryDates = optionDetailDao.findAllStocks();
        for(Stock expiryDate : expiryDates){
            System.out.println(expiryDate.getSymbol());
        }

    }

    @Test
    public void testFinder() {
        Date date = DateUtil.parseMMDDYYYY("05/31/2018");
        List<OptionDetail> ops = optionDetailDao.findOptionDetailBySymbolExpiryDate("SBIN",date);
        Assert.assertTrue(ops.size() > 0);
    }
}
