package com.purchasingms.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PurchaseOrderDTO(@NotBlank String name, @NotNull @Min(1) Long product, @NotNull @Min(1) BigDecimal purchaseValue, @NotBlank String cpfClient, @NotBlank String cep) {
}
