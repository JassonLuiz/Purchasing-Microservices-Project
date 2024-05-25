package com.purchasingms.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PurchaseOrderDTO(String name, Long product, BigDecimal value, LocalDate datePurchase, String cpfClient, String cep) {
}
