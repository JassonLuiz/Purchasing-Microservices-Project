package com.orderconsumerms.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Card {

    private String num;
    private BigDecimal availableLimit;

    public Card(String num, BigDecimal availableLimit) {
        this.num = num;
        this.availableLimit = availableLimit;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public BigDecimal getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(BigDecimal availableLimit) {
        this.availableLimit = availableLimit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "num='" + num + '\'' +
                ", availableLimit=" + availableLimit +
                '}';
    }

    public static class Builder {
        private String num;
        private BigDecimal availableLimit;

        public Builder() {
        }

        public Builder num(String num) {
            this.num = num;
            return this;
        }

        public Builder availableLimit(BigDecimal availableLimit) {
            this.availableLimit = availableLimit;
            return this;
        }

        public Card build() {
            return new Card(num, availableLimit);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
