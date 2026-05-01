package com.example.payment.domain.exception;

import org.apache.http.HttpStatus;

/**
 * Exceção lançada quando uma entidade duplicada é detectada.
 */
public class DuplicateEntityBusinessException extends BusinessException {
    public DuplicateEntityBusinessException(String message) {
        super(message, HttpStatus.SC_CONFLICT);
    }
}
