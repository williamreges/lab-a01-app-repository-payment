package com.example.payment.domain.exception;

/**
 * Exceção lançada quando uma entidade duplicada é detectada.
 */
public class DuplicateEntityBusinessException extends BusinessException {
    public DuplicateEntityBusinessException(String message) {
        super(message);
    }
}
