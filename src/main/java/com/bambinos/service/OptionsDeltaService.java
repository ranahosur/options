package com.bambinos.service;

import com.bambinos.model.OptionDelta;
import com.bambinos.model.OptionDetail;
import com.bambinos.util.PriceUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class OptionsDeltaService   {

    private static final Logger logger = Logger.getLogger(OptionsDeltaService.class);






}
