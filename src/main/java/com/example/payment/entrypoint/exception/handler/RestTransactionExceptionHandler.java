package com.example.payment.entrypoint.exception.handler;

import com.example.payment.dataprovider.repository.exception.TransactionException;
import com.example.payment.entrypoint.exception.model.MessageItem;
import com.example.payment.entrypoint.exception.model.ResponseExceptionCustom;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestTransactionExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<Object> handleDataAccess(TransactionException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseExceptionCustom(List.of(
                        new MessageItem(
                                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR),
                                ex.getMessage()
                        )
                )));
    }

}
