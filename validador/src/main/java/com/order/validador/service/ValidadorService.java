package com.order.validador.service;

import com.order.validador.model.Card;
import com.order.validador.model.PurchaseOrder;
import com.order.validador.service.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidadorService {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    public void validateOrder(PurchaseOrder purchaseOrder) throws Exception {
        validateAvailableLimit(purchaseOrder.getCard());
        validatePurchaseWithLimit(purchaseOrder);
    }

    private void validatePurchaseWithLimit(PurchaseOrder purchaseOrder) throws Exception {
        if(purchaseOrder.getPurchaseValue().longValue() > purchaseOrder.getCard().getAvailableLimit().longValue()){
            log.error("Value of the order: {}. Available limit: {}", purchaseOrder.getPurchaseValue(), purchaseOrder.getCard().getAvailableLimit());
            throw new Exception("You have no limit to make a purchase!");
        }
    }

    private void validateAvailableLimit(Card card) throws Exception {
        if(card.getAvailableLimit().longValue() <= 0){
            throw new Exception("Limit unavailable!");
        }
    }
}
