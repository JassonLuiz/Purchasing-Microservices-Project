package com.purchasingms.controller;

import com.purchasingms.dto.PurchaseOrderDTO;
import com.purchasingms.model.PurchaseOrder;
import com.purchasingms.service.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/v1/PurchaseOrder")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService){
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> save(@RequestBody @Valid PurchaseOrderDTO purchaseOrderDTO){
        var purchaseOrder = new PurchaseOrder();
        BeanUtils.copyProperties(purchaseOrderDTO, purchaseOrder);

        return ResponseEntity.ok(purchaseOrderService.save(purchaseOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> findById(@PathVariable Long id) throws Exception {
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(id);
        return ResponseEntity.ok(purchaseOrder);
    }
}
