package com.example.payment.adapter.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.payment.domain.entity.TransacaoPixUpdateRequest;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para transferência de dados de atualização na API.
 */
public record TransacaoPixUpdateRequestDTO(
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
     * Converte para TransacaoPixUpdateRequest (domain model).
     */
    public TransacaoPixUpdateRequest toDomain() {
        return new TransacaoPixUpdateRequest(
            codigoPessoa,
            valorTrancacao,
            dataTrancacao,
            codigoBeneficiario,
            mensagemTransacao
        );
    }
}
