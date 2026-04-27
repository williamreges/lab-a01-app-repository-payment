package com.example.payment.domain.exception;

/**
 * Exceção base para erros de negócio.
 */
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
