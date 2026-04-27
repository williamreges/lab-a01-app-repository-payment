package com.example.payment.infraestructure.exception.handler;

import com.example.payment.infraestructure.exception.model.MessageItem;
import com.example.payment.infraestructure.exception.model.ResponseExceptionCustom;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.Optional.ofNullable;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var messages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new MessageItem(
                        String.valueOf(status.value()),
                        error.getField().concat(" ") + error.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity
                .status(status) // Bad Request
                .body(new ResponseExceptionCustom(messages));

    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity
                .status(status) // Bad Request
                .body(new ResponseExceptionCustom(List.of(
                        new MessageItem(
                                String.valueOf(status.value()),
                                String.format("O parâmetro '%s' é obrigatório.", ex.getParameterName())
                        )
                )));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        final var message = String.format("Ocorreu um erro ao converter o valor '%s' do campo '%s' para o tipo '%s'. Esperado: %s",
                        ex.getValue(),
                        ex.getPropertyName(),
                        ofNullable(ex.getValue()).map(Object::getClass).map(Class::getName).orElse("Unknown class"),
                        ofNullable(ex.getRequiredType()).map(Class::getName).orElse("Unknown type")
                );

        return ResponseEntity
                .status(status) // Bad Request
                .body(new ResponseExceptionCustom(List.of(
                        new MessageItem(
                                String.valueOf(status.value()),
                                message
                        )
                )));
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return ResponseEntity
                .status(status) // Bad Request
                .body(new ResponseExceptionCustom(List.of(
                        new MessageItem(
                                String.valueOf(status.value()),
                                ex.getMessage()
                        )
                )));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final var message = ofNullable(ex.getMostSpecificCause())
                .map(Throwable::getMessage)
                .orElse("Ocorreu um erro ao processar a mensagem.");

        return ResponseEntity
                .status(status) // Bad Request
                .body(new ResponseExceptionCustom(List.of(
                        new MessageItem(
                                String.valueOf(status.value()),
                                message
                        )
                )));
    }
}
