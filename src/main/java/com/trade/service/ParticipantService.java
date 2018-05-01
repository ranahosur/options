package com.trade.service;

import com.trade.model.MarketDataView;
import com.trade.model.Participant;
import com.trade.model.ParticipantTransaction;

import java.util.List;

public interface ParticipantService {

    Participant findParticipantTransactions(String userId);

    public void saveParticipantTransactions(String username, MarketDataView marketDataView);
}
