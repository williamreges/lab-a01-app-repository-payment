package com.example.payment.dataprovider.repository.validation.impl;

import com.example.payment.dataprovider.repository.validation.MessageJPAValidation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class PaymentJPAValidation extends MessageJPAValidation {

    @Override
    protected String dataIntegrityViolationExceptionMessage(DataIntegrityViolationException e) {
        return "Custom Message: Payment operation violates data integrity or business constraints.";
    }

    @Override
    protected String duplicateKeyExceptionMessage(DuplicateKeyException e) {
        return "Custom Message: A payment with the same unique identifier already exists.";
    }

    @Override
    protected String emptyResultDataAccessExceptionMessage(EmptyResultDataAccessException e) {
        return "Custom Message: No payment record was found for the provided criteria.";
    }

}
