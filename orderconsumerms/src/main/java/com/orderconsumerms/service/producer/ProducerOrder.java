package com.orderconsumerms.service.producer;

import com.orderconsumerms.model.Card;
import com.orderconsumerms.model.PurchaseOrder;
import com.orderconsumerms.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;


@Component
public class ProducerOrder {

    private final Logger log = LoggerFactory.getLogger(ProducerOrder.class);

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    private final CardService cardService;

    public ProducerOrder(RabbitTemplate rabbitTemplate, Queue queue, CardService cardService) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.cardService = cardService;
    }

    @PostMapping
    public void publishMessagePurchaseOrder(PurchaseOrder purchaseOrder){
        purchaseOrder.setCard(Card.builder()
                        .num(cardService.generateCard())
                        .availableLimit(cardService.generateLimit())
                .build());

        rabbitTemplate.convertAndSend(queue.getName(), purchaseOrder);
        log.info("Order successfully assembled on Order Consumer - ProducerOrder: " + purchaseOrder);
    }
}
