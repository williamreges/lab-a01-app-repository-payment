package com.example.payment.adapter.input.rest.dto;

import com.example.payment.domain.entity.TransacaoPixResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para transferência de dados de saída na API.
 * Annotations de Jackson ficam aqui, não no domain.
 */
public record TransacaoPixResponseDTO(
    @JsonProperty("codigoTrancacao")
    UUID codigoTrancacao,

    @JsonProperty("codigoPessoa")
    UUID codigoPessoa,

    @JsonProperty("valorTrancacao")
    BigDecimal valorTrancacao,

    @JsonProperty("dataTrancacao")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime dataTrancacao,

    @JsonProperty("codigoBeneficiario")
    UUID codigoBeneficiario,

    @JsonProperty("mensagemTransacao")
    String mensagemTransacao
) {

    /**
     * Factory method para converter TransacaoPixResponse (domain) para DTO de saída.
     */
    public static TransacaoPixResponseDTO fromDomain(TransacaoPixResponse response) {
        return new TransacaoPixResponseDTO(
            response.codigoTrancacao(),
            response.codigoPessoa(),
            response.valorTrancacao(),
            response.dataTrancacao(),
            response.codigoBeneficiario(),
            response.mensagemTransacao()
        );
    }
}
