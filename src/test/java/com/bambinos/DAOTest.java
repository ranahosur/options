package com.bambinos;

import com.bambinos.dao.OptionDetailDao;
import com.bambinos.model.ExpiryDate;
import com.bambinos.model.OptionDetail;
import com.bambinos.model.Stock;
import com.bambinos.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.List;


@ContextConfiguration(locations = { "classpath:/bambinos/config/user-beans.xml" })
@WebAppConfiguration
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
        List<OptionDetail> ops = optionDetailDao.findOptionDetailAll();
        System.out.println(ops);
        Assert.assertTrue(ops.size() > 0);
    }
}
