package com.example.payment.entrypoint.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoPixResponse(
        @JsonProperty("codigoTrancacao")
        String codigoTrancacao,
        @JsonProperty("codigoPessoa")
        String codigoPessoa,
        @JsonProperty("valorTrancacao")
        BigDecimal valorTrancacao,
        @JsonProperty("dataTrancacao")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime dataTrancacao,
        @JsonProperty("codigoBeneficiario")
        String codigoBeneficiario,
        @JsonProperty("mensagemTransacao")
        String mensagemTransacao

) {
}
