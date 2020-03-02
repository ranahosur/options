package com.bambinos.model;

import java.math.BigDecimal;

public class OptionDelta {
    private String optionDetailId;
    private BigDecimal stockPrice;
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
    private BigDecimal callLastTradedPrice;
    private  BigDecimal callImpliedVolatility;
    private BigDecimal callNetChange;
    private BigDecimal putLastTradedPrice;
    private  BigDecimal putImpliedVolatility;
    private BigDecimal putNetChange;

    public String getOptionDetailId() {
        return optionDetailId;
    }

    public void setOptionDetailId(String optionDetailId) {
        this.optionDetailId = optionDetailId;
    }

    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(BigDecimal stockPrice) {
        this.stockPrice = stockPrice;
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

    @Override
    public String toString() {
        return "OptionDelta [" +
                "optionDetailId='" + optionDetailId +
                ", stockPrice=" + stockPrice +
                ", callBidPrice=" + callBidPrice +
                ", callAskPrice=" + callAskPrice +
                ", callVolume=" + callVolume +
                ", callOpenInt=" + callOpenInt +
                ", callOpenIntChange=" + callOpenIntChange +
                ", callAskQty=" + callAskQty +
                ", callBidQty=" + callBidQty +
                ", putBidPrice=" + putBidPrice +
                ", putAskPrice=" + putAskPrice +
                ", putVolume=" + putVolume +
                ", putOpenInt=" + putOpenInt +
                ", putOpenIntChange=" + putOpenIntChange +
                ", putAskQty=" + putAskQty +
                ", putBidQty=" + putBidQty +
                ", callLastTradedPrice=" + callLastTradedPrice +
                ", callImpliedVolatility=" + callImpliedVolatility +
                ", callNetChange=" + callNetChange +
                ", putLastTradedPrice=" + putLastTradedPrice +
                ", putImpliedVolatility=" + putImpliedVolatility +
                ", putNetChange=" + putNetChange +
                ']';
    }
}
