package com.example.payment.application.exception;

public class OperationNotAllowedBusinessException extends BusinessException {
    public OperationNotAllowedBusinessException(String message) {
        super(message);
    }
}