package com.trade.controller;

import com.trade.dao.AdminPrivilegeDao;
import com.trade.dao.OptionDetailDao;
import com.trade.dao.UserDao;
import com.trade.model.*;
import com.trade.service.MarketDataService;
import com.trade.service.ParticipantService;
import com.trade.service.UserService;
import com.trade.util.CountryUtil;
import com.trade.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MarketDataController extends LoginRegistrationBaseController {
    private static final Logger logger = Logger.getLogger(SubscriptionController.class);

    @Autowired
    public UserService userService;

    @Autowired
    public UserDao userDao;

    @Autowired
    public OptionDetailDao optionDetailDao;

    @Autowired
    public MarketDataService marketDataService;

    @Autowired
    public ParticipantService participantService;


    @RequestMapping(value = "/inputFileMarket", method = RequestMethod.POST)
    public ModelAndView inputFiledMarket(HttpServletRequest request, Model model) {
        AdminPrivilege adminPrivilege = new AdminPrivilege();
        adminPrivilege.setUser(new User());
        String username = (String)request.getSession().getAttribute("username");
        adminPrivilege.setUsername(username);
        logger.debug("addSubscription username from req is "+ username);
        ModelAndView mav = new ModelAndView("uploadMarket");

        model.addAttribute("user", userService.findUserByUsername((String)request.getSession().getAttribute("username")));
        return mav;
    }


    @RequestMapping(value = "/manageMarket", method = RequestMethod.POST)
    public ModelAndView manageMarket(HttpServletRequest request , @ModelAttribute("marketDataView") MarketDataView marketDataView) {
        logger.debug("Entry into manageMarket - selectedDate -> "+ marketDataView.getSelectedDate() + " : selectedStock -> "+ marketDataView.getSelectedStock());
        AdminPrivilege adminPrivilege = new AdminPrivilege();
        adminPrivilege.setUser(new User());
        String username = (String)request.getSession().getAttribute("username");
        adminPrivilege.setUsername(username);
        User user = userService.findUserByUsername(username);
        boolean isParticipant = OptionsConstants.ROLE_TEAM.equals(user.getUserRole().getRole());

        logger.debug("addSubscription username from req is "+ username);
        ModelAndView mav = new ModelAndView("manageMarket");
        if(isParticipant){
            mav.addObject("participantRole","true");
        }
        mav.addObject("adminPrivilege",adminPrivilege);


        mav.addObject("user", userService.findUserByUsername((String)request.getSession().getAttribute("username")));
        List<Stock> stocks = optionDetailDao.findAllStocks();
        mav.addObject("stocks",stocks);
        String selectedStock = request.getParameter("selectedStock");

        if((selectedStock == null || selectedStock.equals(""))){
            //Initial screen, assign the  first stock as selected to give some good screen to user and assign first exp date
            if(stocks != null && stocks.size()> 0){
                selectedStock = stocks.get(0).getSymbol();
//                mav.addObject("selectedStock",selectedStock);
                marketDataView.setSelectedStock(selectedStock);
                logger.debug("added selectedStock "+ selectedStock + " since this was the initial screen with nothing selected");
                List<ExpiryDate> expiryDates = optionDetailDao.findAllExpiryDates(selectedStock);
                mav.addObject("expiryDates",expiryDates);
                String selectedDate = expiryDates.get(0).getExpiryDate();
//                mav.addObject("selectedDate",selectedDate);
                marketDataView.setSelectedDate(selectedDate);
                setOptionDetails( marketDataView,  selectedDate,  selectedStock,request);
            }
        }
        else if(!selectedStock.equals("select")) {
            logger.debug("added selectedStock "+ selectedStock + " since this was selected by user");
            List<ExpiryDate> expiryDates = optionDetailDao.findAllExpiryDates(selectedStock);
            mav.addObject("expiryDates",expiryDates);
//            mav.addObject("selectedStock",selectedStock);
            marketDataView.setSelectedStock(selectedStock);
            String selectedDate = request.getParameter("selectedDate");

            if(selectedDate != null && !selectedDate.equals("")  && !selectedDate.equals("select")){
//                mav.addObject("selectedDate",selectedDate);
                marketDataView.setSelectedDate(selectedDate);
                logger.debug("added selectedDate "+ selectedDate + " since this was selected by user");
            }
            else{
                //add first day as selected if the stock was chosen
                selectedDate = expiryDates.get(0).getExpiryDate();
//                mav.addObject("selectedDate",selectedDate);
                marketDataView.setSelectedDate(selectedDate);
                logger.debug("added selectedDate "+ selectedDate + " since stock was selected by user, hence added the first date");
            }
            setOptionDetails( marketDataView,  selectedDate,  selectedStock,request);
        }
        else{
            //select option was chosen by user, so no details to be displayed
//            mav.addObject("selectedStock","select");
            marketDataView.setSelectedStock("select");
//            mav.addObject("selectedDate","select");
            marketDataView.setSelectedDate("select");
            logger.debug("added selectedDate as select along with stock since no-stock=select was selected by user");
            mav.addObject("optionDetails",new ArrayList<OptionDetail>());
        }

        return mav;
    }



    private void setOptionDetails(MarketDataView marketDataView,String  selectedDate, String selectedStock,HttpServletRequest request) {
        String screen = request.getParameter("screen");
        Date selectedSqlExpDate = DateUtil.parseDateString(selectedDate, OptionsConstants.DATE_FORMAT_YMD);
        List<OptionDetail> optionDetails = optionDetailDao.findOptionDetailBySymbolExpiryDate(selectedStock, selectedSqlExpDate);
        //if coming from Edit screen, set the chosen lot inputs to the form model
        logger.debug("Screen parameter is "+ screen);
        if("edit".equals(screen)) {
            List<OptionDetail> optionDetailsIncoming = marketDataView.getOptionDetails();
            for(OptionDetail optionDetailIncoming : optionDetailsIncoming){
                for(OptionDetail optionDetailInner : optionDetails){
                    if(optionDetailInner.getOptionDetailId().equals(optionDetailIncoming.getOptionDetailId())){
                        logger.debug("Setting for id "+optionDetailIncoming.getOptionDetailId() + " optionDetailIncoming.getCallLotInput() "+optionDetailIncoming.getCallLotInput());
                        optionDetailInner.setCallLotInput(optionDetailIncoming.getCallLotInput());
                        optionDetailInner.setPutLotInput(optionDetailIncoming.getPutLotInput());
                    }
                }
            }
        }
        marketDataView.setOptionDetails(optionDetails);
    }

    @RequestMapping(value = "/uploadMarket", method = RequestMethod.POST)
    public ModelAndView uploadMarket(HttpServletRequest request,
                               @RequestParam("file") MultipartFile file, Model model) {
        AdminPrivilege adminPrivilege = new AdminPrivilege();
        adminPrivilege.setUser(new User());
        String username = (String)request.getSession().getAttribute("username");
        adminPrivilege.setUsername(username);
        logger.debug("addSubscription username from req is "+ username);
        ModelAndView mav = new ModelAndView("uploadMarket");
        model.addAttribute("adminPrivilege",adminPrivilege);
        model.addAttribute("user", userService.findUserByUsername((String)request.getSession().getAttribute("username")));
        if (!file.isEmpty()) {

            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                logger.debug("rootPath is "+ rootPath + "  file.getName() is "+  file.getName());
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String fullPilePath = dir.getAbsolutePath() + File.separator + file.getName();
                // Create the file on server
                File serverFile = new File(fullPilePath);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                marketDataService.uploadMarketData(fullPilePath);
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
                model.addAttribute("message","Uploaded file succesfully");
                return mav;
            } catch (Exception e) {
                model.addAttribute("message","File errored while uploading");
                return mav;
            }
        } else {
            return mav;
        }




    }

    @RequestMapping(value = "/welcomeParticipant", method = RequestMethod.POST)
    public ModelAndView cancelSave(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("marketDataView") MarketDataView marketDataView) {
        return findModelForWelcomeParticipant(request);
    }

    @RequestMapping(value = "/saveMarket", method = RequestMethod.POST)
    public ModelAndView saveMarket(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("marketDataView") MarketDataView marketDataView) {
        String username = (String)request.getSession().getAttribute("username");
        logger.debug("Entry into saveMarket for username "+ username);
         participantService.saveParticipantTransactions(username,marketDataView);
        logger.debug("Exit from saveMarket for username "+ username);
        return findModelForWelcomeParticipant(request);
    }

    @RequestMapping(value="/stockPrice",method = RequestMethod.GET)
    public ModelAndView showStockPrice(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("stockPrice");
        return mav;
    }


    @RequestMapping(value = "/confirmSave", method = RequestMethod.POST)
    public ModelAndView confirmSaveTransactions(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("marketDataView") MarketDataView marketDataView) {
        logger.debug("entry into confirmSaveTransactions");
        String screenMode = request.getParameter("screenMode");
        marketDataView.setPreviousScreen(screenMode);
        List<OptionDetail> optionDetails = new ArrayList<OptionDetail>();
        //if coming from editPortFolio screen, then choose the ones that were actually modified by the participant
        if(screenMode != null && screenMode.equals("editPortfolio")){
            List<OptionDetail> incomingOptionDetails = marketDataView.getOptionDetails();
            for(OptionDetail optionDetail : incomingOptionDetails){
                logger.debug("checking participanttransactionid "+ optionDetail.getParticipantTransactionId());
                ParticipantTransaction existPT = participantService.findParticipantTransactionById(optionDetail.getParticipantTransactionId());
                int lotCount = existPT.getLotCount();
                if(OptionsConstants.OPTION_TYPE_CALL.equals(optionDetail.getOptionType())){
                    if(optionDetail.getCallLotInput() != lotCount){
                        logger.debug("participanttransactionid "+ optionDetail.getParticipantTransactionId() + " call lotCount has changed to "+optionDetail.getCallLotInput() + " from "+ lotCount);
                        optionDetail.setCallLotInput( optionDetail.getCallLotInput() - lotCount );
                        logger.debug("callLotInput set as " + optionDetail.getCallLotInput() + " putLotInput is "+ optionDetail.getPutLotInput());
                        optionDetails.add(optionDetail);
                    }
                    else{
                        logger.debug("participanttransactionid "+ optionDetail.getParticipantTransactionId() + " call lotCount has not changed");
                    }
                }
                else{
                    if(optionDetail.getPutLotInput() != lotCount){
                        logger.debug("participanttransactionid "+ optionDetail.getParticipantTransactionId() + " put lotCount has changed to "+optionDetail.getPutLotInput() + " from "+ lotCount);
                        optionDetail.setPutLotInput(optionDetail.getPutLotInput() - lotCount);
                        logger.debug("putLotInput set as " + optionDetail.getPutLotInput() + " callLotInput is "+optionDetail.getCallLotInput());
                        optionDetails.add(optionDetail);
                    }
                    else{
                        logger.debug("participanttransactionid "+ optionDetail.getParticipantTransactionId() + " put lotCount has not changed");
                    }
                }
            }
        }
        else {
            optionDetails = marketDataView.getOptionDetails();
        }
        marketDataView.setOptionDetails(optionDetails);
        if(optionDetails == null || optionDetails.size() == 0){
            logger.debug("no option details present!!!");
        }
        for(OptionDetail optionDetail : optionDetails){
            logger.debug(" optionDetail id "+ optionDetail.getOptionDetailId() + " callLotInput "+ optionDetail.getCallLotInput() + " call bid price "+ optionDetail.getCallBidPrice());
        }
        ModelAndView mav = new ModelAndView("confirmSave");
        logger.debug("exit from confirmSaveTransactions");
        return mav;
    }

    @RequestMapping(value = "/editPortfolio", method = RequestMethod.POST)
    public ModelAndView editPortfolio(HttpServletRequest request, @ModelAttribute("marketDataView") MarketDataView marketDataView) {
        String username = (String)request.getSession().getAttribute("username");
        User existUser = userService.findUserByUsername(username);
        String screenMode = request.getParameter("screenMode");
        marketDataView.setPreviousScreen(screenMode);

        //screenMode indicates where it is routed from. If it came from confirmSave, then merge the data from the confirmSave screen to show the original
        //input entered by user
        logger.debug("entry into editPortfolio with screenMode "+screenMode);
        Participant participant = participantService.findParticipantTransactions(existUser.getUserId());
        logger.debug("participant id is "+  participant.getParticipantId() + " number of transactions "+ participant.getParticipantTransactions().size());
         List<ParticipantTransaction> participantTransactions = participant.getParticipantTransactions();
         List<OptionDetail> optionDetails = new ArrayList<OptionDetail>();
         for(ParticipantTransaction participantTransaction : participantTransactions){

            if("confirmSave".equals(screenMode)){
                List<OptionDetail> incomingODs = marketDataView.getOptionDetails();
                for(OptionDetail incomingOD : incomingODs){

                    if(incomingOD.getParticipantTransactionId().equals(participantTransaction.getParticipantTransactionId())){
                        logger.debug("incomingOD has PTid "+ incomingOD.getParticipantTransactionId() + " optionType "+incomingOD.getOptionType() + " callLotInput "+incomingOD.getCallLotInput() + " putLotInput " +
                        + incomingOD.getPutLotInput() + " PT lot count "+ participantTransaction.getLotCount());
                        if(OptionsConstants.OPTION_TYPE_CALL.equals(incomingOD.getOptionType())){
                            participantTransaction.setLotCount(participantTransaction.getLotCount() + incomingOD.getCallLotInput());
                        }
                        else{
                            participantTransaction.setLotCount(participantTransaction.getLotCount() + incomingOD.getPutLotInput());
                        }
                        logger.debug("after setting - incomingOD has PTid "+ incomingOD.getParticipantTransactionId() + " optionType "+incomingOD.getOptionType() + " callLotInput "+incomingOD.getCallLotInput() + " putLotInput " +
                                + incomingOD.getPutLotInput() + " PT lot count "+ participantTransaction.getLotCount());
                    }
                }
            }
             optionDetails.add(convertToOptionDetail(participantTransaction));
         }
         logger.debug("no of optiondetails "+ optionDetails.size());
        marketDataView.setOptionDetails(optionDetails);
        ModelAndView mav = new ModelAndView("editPortfolio");
        mav.addObject("screenMode",request.getParameter("screenMode"));
        logger.debug("exit from editPortfolio");
        return mav;
    }

    @RequestMapping(value = "/cancelPortfolioEdit", method = RequestMethod.POST)
    public ModelAndView cancelPortfolioEdit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
        return findModelForWelcomeParticipant(request);
    }

    private OptionDetail convertToOptionDetail(ParticipantTransaction participantTransaction){
        logger.debug("converting id : "+ participantTransaction.getParticipantTransactionId());
        OptionDetail optionDetail = new OptionDetail();
        optionDetail.setStockPrice(participantTransaction.getCurrentMarketPrice());
        optionDetail.setStrikePrice(participantTransaction.getStrikePrice());
        optionDetail.setCallBidPrice(participantTransaction.getCallBidPrice());
        optionDetail.setParticipantTransactionId(participantTransaction.getParticipantTransactionId());
        optionDetail.setCallAskPrice(participantTransaction.getCallAskPrice());
        optionDetail.setPutAskPrice(participantTransaction.getPutAskPrice());
        optionDetail.setPutBidPrice(participantTransaction.getPutBidPrice());
        optionDetail.setOptionDetailId(participantTransaction.getOptionDetailId());
        optionDetail.setExpiryDate(participantTransaction.getExpiryDate());
        optionDetail.setEntryPrice(participantTransaction.getEntryPrice());
        optionDetail.setOptionType(participantTransaction.getOptionType());
        if(OptionsConstants.OPTION_TYPE_CALL.equals(participantTransaction.getOptionType())){
            optionDetail.setCallLotInput(participantTransaction.getLotCount());
        }
        else{
            optionDetail.setPutLotInput(participantTransaction.getLotCount());
        }
        optionDetail.setName(participantTransaction.getStockName());
        optionDetail.setSymbol(participantTransaction.getSymbol());
        logger.debug("optiondetailid "+ optionDetail.getOptionDetailId() + " call Lot "+ optionDetail.getCallLotInput() + " put lot "+ optionDetail.getPutLotInput());
        logger.debug("exiting id : "+ participantTransaction.getParticipantTransactionId());
        return optionDetail;
    }
}
