package com.example.payment.entrypoint.exception.handler;

import com.example.payment.application.exception.BusinessException;
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
public class RestBusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseExceptionCustom(List.of(
                        new MessageItem(
                                String.valueOf(HttpStatus.BAD_REQUEST),
                                ex.getMessage()
                        )
                )));
    }


}
