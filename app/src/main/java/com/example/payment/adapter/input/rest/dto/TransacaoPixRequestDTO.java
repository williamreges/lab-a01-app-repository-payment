package com.example.payment.adapter.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.payment.domain.entity.TransacaoPixRequest;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para transferência de dados de entrada na API.
 * Annotations de Jackson e Jakarta ficam aqui, não no domain.
 */
public record TransacaoPixRequestDTO(
    @JsonProperty("codigoPessoa")
    @NotNull(message = "codigoPessoa can not null")
    UUID codigoPessoa,

    @NotNull(message = "valorTrancacao can not null")
    @JsonProperty("valorTrancacao")
    BigDecimal valorTrancacao,

    @JsonProperty("dataTrancacao")
    LocalDateTime dataTrancacao,

    @JsonProperty("codigoBeneficiario")
    UUID codigoBeneficiario,

    @JsonProperty("mensagemTransacao")
    String mensagemTransacao
) {

    /**
     * Converte para TransacaoPixRequest (domain model).
     */
    public TransacaoPixRequest toDomain() {
        return new TransacaoPixRequest(
            codigoPessoa,
            valorTrancacao,
            dataTrancacao,
            codigoBeneficiario,
            mensagemTransacao
        );
    }
}
