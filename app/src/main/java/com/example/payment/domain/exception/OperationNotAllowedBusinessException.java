package com.example.payment.domain.exception;

import org.apache.http.HttpStatus;

/**
 * Exceção lançada quando uma operação não é permitida pelas regras de negócio.
 */
public class OperationNotAllowedBusinessException extends BusinessException {
    public OperationNotAllowedBusinessException(String message) {
        super(message, HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }
}
