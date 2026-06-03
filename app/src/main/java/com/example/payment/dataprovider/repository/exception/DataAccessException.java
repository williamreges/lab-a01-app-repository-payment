package com.example.payment.dataprovider.repository.exception;

public abstract class DataAccessException extends RuntimeException {
    protected DataAccessException(String message) {
        super(message);
    }

    protected DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
