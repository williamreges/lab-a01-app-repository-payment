package com.example.payment.infraestructure.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static java.util.Optional.ofNullable;

@Component
public class CorrelationIdIHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        ofNullable(
                request.getHeader("correlationID"))
                .ifPresent(
                        s -> MDC.put("correlationID", s));

        return true;
    }
}
