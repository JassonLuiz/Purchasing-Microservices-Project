package com.order.validador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    private JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void notifyCustomerApprovedPurchase(String email){
        var msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Approved purchase");
        msg.setText("Your purchase has been approved successfully! You will soon receive your tracking code!!");
        javaMailSender.send(msg);
        log.info("Customer notified of successfully approved purchase!!");
    }
}
