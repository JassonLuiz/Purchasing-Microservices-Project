package com.order.validador.service;

import com.order.validador.model.Card;
import com.order.validador.model.PurchaseOrder;
import com.order.validador.service.Exceptions.InsufficientFundsException;
import com.order.validador.service.Exceptions.LimitUnavailableException;
import com.order.validador.service.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidadorService {

    private final Logger log = LoggerFactory.getLogger(Consumer.class);

    private final EmailService emailService;

    public ValidadorService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void validateOrder(PurchaseOrder purchaseOrder) {
        validateAvailableLimit(purchaseOrder.getCard());
        validatePurchaseWithLimit(purchaseOrder);
        emailService.notifyCustomerApprovedPurchase(purchaseOrder.getEmail());
    }

    private void validatePurchaseWithLimit(PurchaseOrder purchaseOrder) {
        if(purchaseOrder.getPurchaseValue().longValue() > purchaseOrder.getCard().getAvailableLimit().longValue()){
            log.error("Value of the order: {}. Available limit: {}", purchaseOrder.getPurchaseValue(), purchaseOrder.getCard().getAvailableLimit());
            throw new InsufficientFundsException("You have no limit to make a purchase!");
        }
    }

    private void validateAvailableLimit(Card card) {
        if(card.getAvailableLimit().longValue() <= 0){
            throw new LimitUnavailableException("Limit unavailable!");
        }
    }
}
