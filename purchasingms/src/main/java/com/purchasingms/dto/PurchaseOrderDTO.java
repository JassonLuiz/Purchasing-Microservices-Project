package com.purchasingms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record PurchaseOrderDTO(@NotBlank(message = "{NotBlank.purchaseOrder.name}") String name,
                               @NotBlank(message = "{NotBlank.purchaseOrder.email}") String email,
                               @NotNull(message = "{NotNull.purchaseOrder.product}") @Min(1) Long product,
                               @NotNull(message = "{NotNull.purchaseOrder.purchaseValue}") @Min(1) BigDecimal purchaseValue,
                               @NotBlank(message = "{NotBlank.purchaseOrder.cpfClient}") String cpfClient,
                               @NotBlank(message = "{NotBlank.purchaseOrder.cep}") String cep) {
}
