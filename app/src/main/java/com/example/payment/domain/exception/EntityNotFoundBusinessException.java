package com.example.payment.domain.exception;

import org.apache.http.HttpStatus;

/**
 * Exceção lançada quando uma entidade não é encontrada.
 */
public class EntityNotFoundBusinessException extends BusinessException {
    public EntityNotFoundBusinessException(String message) {
        super(message, HttpStatus.SC_NOT_FOUND);
    }
}
