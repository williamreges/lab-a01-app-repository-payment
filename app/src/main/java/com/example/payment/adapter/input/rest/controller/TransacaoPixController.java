package com.example.payment.adapter.input.rest.controller;

import com.example.payment.adapter.input.rest.dto.TransacaoPixRequestDTO;
import com.example.payment.adapter.input.rest.dto.TransacaoPixResponseDTO;
import com.example.payment.adapter.input.rest.dto.TransacaoPixUpdateRequestDTO;
import com.example.payment.domain.entity.TransacaoPixQueryRequest;
import com.example.payment.domain.entity.TransacaoPixRequest;
import com.example.payment.domain.entity.TransacaoPixResponse;
import com.example.payment.domain.entity.TransacaoPixUpdateRequest;
import com.example.payment.domain.port.TransacaoPersistencePort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Adapter de entrada (Driving Adapter) da arquitetura hexagonal.
 * Responsável por receber requisições HTTP e convertê-las para o domain.
 * Injeta a PORT de persistência, não a implementação concreta.
 * 
 * Localização: adapter/input/rest/controller
 */
@Validated
@RestController
@RequestMapping("/transacao-pix")
public class TransacaoPixController {

    private static final Logger log = LogManager.getLogger(TransacaoPixController.class);
    private final TransacaoPersistencePort transacaoPersistencePort;

    public TransacaoPixController(TransacaoPersistencePort transacaoPersistencePort) {
        this.transacaoPersistencePort = transacaoPersistencePort;
    }

    @PostMapping
    public String save(@Valid @RequestBody TransacaoPixRequestDTO requestDTO) {
        log.info("Start save {}", requestDTO);
        TransacaoPixRequest domainRequest = requestDTO.toDomain();
        return transacaoPersistencePort.save(domainRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        log.info("Start delete {}", id);
        transacaoPersistencePort.delete(id);
        log.info("End delete {}", id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody TransacaoPixUpdateRequestDTO requestDTO) {
        log.info("Start update {}", id);
        TransacaoPixUpdateRequest domainRequest = requestDTO.toDomain();
        transacaoPersistencePort.update(id, domainRequest);
    }

    @GetMapping("/{id}")
    public TransacaoPixResponseDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        log.info("Start getById {}", id);
        var domainResponse = transacaoPersistencePort.getById(id);
        return TransacaoPixResponseDTO.fromDomain(domainResponse);
    }

    @GetMapping
    public Page<TransacaoPixResponseDTO> query(@Valid TransacaoPixQueryRequest request, Pageable pageable) {
        log.info("Start query {}", request);
        Page<TransacaoPixResponse> domainPage = transacaoPersistencePort.query(request, pageable);
        return domainPage.map(TransacaoPixResponseDTO::fromDomain);
    }
}
