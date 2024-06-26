package com.order.validador.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String name;
    private Long product;

    private BigDecimal purchaseValue;

    private LocalDate datePurchase;
    private String cpfClient;
    private String cep;

    private Card card;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(BigDecimal purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public LocalDate getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDate datePurchase) {
        this.datePurchase = datePurchase;
    }

    public String getCpfClient() {
        return cpfClient;
    }

    public void setCpfClient(String cpfClient) {
        this.cpfClient = cpfClient;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", product=" + product +
                ", purchaseValue=" + purchaseValue +
                ", datePurchase=" + datePurchase +
                ", cpfClient='" + cpfClient + '\'' +
                ", cep='" + cep + '\'' +
                ", card=" + card +
                '}';
    }
}
