package com.purchasingms;

import com.purchasingms.model.PurchaseOrder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DataMock {

    public PurchaseOrder getPurchaseOrder(){
        var purchaseOrder = new PurchaseOrder();
        purchaseOrder.setName("Jasson Luiz");
        purchaseOrder.setProduct(1L);
        purchaseOrder.setPurchaseValue(BigDecimal.TEN);
        purchaseOrder.setDatePurchase(LocalDate.now());
        purchaseOrder.setCpfClient("111.222.333-40");
        purchaseOrder.setCep("12345678");
        purchaseOrder.setEmail("jassonluiz@hotmail.com");

        return purchaseOrder;
    }
}
