package com.example.payment.entrypoint.controller;

import com.example.payment.application.usecases.impl.TransacaoPixService;
import com.example.payment.entrypoint.model.request.TransacaoPixQueryRequest;
import com.example.payment.entrypoint.model.request.TransacaoPixRequest;
import com.example.payment.entrypoint.model.request.TransacaoPixUpdateRequest;
import com.example.payment.entrypoint.model.response.TransacaoPixResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/transacao-pix")
public class TransacaoPixController {

    private static final Logger log = LogManager.getLogger(TransacaoPixController.class);
    private final TransacaoPixService transacaoPixService;

    public TransacaoPixController(TransacaoPixService transacaoPixService) {
        this.transacaoPixService = transacaoPixService;
    }

    @PostMapping
    public String save(@Valid @RequestBody TransacaoPixRequest request) {
        log.info("Start save {}", request);
        return transacaoPixService.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        log.info("Start delete {}", id);
        transacaoPixService.delete(id);
        log.info("End delete {}", id);

    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody TransacaoPixUpdateRequest request) {
        log.info("Start update {}", id);
        transacaoPixService.update(id, request);
    }

    @GetMapping("/{id}")
    public TransacaoPixResponse getById(@Valid @NotNull @PathVariable("id") String id) {
        log.info("Start getById {}", id);
        return transacaoPixService.getById(id);
    }

    @GetMapping
    public Page<TransacaoPixResponse> query(@Valid TransacaoPixQueryRequest request) {
        log.info("Start query {}", request);
        return transacaoPixService.query(request);
    }
}
