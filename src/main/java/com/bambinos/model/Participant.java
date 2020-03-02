package com.bambinos.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Participant {

    private String participantId;
    private String userId;
    private BigDecimal totalFunds;
    private Timestamp createDate;

    List<ParticipantTransaction> participantTransactions;

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalFunds() {
        return totalFunds;
    }

    public void setTotalFunds(BigDecimal totalFunds) {
        this.totalFunds = totalFunds;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public List<ParticipantTransaction> getParticipantTransactions() {
        return participantTransactions;
    }

    public void setParticipantTransactions(List<ParticipantTransaction> participantTransactions) {
        this.participantTransactions = participantTransactions;
    }
}
