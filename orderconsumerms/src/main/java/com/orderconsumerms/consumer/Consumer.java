package com.orderconsumerms.consumer;

import com.orderconsumerms.model.PurchaseOrder;
import com.orderconsumerms.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    private final EmailService emailService;

    public Consumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = {"${broker.queue.purchase.name}"})
    public void consumer(@Payload PurchaseOrder purchaseOrder){
        log.info("Request received: {}", purchaseOrder);
        emailService.notifyCustomer(purchaseOrder.getEmail());
    }
}
