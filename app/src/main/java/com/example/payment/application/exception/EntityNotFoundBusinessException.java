package com.example.payment.application.exception;

public class EntityNotFoundBusinessException extends BusinessException {
    public EntityNotFoundBusinessException(String message) {
        super(message);
    }
}