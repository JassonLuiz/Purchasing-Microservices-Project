package com.order.validador.service.consumer;

import com.order.validador.model.PurchaseOrder;
import com.order.validador.service.Exceptions.InsufficientFundsException;
import com.order.validador.service.Exceptions.LimitUnavailableException;
import com.order.validador.service.ValidadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    private final ValidadorService validadorService;

    public Consumer(ValidadorService validadorService) {
        this.validadorService = validadorService;
    }

    @RabbitListener(queues = {"${broker.queue.pending.purchase.name}"})
    public void consumer(@Payload PurchaseOrder purchaseOrder) throws IOException {
        log.info("Request received in Validador: {}", purchaseOrder);

        try {
            validadorService.validateOrder(purchaseOrder);
        } catch (LimitUnavailableException | InsufficientFundsException e) {
            e.printStackTrace();
        }
    }
}
