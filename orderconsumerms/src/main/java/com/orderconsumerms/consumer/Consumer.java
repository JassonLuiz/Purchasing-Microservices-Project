package com.orderconsumerms.consumer;

import com.orderconsumerms.model.PurchaseOrder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = {"${broker.queue.purchase.name}"})
    public void consumer(@Payload PurchaseOrder purchaseOrder){
        System.out.println("Mensagem recebida: " + purchaseOrder.toString());
    }
}
