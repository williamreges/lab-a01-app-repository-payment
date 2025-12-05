package com.example.payment.dataprovider.repository;

import com.example.payment.application.usecases.mapper.TransacaoPixEntityMapper;
import com.example.payment.application.usecases.mapper.TransacaoPixResponseMapper;
import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import com.example.payment.dataprovider.repository.spec.TransacaoPixEntitySpecification;
import com.example.payment.dataprovider.repository.validation.impl.PaymentJPAValidation;
import com.example.payment.entrypoint.model.request.TransacaoPixQueryRequest;
import com.example.payment.entrypoint.model.request.TransacaoPixRequest;
import com.example.payment.entrypoint.model.request.TransacaoPixUpdateRequest;
import com.example.payment.entrypoint.model.response.TransacaoPixResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TransacaoPixRepository {

    private static final Logger log = LogManager.getLogger(TransacaoPixRepository.class);
    private final TransacaoPixRepositoryJPA transacaoPixRepositoryJPA;
    private final PaymentJPAValidation paymentJPAValidation;

    public TransacaoPixRepository(TransacaoPixRepositoryJPA transacaoPixRepositoryJPA, PaymentJPAValidation paymentJPAValidation) {
        this.transacaoPixRepositoryJPA = transacaoPixRepositoryJPA;
        this.paymentJPAValidation = paymentJPAValidation;
    }

    public String save(TransacaoPixRequest request) {
        log.info("Start Method save {}", request);
        try {
            return saveRequest(request);
        } catch (DataAccessException dataAccessException) {
            paymentJPAValidation.messageExceptionFactory(dataAccessException);
        }
        return "";
    }

    private String saveRequest(TransacaoPixRequest request) {
        TransacaoPixEntity bean = TransacaoPixEntityMapper.INSTANCE.mapperToEntity(request);
        bean = transacaoPixRepositoryJPA.save(bean);
        log.info("End Method save {}", request);
        return bean.getCodigoTrancacao();
    }

    public void delete(String id) {
        transacaoPixRepositoryJPA.deleteById(id);
    }

    public void update(String id, TransacaoPixUpdateRequest request) {
        log.info("Start Method update {}", request);
        TransacaoPixEntity bean = requireOne(id);
        TransacaoPixEntityMapper.INSTANCE.mapperUpdateToEntity(request, bean);
        transacaoPixRepositoryJPA.save(bean);
        log.info("End Method update {}", request);
    }

    public TransacaoPixResponse getById(String id) {
        TransacaoPixEntity original = requireOne(id);
        return toResponse(original);
    }

    public Page<TransacaoPixResponse> query(TransacaoPixQueryRequest request, Pageable pageable) {
        log.info("Start Method query {}", request);
        final var listEntity = transacaoPixRepositoryJPA
                .findAll(TransacaoPixEntitySpecification
                        .buildSpecification(request), pageable);

        log.info("End Method query {}", request);
        return new PageImpl<>(listEntity.stream()
                .map(this::toResponse)
                .toList());
    }


    private TransacaoPixResponse toResponse(TransacaoPixEntity original) {
        log.info("Start Method toResponse {}", original);
        return TransacaoPixResponseMapper.INSTANCE.mapperToResponse(original);
    }

    private TransacaoPixEntity requireOne(String id) {
        log.info("Start Method requireOne {}", id);
        return transacaoPixRepositoryJPA.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
