package com.purchasingms.service;

import com.purchasingms.DataMock;
import com.purchasingms.model.PurchaseOrder;
import com.purchasingms.repository.PurchaseOrderRepository;
import com.purchasingms.service.produces.Producer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PurchaseOrderServiceTest {

    @InjectMocks
    private PurchaseOrderService purchaseOrderService;

    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    private Producer producer;

    private DataMock mock = new DataMock();

    @DisplayName("Must save order successfully")
    @Test
    void mustSaveOrderSuccessfully(){
        var purchaseOrderMock = mock.getPurchaseOrder();

        Mockito.when(purchaseOrderRepository.save(Mockito.any(PurchaseOrder.class))).thenReturn(purchaseOrderMock);
        Mockito.doNothing().when(producer).publishMessagePurchaseOrder(Mockito.any(PurchaseOrder.class));

        var purchaseOrderSave = purchaseOrderService.save(purchaseOrderMock);

        assertEquals(purchaseOrderMock.getName(), purchaseOrderSave.getName());
        assertNotNull(purchaseOrderSave.getEmail());
    }

    @DisplayName("Should fail to search for id")
    @Test
    void shouldFailToSearchForId(){
        var id = 1L;

        Throwable exception = assertThrows(Exception.class, () -> purchaseOrderService.findById(id));

        assertEquals("Purchase order not found!", exception.getMessage());
    }

    @DisplayName("Should to search for id")
    @Test
    void shouldToSearchForId() throws Exception {
        var purchaseOrderMock = mock.getPurchaseOrder();
        purchaseOrderMock.setId(1L);
        var id = 1L;

        Mockito.when(purchaseOrderRepository.findById(id)).thenReturn(Optional.of(purchaseOrderMock));

        var purchaseOrder = purchaseOrderService.findById(id);

        assertEquals(purchaseOrderMock.getId(), purchaseOrder.getId());
        assertNotNull(purchaseOrder);
        Mockito.verify(purchaseOrderRepository, Mockito.atLeastOnce()).findById(id);
    }

    @DisplayName("Must exclude by id")
    @Test
    void mustExcludeById() throws Exception {
        var purchaseOrderMock = mock.getPurchaseOrder();
        purchaseOrderMock.setId(1L);
        var id = 1L;

        Mockito.when(purchaseOrderRepository.findById(id)).thenReturn(Optional.of(purchaseOrderMock));
        Mockito.doNothing().when(purchaseOrderRepository).deleteById(id);

        purchaseOrderService.delete(id);
        Mockito.verify(purchaseOrderRepository, Mockito.atLeastOnce()).deleteById(id);
    }

}
