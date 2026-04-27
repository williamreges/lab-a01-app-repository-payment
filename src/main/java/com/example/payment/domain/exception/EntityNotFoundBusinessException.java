package com.example.payment.domain.exception;

/**
 * Exceção lançada quando uma entidade não é encontrada.
 */
public class EntityNotFoundBusinessException extends BusinessException {
    public EntityNotFoundBusinessException(String message) {
        super(message);
    }
}
