package com.order.validador.service.Exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(String message) {
        super(message);
    }
}
