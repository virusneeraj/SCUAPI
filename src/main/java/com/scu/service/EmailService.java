package com.scu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    public boolean sendEmail(String to, String subject, String body) {
        System.out.println("sendEmail TO:" + to + " SUBJECT:" + subject);
        boolean isEmailSend = false;

        //String username = PropUtil.getSetting("EMAIL_SERVICE_USERNAME");
        //String password = PropUtil.getSetting("EMAIL_SERVICE_PASSWORD");

        // username = StringUtils.newStringUtf8(Base64.decodeBase64(username));
        //password = StringUtils.newStringUtf8(Base64.decodeBase64(password));

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        final String finalUsername = "gaur.salim@gmail.com";
        final String finalPassword = "Hays@12345";
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(finalUsername, finalPassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            // message.setFrom(new InternetAddress(PropUtil.getSetting("EMAIL_SERVICE_FROM")));
            message.setFrom(new InternetAddress("gaur.salim@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            isEmailSend = true;

        } catch (MessagingException e) {
            logger.error("SendEmail", e);
        }
        return isEmailSend;
    }
}