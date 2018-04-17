package com.trade.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * Created by raghu.anahosur on 7/28/2017.
 */
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = Logger.getLogger(EmailServiceImpl.class);

//    @Autowired
//    JavaMailSender mailSender;

    public void sendEmail(JavaMailSender mailSender, String username, String emailaddress,String password) {
        logger.debug("Entry into sendEmail with username "+ username + " email "+ emailaddress);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailaddress);
        email.setSubject("Bill Payment Online|Forgot Password");
        String[] tokens = new String[3];
        tokens[0] = username;
        tokens[1] = password;
        tokens[2] = ((JavaMailSenderImpl)mailSender).getUsername();
        email.setText(formatEmailText(emailText,tokens));
        logger.debug("host email is "+ tokens[2]);
        // sends the e-mail
        mailSender.send(email);
        logger.debug("Entry into sendEmail with username "+ username + " email "+ emailaddress);
    }

    private static String emailText = "The password for your registered userid {0} is {1}.\n" +
            " \n" +
            "Please do not reply to this Email as it is generated by the system..\n" +
            "For any queries, you can contact us at {2}.\n" +
            " \n" +
            "Thanks again for choosing www.billpayment.com.\n" +
            "The easiest to use and most reliable destination for online bills payments\n" +
            " \n" +
            "Please change the password after login\n" +
            " \n" +
            "Best Regards,\n" +
            "eSeva Support Team\n" +
            "eseva.support@gmail.com";


    private String formatEmailText(final String emailInputText, final String[] messageArguments) {
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(Locale.US);
        formatter.applyPattern(emailInputText);
        return formatter.format(messageArguments);
    }
}
