package com.purchasingms.controller;

import com.purchasingms.dto.PurchaseOrderDTO;
import com.purchasingms.model.PurchaseOrder;
import com.purchasingms.service.PurchaseOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/PurchaseOrder")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService){
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> save(@RequestBody PurchaseOrderDTO purchaseOrderDTO){
        var purchaseOrder = new PurchaseOrder();
        BeanUtils.copyProperties(purchaseOrderDTO, purchaseOrder);

        return ResponseEntity.ok(purchaseOrderService.save(purchaseOrder));
    }
}
