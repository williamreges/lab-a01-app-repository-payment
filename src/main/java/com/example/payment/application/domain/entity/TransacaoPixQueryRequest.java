package com.example.payment.application.domain.entity;


import java.math.BigDecimal;
import java.util.Date;

public record TransacaoPixQueryRequest(


        String codigoTrancacao,

        String codigoPessoa,

        BigDecimal valorTrancacao,

        Date dataTrancacao,

        String codigoBeneficiario,

        String mensagemTransacao
) {
}
