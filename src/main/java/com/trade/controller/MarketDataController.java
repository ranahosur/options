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


    @RequestMapping(value = "/confirmSave", method = RequestMethod.POST)
    public ModelAndView confirmSaveTransactions(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("marketDataView") MarketDataView marketDataView) {
        String username = (String)request.getSession().getAttribute("username");
        logger.debug("entry into confirmSaveTransactions");

        List<OptionDetail> optionDetails = marketDataView.getOptionDetails();
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
    public ModelAndView editPortfolio(HttpServletRequest request, @ModelAttribute("user") User user) {
        String username = (String)request.getSession().getAttribute("username");
        User existUser = userService.findUserByUsername(username);
        logger.debug("entry into editPortfolio");
        Participant participant = participantService.findParticipantTransactions(existUser.getUserId());
        logger.debug("participant id is "+  participant.getParticipantId());

        user.setParticipant(participant);
        ModelAndView mav = new ModelAndView("welcomeParticipant");
        mav.addObject("screenMode",request.getParameter("screenMode"));
        logger.debug("exit from editPortfolio");
        return mav;
    }
}
