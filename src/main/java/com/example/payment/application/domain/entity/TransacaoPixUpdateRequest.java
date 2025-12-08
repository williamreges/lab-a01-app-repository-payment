package com.example.payment.application.domain.entity;


import java.math.BigDecimal;
import java.util.Date;

public non-sealed class TransacaoPixUpdateRequest extends TransacaoPixRequest {

    public TransacaoPixUpdateRequest(String codigoPessoa, BigDecimal valorTrancacao, Date dataTrancacao, String codigoBeneficiario, String mensagemTransacao) {
        super(codigoPessoa, valorTrancacao, dataTrancacao, codigoBeneficiario, mensagemTransacao);
    }
}
