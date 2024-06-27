package com.orderconsumerms.service;

import com.orderconsumerms.model.PurchaseOrder;
import com.orderconsumerms.service.producer.ProducerOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    private JavaMailSender javaMailSender;

    private ProducerOrder producerOrder;

    public EmailService(JavaMailSender javaMailSender, ProducerOrder producerOrder){
        this.javaMailSender = javaMailSender;
        this.producerOrder = producerOrder;
    }

    public void notifyCustomer(PurchaseOrder purchaseOrder){
        var msg = new SimpleMailMessage();
        msg.setTo(purchaseOrder.getEmail());
        msg.setSubject("Purchase received");
        msg.setText("This is a purchase confirmation email received. " +
                "Now we will approve your purchase and you will shortly receive a new confirmation email. " +
                "\nThank you for shopping with us!!");
        javaMailSender.send(msg);
        log.info("Customer notified successfully!!");

        log.info("Preparing producer order...");
        producerOrder.publishMessagePurchaseOrder(purchaseOrder);
    }
}
