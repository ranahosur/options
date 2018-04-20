package com.trade.service;

import com.trade.model.User;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

/**
 * Created by raghu.anahosur on 7/28/2017.
 */
public interface EmailService {
    void sendEmail(JavaMailSender mailSender, String username, String emailaddress, String password);

    void sendEmail(JavaMailSender mailSender, User user, String emailType, String[] tokens);

}
