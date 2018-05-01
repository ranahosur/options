package com.trade.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class OptionDetail {

    private String optionDetailId;
    private String name;
    private String symbol;
    private BigDecimal stockPrice;
    private BigDecimal strikePrice;
    private Date expiryDate;
    private BigDecimal callBidPrice;
    private BigDecimal callAskPrice;
    private Long callVolume;
    private Long callOpenInt;
    private Long callOpenIntChange;
    private Long callAskQty;
    private Long callBidQty;
    private BigDecimal putBidPrice;
    private BigDecimal putAskPrice;
    private Long putVolume;
    private Long putOpenInt;
    private Long putOpenIntChange;
    private Long putAskQty;
    private Long putBidQty;
    private Timestamp createDate;
    private BigDecimal callLastTradedPrice;
    private  BigDecimal callImpliedVolatility;
    private BigDecimal callNetChange;
    private BigDecimal putLastTradedPrice;
    private  BigDecimal putImpliedVolatility;
    private BigDecimal putNetChange;

    private int callLotInput;
    private int putLotInput;

    public String getOptionDetailId() {
        return optionDetailId;
    }

    public void setOptionDetailId(String optionDetailId) {
        this.optionDetailId = optionDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(BigDecimal stockPrice) {
        this.stockPrice = stockPrice;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(BigDecimal strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getCallBidPrice() {
        return callBidPrice;
    }

    public void setCallBidPrice(BigDecimal callBidPrice) {
        this.callBidPrice = callBidPrice;
    }

    public BigDecimal getCallAskPrice() {
        return callAskPrice;
    }

    public void setCallAskPrice(BigDecimal callAskPrice) {
        this.callAskPrice = callAskPrice;
    }

    public Long getCallVolume() {
        return callVolume;
    }

    public void setCallVolume(Long callVolume) {
        this.callVolume = callVolume;
    }

    public Long getCallOpenInt() {
        return callOpenInt;
    }

    public void setCallOpenInt(Long callOpenInt) {
        this.callOpenInt = callOpenInt;
    }

    public Long getCallOpenIntChange() {
        return callOpenIntChange;
    }

    public void setCallOpenIntChange(Long callOpenIntChange) {
        this.callOpenIntChange = callOpenIntChange;
    }

    public Long getCallAskQty() {
        return callAskQty;
    }

    public void setCallAskQty(Long callAskQty) {
        this.callAskQty = callAskQty;
    }

    public Long getCallBidQty() {
        return callBidQty;
    }

    public void setCallBidQty(Long callBidQty) {
        this.callBidQty = callBidQty;
    }

    public BigDecimal getPutBidPrice() {
        return putBidPrice;
    }

    public void setPutBidPrice(BigDecimal putBidPrice) {
        this.putBidPrice = putBidPrice;
    }

    public BigDecimal getPutAskPrice() {
        return putAskPrice;
    }

    public void setPutAskPrice(BigDecimal putAskPrice) {
        this.putAskPrice = putAskPrice;
    }

    public Long getPutVolume() {
        return putVolume;
    }

    public void setPutVolume(Long putVolume) {
        this.putVolume = putVolume;
    }

    public Long getPutOpenInt() {
        return putOpenInt;
    }

    public void setPutOpenInt(Long putOpenInt) {
        this.putOpenInt = putOpenInt;
    }

    public Long getPutOpenIntChange() {
        return putOpenIntChange;
    }

    public void setPutOpenIntChange(Long putOpenIntChange) {
        this.putOpenIntChange = putOpenIntChange;
    }

    public Long getPutAskQty() {
        return putAskQty;
    }

    public void setPutAskQty(Long putAskQty) {
        this.putAskQty = putAskQty;
    }

    public Long getPutBidQty() {
        return putBidQty;
    }

    public void setPutBidQty(Long putBidQty) {
        this.putBidQty = putBidQty;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getCallLastTradedPrice() {
        return callLastTradedPrice;
    }

    public void setCallLastTradedPrice(BigDecimal callLastTradedPrice) {
        this.callLastTradedPrice = callLastTradedPrice;
    }

    public BigDecimal getCallImpliedVolatility() {
        return callImpliedVolatility;
    }

    public void setCallImpliedVolatility(BigDecimal callImpliedVolatility) {
        this.callImpliedVolatility = callImpliedVolatility;
    }

    public BigDecimal getCallNetChange() {
        return callNetChange;
    }

    public void setCallNetChange(BigDecimal callNetChange) {
        this.callNetChange = callNetChange;
    }

    public BigDecimal getPutLastTradedPrice() {
        return putLastTradedPrice;
    }

    public void setPutLastTradedPrice(BigDecimal putLastTradedPrice) {
        this.putLastTradedPrice = putLastTradedPrice;
    }

    public BigDecimal getPutImpliedVolatility() {
        return putImpliedVolatility;
    }

    public void setPutImpliedVolatility(BigDecimal putImpliedVolatility) {
        this.putImpliedVolatility = putImpliedVolatility;
    }

    public BigDecimal getPutNetChange() {
        return putNetChange;
    }

    public void setPutNetChange(BigDecimal putNetChange) {
        this.putNetChange = putNetChange;
    }

    public int getCallLotInput() {
        return callLotInput;
    }

    public void setCallLotInput(int callLotInput) {
        this.callLotInput = callLotInput;
    }

    public int getPutLotInput() {
        return putLotInput;
    }

    public void setPutLotInput(int putLotInput) {
        this.putLotInput = putLotInput;
    }
}
