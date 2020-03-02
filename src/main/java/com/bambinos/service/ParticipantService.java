package com.bambinos.service;

import com.bambinos.model.MarketDataView;
import com.bambinos.model.Participant;
import com.bambinos.model.ParticipantTransaction;

public interface ParticipantService {

    Participant findParticipantTransactions(String userId);

    void saveParticipantTransactions(String username, MarketDataView marketDataView);

    ParticipantTransaction findParticipantTransactionById(String participantTransactionId);

}
