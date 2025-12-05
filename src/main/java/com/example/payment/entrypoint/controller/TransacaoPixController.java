package com.example.payment.entrypoint.controller;

import com.example.payment.dataprovider.repository.TransacaoPixRepository;
import com.example.payment.entrypoint.model.request.TransacaoPixQueryRequest;
import com.example.payment.entrypoint.model.request.TransacaoPixRequest;
import com.example.payment.entrypoint.model.request.TransacaoPixUpdateRequest;
import com.example.payment.entrypoint.model.response.TransacaoPixResponse;
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


@Validated
@RestController
@RequestMapping("/transacao-pix")
public class TransacaoPixController {

    private static final Logger log = LogManager.getLogger(TransacaoPixController.class);
    private final TransacaoPixRepository transacaoPixRepository;

    public TransacaoPixController(TransacaoPixRepository transacaoPixRepository) {
        this.transacaoPixRepository = transacaoPixRepository;
    }

    @PostMapping
    public String save(@Valid @RequestBody TransacaoPixRequest request) {
        log.info("Start save {}", request);
        return transacaoPixRepository.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        log.info("Start delete {}", id);
        transacaoPixRepository.delete(id);
        log.info("End delete {}", id);

    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody TransacaoPixUpdateRequest request) {
        log.info("Start update {}", id);
        transacaoPixRepository.update(id, request);
    }

    @GetMapping("/{id}")
    public TransacaoPixResponse getById(@Valid @NotNull @PathVariable("id") String id) {
        log.info("Start getById {}", id);
        return transacaoPixRepository.getById(id);
    }

    @GetMapping
    public Page<TransacaoPixResponse> query(@Valid TransacaoPixQueryRequest request, Pageable pageable) {
        log.info("Start query {}", request);
        return transacaoPixRepository.query(request, pageable);
    }
}
