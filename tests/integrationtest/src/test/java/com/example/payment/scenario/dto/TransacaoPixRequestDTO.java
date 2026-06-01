package com.example.payment.scenario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para transferência de dados de entrada na API.
 * Annotations de Jackson e Jakarta ficam aqui, não no domain.
 */
public record TransacaoPixRequestDTO(
        @JsonProperty("codigoPessoa")
        UUID codigoPessoa,

        @JsonProperty("valorTrancacao")
        BigDecimal valorTrancacao,

        @JsonProperty("dataTrancacao")
        LocalDateTime dataTrancacao,

        @JsonProperty("codigoBeneficiario")
        UUID codigoBeneficiario,

        @JsonProperty("mensagemTransacao")
        String mensagemTransacao
) {

}
