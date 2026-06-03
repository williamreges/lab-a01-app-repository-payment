package com.example.payment.infraestructure.exception.handler;

import com.example.payment.dataprovider.repository.exception.TransactionException;
import com.example.payment.infraestructure.exception.model.MessageItem;
import com.example.payment.infraestructure.exception.model.ResponseExceptionCustom;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomTransactionExceptionHandler {


    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ResponseExceptionCustom> handleMethodArgumentNotValid(TransactionException ex) {
        var messages = List.of(new MessageItem(String.valueOf(SC_UNPROCESSABLE_ENTITY), ex.getMessage()));
        return ResponseEntity
                .status(SC_UNPROCESSABLE_ENTITY)
                .body(new ResponseExceptionCustom(messages));

    }

}
