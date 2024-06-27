package com.order.validador.service.Exceptions;

public class LimitUnavailableException extends RuntimeException{
    public LimitUnavailableException(String message) {
        super(message);
    }
}
