package com.example.payment.domain.exception;

/**
 * Exceção lançada quando uma operação não é permitida pelas regras de negócio.
 */
public class OperationNotAllowedBusinessException extends BusinessException {
    public OperationNotAllowedBusinessException(String message) {
        super(message);
    }
}
