package com.example.payment.adapter.output.persistence.repository;

import com.example.payment.application.usecases.mapper.TransacaoPixEntityMapper;
import com.example.payment.application.usecases.mapper.TransacaoPixResponseMapper;
import com.example.payment.dataprovider.repository.TransacaoPixRepositoryJPA;
import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import com.example.payment.dataprovider.repository.spec.TransacaoPixEntitySpecification;
import com.example.payment.dataprovider.repository.validation.impl.PaymentJPAValidation;
import com.example.payment.domain.entity.TransacaoPixQueryRequest;
import com.example.payment.domain.entity.TransacaoPixRequest;
import com.example.payment.domain.entity.TransacaoPixResponse;
import com.example.payment.domain.entity.TransacaoPixUpdateRequest;
import com.example.payment.domain.exception.EntityNotFoundBusinessException;
import com.example.payment.domain.port.TransacaoPersistencePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Adapter que implementa o Port de persistência.
 * Responsável pela comunicação com a camada de persistência (JPA/Banco de dados).
 * Este é um adapter de saída (driven adapter) na arquitetura hexagonal.
 * <p>
 * O Spring injeta automaticamente este adapter quando o Port é solicitado.
 * Localização: adapter/output/persistence/repository
 */
@Component
public class TransacaoPixRepositoryAdapter implements TransacaoPersistencePort {

    private static final Logger log = LogManager.getLogger(TransacaoPixRepositoryAdapter.class);
    private final TransacaoPixRepositoryJPA transacaoPixRepositoryJPA;
    private final PaymentJPAValidation paymentJPAValidation;

    public TransacaoPixRepositoryAdapter(TransacaoPixRepositoryJPA transacaoPixRepositoryJPA,
                                         PaymentJPAValidation paymentJPAValidation) {
        this.transacaoPixRepositoryJPA = transacaoPixRepositoryJPA;
        this.paymentJPAValidation = paymentJPAValidation;
    }

    @Override
    public String save(TransacaoPixRequest request) {
        log.info("Start Method save {}", request);
        try {
            return saveRequest(request)
                    .map(TransacaoPixEntity::getCodigoTrancacao)
                    .map(UUID::toString)
                    .orElse("");
        } catch (DataAccessException dataAccessException) {
            paymentJPAValidation.messageExceptionFactory(dataAccessException);
        }
        return "";
    }

    @Override
    public void delete(String id) {
        log.info("Start Method delete {}", id);
        transacaoPixRepositoryJPA.deleteById(UUID.fromString(id));
        log.info("End Method delete {}", id);
    }

    @Override
    public void update(String id, TransacaoPixUpdateRequest request) {
        log.info("Start Method update {}", request);
        TransacaoPixEntity bean = requireOne(id);
        TransacaoPixEntityMapper.INSTANCE.mapperUpdateToEntity(request, bean);
        transacaoPixRepositoryJPA.save(bean);
        log.info("End Method update {}", request);
    }

    @Override
    public TransacaoPixResponse getById(String id) {
        log.info("Start Method getById {}", id);
        TransacaoPixEntity original = requireOne(id);
        return toResponse(original);
    }

    @Override
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

    private Optional<TransacaoPixEntity> saveRequest(TransacaoPixRequest request) {
        TransacaoPixEntity bean = TransacaoPixEntityMapper.INSTANCE.mapperToEntity(request);
        bean = transacaoPixRepositoryJPA.save(bean);
        log.info("End Method save {}", request);
        return Optional.ofNullable(bean);
    }

    private TransacaoPixResponse toResponse(TransacaoPixEntity original) {
        log.info("Start Method toResponse {}", original);
        return TransacaoPixResponseMapper.INSTANCE.mapperToResponse(original);
    }

    private TransacaoPixEntity requireOne(String id) {
        log.info("Start Method requireOne {}", id);
        return transacaoPixRepositoryJPA.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundBusinessException("Resource not found: " + id));
    }
}
