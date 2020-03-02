package com.bambinos.service;

import com.bambinos.model.User;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by raghu.anahosur on 7/28/2017.
 */
public interface EmailService {
    void sendEmail(JavaMailSender mailSender, String username, String emailaddress, String password);

    void sendEmail(JavaMailSender mailSender, User user, String emailType, String[] tokens);

}
