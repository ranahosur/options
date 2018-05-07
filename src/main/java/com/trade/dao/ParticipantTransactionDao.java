package com.trade.dao;

import com.trade.model.ParticipantTransaction;

import java.util.List;

public interface ParticipantTransactionDao {

    void createParticipantTransaction(ParticipantTransaction participantTransaction);

    void updateParticipantTransaction(ParticipantTransaction participantTransaction);

    List<ParticipantTransaction> findParticipantTransactionByParticipantId(String participantId);

    void deleteParticipantTransaction(String transacationId);

    ParticipantTransaction findParticipantTransactionById(String participantTransactionId);
}
