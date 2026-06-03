package com.example.payment.dataprovider.repository.exception;

public class TransactionException extends RuntimeException {
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
