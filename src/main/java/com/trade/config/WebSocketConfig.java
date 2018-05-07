package com.trade.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableScheduling
@ComponentScan("com.trade")
@EnableWebSocketMessageBroker

public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    private static final Logger logger = Logger.getLogger(WebSocketConfig.class);

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        logger.debug("Entry into registerStompEndpoints for portfolio ");
         registry.addEndpoint("/portfolio").withSockJS();
        registry.addEndpoint("/optionsPortfolio").withSockJS();
        logger.debug("exit from registerStompEndpoints for portfolio ");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        logger.debug("Entry into configureMessageBroker for portfolio ");
        registry.enableSimpleBroker("/queue/", "/topic/");
//		registry.enableStompBrokerRelay("/queue/", "/topic/");
        registry.setApplicationDestinationPrefixes("/app");
        logger.debug("exit from configureMessageBroker for portfolio ");
    }

}
