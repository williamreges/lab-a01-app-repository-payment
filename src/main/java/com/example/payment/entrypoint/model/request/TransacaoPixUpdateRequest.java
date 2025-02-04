package com.example.payment.entrypoint.model.request;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransacaoPixUpdateRequest extends TransacaoPixRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    public TransacaoPixUpdateRequest(String codigoPessoa, BigDecimal valorTrancacao, Date dataTrancacao, String codigoBeneficiario, String mensagemTransacao) {
        super(codigoPessoa, valorTrancacao, dataTrancacao, codigoBeneficiario, mensagemTransacao);
    }
}
