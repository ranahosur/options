package com.bambinos.service.impl;

import com.bambinos.dao.OptionDetailDao;
import com.bambinos.model.OptionDetail;
import com.bambinos.model.OptionsConstants;
import com.bambinos.service.MarketDataService;
import com.bambinos.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class MarketDataServiceImpl implements MarketDataService {

    private static final Logger logger = Logger.getLogger(MarketDataServiceImpl.class);

    @Autowired
    public OptionDetailDao optionDetailDao;


    @Override
    public List<OptionDetail> findOptionDetailsAll() {
        return optionDetailDao.findOptionDetailAll();
    }

    @Override
    public List<OptionDetail> findOptionDetailBySymbol(String symbol) {
        return optionDetailDao.findOptionDetailBySymbol(symbol);
    }

    @Override
    public List<OptionDetail> findOptionDetailBySymbolExpiryDate(String symbol, Date expiryDate) {
        return optionDetailDao.findOptionDetailBySymbolExpiryDate(symbol,expiryDate);
    }

    @Override
    public void updateOptionDetail(OptionDetail optionDetail) {
        optionDetailDao.updateOptionDetail(optionDetail);
    }

    public void uploadMarketData(String fileName) {

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            String sCurrentLine;
            int count = 0;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                if(count == 0){
                    logger.debug("Just the  header "+ sCurrentLine);
                }
                else{
                    logger.debug("Processing --> "+ sCurrentLine);
                    OptionDetail optionDetail = convertToOptionDetail(sCurrentLine);
                    optionDetailDao.createOptionDetail(optionDetail);
                    logger.debug("Created Option Detail with id "+ optionDetail.getOptionDetailId());
                }
                count++;

            }

        } catch (IOException e) {

            logger.error("unable to load file", e);
        } finally {

            try {

                if (bufferedReader != null)
                    bufferedReader.close();

                if (fileReader != null)
                    fileReader.close();

            } catch (IOException ex) {

                logger.error("unable to load file", ex);

            }
        }
    }

    private OptionDetail convertToOptionDetail(String inputLine){
        OptionDetail optionDetail = new OptionDetail();
        String[] tokens = inputLine.split(",");

        for(int count = 0; count < tokens.length; count++){
            String token = tokens[count];
            if(count == OptionsConstants.MARKET_CSV_MAPPING.SYMBOL.ordinal()){
                optionDetail.setSymbol(token);
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.STOCK_NAME.ordinal()){
                optionDetail.setName(token);
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.STOCK_PRICE.ordinal()){
                optionDetail.setStockPrice(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.EXPIRY_DATE.ordinal()){
                optionDetail.setExpiryDate(DateUtil.parseMMDDYYYY(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_OPEN_INT.ordinal()){
                optionDetail.setCallOpenInt(parseLong(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_OPEN_INT_CHANGE.ordinal()){
                optionDetail.setCallOpenIntChange(parseLong(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_VOLUME.ordinal()){
                optionDetail.setCallVolume(parseLong(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_IMPLIED_VOLATILITY.ordinal()){
                optionDetail.setCallImpliedVolatility(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_LAST_TRADED_PRICE.ordinal()){
                optionDetail.setCallLastTradedPrice(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_NET_CHANGE.ordinal()){
                optionDetail.setCallNetChange(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_BID_QTY.ordinal()){
                optionDetail.setCallBidQty(parseLong(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_BID_PRICE.ordinal()){
                optionDetail.setCallBidPrice(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_ASK_PRICE.ordinal()){
                optionDetail.setCallAskPrice(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.CALL_ASK_QTY.ordinal()){
                optionDetail.setCallAskQty(parseLong(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.STRIKE_PRICE.ordinal()){
                optionDetail.setStrikePrice(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_BID_QTY.ordinal()){
                optionDetail.setPutBidQty(parseLong(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_BID_PRICE.ordinal()){
                optionDetail.setPutBidPrice(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_ASK_PRICE.ordinal()){
                optionDetail.setPutAskPrice(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_ASK_QTY.ordinal()){
                optionDetail.setPutAskQty(parseLong(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_NET_CHANGE.ordinal()){
                optionDetail.setPutNetChange(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_LAST_TRADED_PRICE.ordinal()){
                optionDetail.setPutLastTradedPrice(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_IMPLIED_VOLATILITY.ordinal()){
                optionDetail.setPutImpliedVolatility(parseBD(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_VOLUME.ordinal()){
                optionDetail.setPutVolume(parseLong(token));
            }

            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_OPEN_INT_CHANGE.ordinal()){
                optionDetail.setPutOpenIntChange(parseLong(token));
            }
            else if(count == OptionsConstants.MARKET_CSV_MAPPING.PUT_OPEN_INT.ordinal()){
                optionDetail.setPutOpenInt(parseLong(token));
            }
        }
        return optionDetail;
    }

    private Long parseLong(String s){
        if(s == null || s.equals("")){
            return null;
        }
        else{
            return Long.parseLong(s);
        }
    }

    private BigDecimal parseBD(String s){
        if(s == null || s.equals("")){
            return null;
        }
        else{
            return new BigDecimal(s);
        }
    }
}
