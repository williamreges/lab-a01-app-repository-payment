package com.example.payment.application.exception;

public class DuplicateEntityBusinessException extends BusinessException {
    public DuplicateEntityBusinessException(String message) {
        super(message);
    }
}
