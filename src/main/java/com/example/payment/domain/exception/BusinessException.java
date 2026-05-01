package com.example.payment.domain.exception;

/**
 * Exceção base para erros de negócio.
 */
public class BusinessException extends RuntimeException {

    private final Integer codigo;

    public BusinessException(String message, Integer codigo) {
        super(message);
        this.codigo = codigo;
    }

    public BusinessException(String message, Throwable cause, Integer codigo) {
        super(message, cause);
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
