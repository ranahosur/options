package com.trade.service;

import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by raghu.anahosur on 7/28/2017.
 */
public interface EmailService {
    void sendEmail(JavaMailSender mailSender, String username, String emailaddress, String password);
}
