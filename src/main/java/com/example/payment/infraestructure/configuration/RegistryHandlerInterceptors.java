package com.example.payment.infraestructure.configuration;

import com.example.payment.infraestructure.interceptor.CorrelationIdIHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RegistryHandlerInterceptors implements WebMvcConfigurer {

    private final CorrelationIdIHandlerInterceptor correlationIdIHandlerInterceptor;

    public RegistryHandlerInterceptors(CorrelationIdIHandlerInterceptor correlationIdIHandlerInterceptor) {
        this.correlationIdIHandlerInterceptor = correlationIdIHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(correlationIdIHandlerInterceptor);
    }
}
