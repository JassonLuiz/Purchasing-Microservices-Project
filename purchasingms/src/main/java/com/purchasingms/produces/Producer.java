package com.purchasingms.produces;

import com.purchasingms.model.PurchaseOrder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;


@Component
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;


    public Producer(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @PostMapping
    public void publishMessagePurchaseOrder(PurchaseOrder purchaseOrder){
        rabbitTemplate.convertAndSend(queue.getName(), purchaseOrder);
    }

}
