package com.bambinos.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class ParticipantTransaction {

    private String participantTransactionId;
    private String participantId;
    private String optionDetailId;
    private String optionType;
    private BigDecimal entryPrice;
    private BigDecimal exitPrice;
    private Integer lotCount;
    private Timestamp createDate;
    private String status;
    private Date excerciseDate;
    private BigDecimal fundsInvested;
    private String result;
    private String linkedTransactionId;

    private String stockName;
    private String symbol;
    private BigDecimal currentMarketPrice;
    private BigDecimal profitLoss;

    private Date expiryDate;
    private BigDecimal strikePrice;
    private BigDecimal callBidPrice;
    private BigDecimal callAskPrice;
    private BigDecimal putBidPrice;
    private BigDecimal putAskPrice;




    public String getParticipantTransactionId() {
        return participantTransactionId;
    }

    public void setParticipantTransactionId(String participantTransactionId) {
        this.participantTransactionId = participantTransactionId;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getOptionDetailId() {
        return optionDetailId;
    }

    public void setOptionDetailId(String optionDetailId) {
        this.optionDetailId = optionDetailId;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public BigDecimal getExitPrice() {
        return exitPrice;
    }

    public void setExitPrice(BigDecimal exitPrice) {
        this.exitPrice = exitPrice;
    }



    public Integer getLotCount() {
        return lotCount;
    }

    public void setLotCount(Integer lotCount) {
        this.lotCount = lotCount;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExcerciseDate() {
        return excerciseDate;
    }

    public void setExcerciseDate(Date excerciseDate) {
        this.excerciseDate = excerciseDate;
    }

    public BigDecimal getFundsInvested() {
        return fundsInvested;
    }

    public void setFundsInvested(BigDecimal fundsInvested) {
        this.fundsInvested = fundsInvested;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLinkedTransactionId() {
        return linkedTransactionId;
    }

    public void setLinkedTransactionId(String linkedTransactionId) {
        this.linkedTransactionId = linkedTransactionId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getCurrentMarketPrice() {
        return currentMarketPrice;
    }

    public void setCurrentMarketPrice(BigDecimal currentMarketPrice) {
        this.currentMarketPrice = currentMarketPrice;
    }

    public BigDecimal getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(BigDecimal profitLoss) {
        this.profitLoss = profitLoss;
    }

    public BigDecimal getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(BigDecimal entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(BigDecimal strikePrice) {
        this.strikePrice = strikePrice;
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
}
