package com.trade.dao;

import com.trade.model.Participant;

public interface ParticipantDao {

    void createParticipant(Participant participant);

    void updateParticipant(Participant participant);

    Participant findParticipantByUserId(String userId);

    void deleteParticipant(String participantId);
    
}
