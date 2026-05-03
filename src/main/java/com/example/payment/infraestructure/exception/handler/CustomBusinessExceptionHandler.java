package com.example.payment.infraestructure.exception.handler;

import com.example.payment.domain.exception.BusinessException;
import com.example.payment.infraestructure.exception.model.MessageItem;
import com.example.payment.infraestructure.exception.model.ResponseExceptionCustom;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomBusinessExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseExceptionCustom> handleMethodArgumentNotValid(BusinessException ex) {
        var messages = List.of(new MessageItem(String.valueOf(ex.getCodigo()), ex.getMessage()));
        return ResponseEntity
                .status(ex.getCodigo())
                .body(new ResponseExceptionCustom(messages));

    }

}
