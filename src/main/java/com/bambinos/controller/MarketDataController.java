package com.bambinos.controller;

import com.bambinos.dao.OptionDetailDao;
import com.bambinos.dao.LoginDAO;
import com.bambinos.model.*;
import com.bambinos.service.MarketDataService;
import com.bambinos.service.ParticipantService;
import com.bambinos.service.UserService;
import com.bambinos.util.DateUtil;
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


public class MarketDataController {
    private static final Logger logger = Logger.getLogger(SubscriptionController.class);


}
