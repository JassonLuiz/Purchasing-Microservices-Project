package com.purchasingms.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.purchasingms.DataMock;
import com.purchasingms.PurchasingmsApplication;
import com.purchasingms.model.PurchaseOrder;
import com.purchasingms.service.PurchaseOrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.containers.GenericContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PurchasingmsApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PurchaseOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private ObjectMapper objectMapper;

    private static GenericContainer<?> rabbitmq;

    private static final String ORDER_ROUTER = "/v1/PurchaseOrder";

    private DataMock dataMock = new DataMock();

    @BeforeAll
    public static void setUp(){
        rabbitmq = new GenericContainer<>("rabbitmq:3.13-management")
                .withExposedPorts(5672, 15672);
        rabbitmq.start();

        System.setProperty("spring.rabbitmq.host", rabbitmq.getHost());
        System.setProperty("spring.rabbitmq.port", rabbitmq.getMappedPort(5672).toString());
    }

    @DisplayName("POST - Must save order successfully")
    @Test
    void mustSaveOrderSuccessfully() throws Exception {
        var purchaseOrderMock = dataMock.getPurchaseOrderDTO();
        var id = 1L;

        mockMvc.perform(post(ORDER_ROUTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(purchaseOrderMock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Jasson Luiz")))
                .andExpect(jsonPath("$.email", is("jassonluiz@hotmail.com")));

        PurchaseOrder purchaseOrder = purchaseOrderService.findById(id);

        assertEquals(purchaseOrder.getId(), id);
        assertNotNull(purchaseOrder);
    }

    @DisplayName("GET - Must find order successfully")
    @Test
    void mustFindOrderSuccessfully() throws Exception {
        var id = 1L;

        mockMvc.perform(get(ORDER_ROUTER.concat("/" + id)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("GET - Must find order fail")
    @Test
    void mustFindOrderFail() throws Exception {
        var id = 20L;

        mockMvc.perform(get(ORDER_ROUTER.concat("/" + id)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
