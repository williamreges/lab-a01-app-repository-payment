package com.example.payment.application.usecases.impl;

import com.example.payment.application.usecases.mapper.TransacaoPixEntityMapper;
import com.example.payment.application.usecases.mapper.TransacaoPixResponseMapper;
import com.example.payment.dataprovider.repository.TransacaoPixRepository;
import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import com.example.payment.entrypoint.model.request.TransacaoPixQueryRequest;
import com.example.payment.entrypoint.model.request.TransacaoPixRequest;
import com.example.payment.entrypoint.model.request.TransacaoPixUpdateRequest;
import com.example.payment.entrypoint.model.response.TransacaoPixResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TransacaoPixService {

    private static final Logger log = LogManager.getLogger(TransacaoPixService.class);
    private final TransacaoPixRepository transacaoPixRepository;

    public TransacaoPixService(TransacaoPixRepository transacaoPixRepository) {
        this.transacaoPixRepository = transacaoPixRepository;
    }

    public String save(TransacaoPixRequest request) {
        log.info("Start Method save {}", request);
        TransacaoPixEntity bean = TransacaoPixEntityMapper.INSTANCE.mapperToEntity(request);
        bean = transacaoPixRepository.save(bean);
        log.info("End Method save {}", request);
        return bean.getCodigoTrancacao();
    }

    public void delete(String id) {
        transacaoPixRepository.deleteById(id);
    }

    public void update(String id, TransacaoPixUpdateRequest request) {
        log.info("Start Method update {}", request);
        TransacaoPixEntity bean = requireOne(id);
        TransacaoPixEntityMapper.INSTANCE.mapperUpdateToEntity(request, bean);
        transacaoPixRepository.save(bean);
        log.info("End Method update {}", request);
    }

    public TransacaoPixResponse getById(String id) {
        TransacaoPixEntity original = requireOne(id);
        return toResponse(original);
    }

    public Page<TransacaoPixResponse> query(TransacaoPixQueryRequest request) {
        log.info("Start Method query {}", request);
        final var listEntity = transacaoPixRepository.findAll(
                getCriterioCodigoPessoa(request)
                        .or(getCriterioCodigoBeneficiario(request)
                        ));
        log.info("End Method query {}", request);
        return new PageImpl<>(listEntity.stream()
                .map(this::toResponse)
                .toList());
    }

    private static Specification<TransacaoPixEntity> getCriterioCodigoBeneficiario(TransacaoPixQueryRequest request) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("codigoBeneficiario"),
                        request.codigoBeneficiario());
    }

    private static Specification<TransacaoPixEntity> getCriterioCodigoPessoa(TransacaoPixQueryRequest request) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("codigoPessoa"),
                        request.codigoPessoa());
    }

    private TransacaoPixResponse toResponse(TransacaoPixEntity original) {
        log.info("Start Method toResponse {}", original);
        return TransacaoPixResponseMapper.INSTANCE.mapperToResponse(original);
    }

    private TransacaoPixEntity requireOne(String id) {
        log.info("Start Method requireOne {}", id);
        return transacaoPixRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
