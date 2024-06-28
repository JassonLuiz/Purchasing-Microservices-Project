package com.purchasingms.service;

import com.purchasingms.model.PurchaseOrder;
import com.purchasingms.service.produces.Producer;
import com.purchasingms.repository.PurchaseOrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final Producer producer;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, Producer producer) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.producer = producer;
    }

    @Transactional
    public PurchaseOrder save(PurchaseOrder purchaseOrder){
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        producer.publishMessagePurchaseOrder(purchaseOrder);
        return purchaseOrder;
    }

    public PurchaseOrder findById(Long id) throws Exception {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new Exception("Purchase order not found!"));
    }

    public void delete(Long id) throws Exception {
        var purchase = findById(id);
        purchaseOrderRepository.deleteById(id);
    }
}
