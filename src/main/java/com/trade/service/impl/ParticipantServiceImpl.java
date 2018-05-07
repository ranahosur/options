package com.trade.service.impl;

import com.trade.dao.*;
import com.trade.model.*;
import com.trade.service.ParticipantService;
import com.trade.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static com.trade.model.OptionsConstants.TRANSACTION_STATUS_NEW;

public class ParticipantServiceImpl implements ParticipantService {

    private static final Logger logger = Logger.getLogger(ParticipantServiceImpl.class);


    @Autowired
    public UserDao userDao;


    @Autowired
    public ParticipantDao participantDao;

    @Autowired
    public ParticipantTransactionDao participantTransactionDao;



    public Participant findParticipantTransactions(String userId) {
        Participant participant = participantDao.findParticipantByUserId(userId);

        participant.setParticipantTransactions(participantTransactionDao.findParticipantTransactionByParticipantId(participant.getParticipantId()));
        for(ParticipantTransaction participantTransaction : participant.getParticipantTransactions()){
            BigDecimal originalUnitPrice = participantTransaction.getEntryPrice();
            BigDecimal existingUnitPrice = null;
            if(participantTransaction.getOptionType().equals(OptionsConstants.OPTION_TYPE_CALL)){
                if(participantTransaction.getLotCount() < 0){
                    existingUnitPrice =  participantTransaction.getCallAskPrice();
                }
                if(participantTransaction.getLotCount() < 0){
                    existingUnitPrice =  participantTransaction.getCallBidPrice();
                }
            }
            else {
                if(participantTransaction.getLotCount() < 0){
                    existingUnitPrice =  participantTransaction.getPutAskPrice();
                }
                if(participantTransaction.getLotCount() < 0){
                    existingUnitPrice =  participantTransaction.getPutBidPrice();
                }
            }
            if(originalUnitPrice != null && existingUnitPrice != null){
                participantTransaction.setProfitLoss(existingUnitPrice.subtract(existingUnitPrice).multiply(new BigDecimal(participantTransaction.getLotCount())));
            }

        }
        return participant;
    }


    public void saveParticipantTransactions(String username, MarketDataView marketDataView) {
        List<OptionDetail> optionDetails = marketDataView.getOptionDetails();
        User user = userDao.findUser(username);
        Participant participant = participantDao.findParticipantByUserId(user.getUserId());

        for(OptionDetail optionDetail : optionDetails){
            if(optionDetail.getCallLotInput() != 0){
                ParticipantTransaction participantTransaction = populateParticipantTransaction(optionDetail,participant,OptionsConstants.OPTION_TYPE_CALL);
                participantTransactionDao.createParticipantTransaction(participantTransaction);
            }
            if(optionDetail.getPutLotInput() != 0){
                ParticipantTransaction participantTransaction = populateParticipantTransaction(optionDetail,participant,OptionsConstants.OPTION_TYPE_PUT);
                participantTransactionDao.createParticipantTransaction(participantTransaction);
            }
        }
    }

    private ParticipantTransaction populateParticipantTransaction(OptionDetail optionDetail,Participant participant, String optionType) {
        logger.debug("Entry into populateParticipantTransaction");
        ParticipantTransaction participantTransaction = new ParticipantTransaction();
        participantTransaction.setParticipantId(participant.getParticipantId());
        participantTransaction.setOptionDetailId(optionDetail.getOptionDetailId());
        participantTransaction.setOptionType(optionType);
        if(OptionsConstants.OPTION_TYPE_CALL.equals(optionType)){
            if(optionDetail.getCallLotInput() > 0){

                participantTransaction.setEntryPrice(optionDetail.getCallAskPrice());
                logger.debug("Entry price for Call Buy is "+participantTransaction.getEntryPrice());
            }
            else if(optionDetail.getCallLotInput() < 0){
                participantTransaction.setEntryPrice(optionDetail.getCallBidPrice());
                logger.debug("Entry price for Call Sell is "+participantTransaction.getEntryPrice());
            }
            participantTransaction.setLotCount(optionDetail.getCallLotInput());
            logger.debug("lot Count for Call "+ participantTransaction.getLotCount());
        }
        else {
            if(optionDetail.getPutLotInput() > 0){
                participantTransaction.setEntryPrice(optionDetail.getPutAskPrice());
                logger.debug("Entry price for Put Buy is "+participantTransaction.getEntryPrice());
            }
            else if(optionDetail.getPutLotInput() < 0){
                participantTransaction.setEntryPrice(optionDetail.getPutBidPrice());
                logger.debug("Entry price for Put Sell is "+participantTransaction.getEntryPrice());
            }
            participantTransaction.setLotCount(optionDetail.getPutLotInput());
            logger.debug("lot Count for Put "+ participantTransaction.getLotCount());
        }
        participantTransaction.setStrikePrice(optionDetail.getStrikePrice());
        participantTransaction.setExpiryDate(optionDetail.getExpiryDate());
        participantTransaction.setStatus(TRANSACTION_STATUS_NEW);
        logger.debug("Entry price -> "+ participantTransaction.getEntryPrice() + " ::  lot count -> "+ participantTransaction.getLotCount());
        if(participantTransaction.getEntryPrice() != null && participantTransaction.getLotCount() != null) {
            participantTransaction.setFundsInvested(participantTransaction.getEntryPrice().multiply(new BigDecimal(participantTransaction.getLotCount())));
        }
        else{
            participantTransaction.setFundsInvested(new BigDecimal("0.0"));
        }
        logger.debug("Exit from populateParticipantTransaction");
        return participantTransaction;
    }

    public ParticipantTransaction findParticipantTransactionById(String participantTransactionId) {
        return participantTransactionDao.findParticipantTransactionById(participantTransactionId);
    }
}
