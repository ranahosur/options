package com.bambinos;

import com.bambinos.dao.*;
import com.bambinos.model.*;
import com.bambinos.service.ParticipantService;
import com.bambinos.service.UserService;
import com.bambinos.util.DateUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Random;

import static com.bambinos.model.OptionsConstants.TRANSACTION_RESULT_LOSS;
import static com.bambinos.model.OptionsConstants.TRANSACTION_STATUS_NEW;
import static junit.framework.TestCase.assertTrue;

@Ignore
@ContextConfiguration(locations = { "classpath:/bambinos/config/user-beans.xml" })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ParticipantServiceTest {

    public static final String testSymbol = "test002";
    @Autowired
    UserService userService;

    @Autowired
    public LoginDAO loginDAO;

    @Autowired
    public UserRoleDao userRoleDao;

    @Autowired
    public RoleDao roleDao;

    @Autowired
    public TeamDao teamDao;

    @Autowired
    public TeamMemberDao teamMemberDao;

    @Autowired
    public OptionDetailDao optionDetailDao;

    @Autowired
    public ParticipantDao participantDao;

    @Autowired
    public ParticipantTransactionDao participantTransactionDao;

    @Autowired
    public ParticipantService participantService;

    @Test
    public void testOptions() {
        OptionDetail optionDetail = new OptionDetail();
        optionDetail.setSymbol(testSymbol);
        optionDetail.setStrikePrice(new BigDecimal("100.0"));
        optionDetail.setStockPrice(new BigDecimal("120.0"));
        optionDetail.setName("Test Stock");
        optionDetail.setExpiryDate(new Date(DateUtil.parseYYYYMMdd("20180424").getTime()));
        optionDetail.setCallVolume(new Long(100));
        optionDetail.setCallOpenIntChange(new Long(5));
        optionDetail.setCallOpenInt(new Long(30));
        optionDetail.setCallBidQty(new Long(200));
        optionDetail.setCallBidQty(new Long(100));
        optionDetail.setCallBidPrice(new BigDecimal("190.1"));
        optionDetail.setCallAskQty(new Long(10));
        optionDetail.setCallAskPrice(new BigDecimal("137.23"));
        optionDetail.setPutAskPrice(new BigDecimal("120.98"));
        optionDetail.setPutAskQty(new Long(90));
        optionDetail.setPutBidPrice(new BigDecimal("107.23"));
        optionDetail.setPutBidQty(new Long(98));
        optionDetail.setPutBidPrice(new BigDecimal("134.12"));
        optionDetail.setPutOpenInt(new Long(33));
        optionDetail.setPutOpenIntChange(new Long(7));
        optionDetail.setPutVolume(new Long(12));

        List<OptionDetail> optionDetails = optionDetailDao.findOptionDetailBySymbol(testSymbol);
        if(optionDetails != null && optionDetails.size() > 0){
            for(OptionDetail myOpt : optionDetails){
                optionDetailDao.deleteOptionDetail(myOpt.getOptionDetailId());
            }
        }
        optionDetailDao.createOptionDetail(optionDetail);
        optionDetails = optionDetailDao.findOptionDetailBySymbol(testSymbol);
       assertTrue(optionDetails.size() > 0);

    }

    @Test
    public void testParticipant() {
        User user = null;//loginDAO.findUserByEmail("ranahosur@gmail.com");
        Participant participantExist = participantDao.findParticipantByUserId(user.getUserId());
        if(participantExist != null){
            participantDao.deleteParticipant(participantExist.getParticipantId());
        }
        Participant participant = new Participant();
        participant.setUserId(user.getUserId());
        participant.setTotalFunds(new BigDecimal("200000"));
        participantDao.createParticipant(participant);

        assertTrue(participantDao.findParticipantByUserId(user.getUserId()) != null);

    }

    @Test
    public void testParticipantTransaction() {
        User user = null;//loginDAO.findUserByEmail("ranahosur@gmail.com");
        Participant participantExist = participantDao.findParticipantByUserId(user.getUserId());

        List<ParticipantTransaction> participantTransactionsExist = participantTransactionDao.findParticipantTransactionByParticipantId(participantExist.getParticipantId());
        if (participantTransactionsExist != null && participantTransactionsExist.size() > 0) {
            for(ParticipantTransaction pt : participantTransactionsExist) {
//                participantTransactionDao.deleteParticipantTransaction(pt.getParticipantTransactionId());
            }
        }

        Random random = new Random();

        ParticipantTransaction participantTransaction = new ParticipantTransaction();
        participantTransaction.setParticipantId(participantExist.getParticipantId());
        participantTransaction.setExcerciseDate(DateUtil.currentDate());
        participantTransaction.setFundsInvested( new BigDecimal( Math.random())); //"124834"));
        participantTransaction.setLotCount(random.nextInt());
        participantTransaction.setOptionDetailId(optionDetailDao.findOptionDetailBySymbol(testSymbol).get(0).getOptionDetailId());
        participantTransaction.setOptionType(OptionsConstants.OPTION_TYPE_CALL);
        participantTransaction.setResult(TRANSACTION_RESULT_LOSS);
        participantTransaction.setStatus(TRANSACTION_STATUS_NEW);
        participantTransactionDao.createParticipantTransaction(participantTransaction);

        assertTrue(participantTransactionDao.findParticipantTransactionByParticipantId(participantExist.getParticipantId()).size() > 0);

    }


    @Test
    public void testFindTrans() {
        User user = null;//loginDAO.findUserByEmail("ranahosur@gmail.com");
        Participant participant = participantService.findParticipantTransactions(user.getUserId());
        assertTrue(participant.getParticipantTransactions().size() > 0);
    }
}
