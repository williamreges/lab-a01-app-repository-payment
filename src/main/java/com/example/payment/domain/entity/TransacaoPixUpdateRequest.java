package com.example.payment.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade de domínio para atualização de transação PIX.
 */
public non-sealed class TransacaoPixUpdateRequest extends TransacaoPixRequest {

    public TransacaoPixUpdateRequest(UUID codigoPessoa, BigDecimal valorTrancacao, LocalDateTime dataTrancacao, UUID codigoBeneficiario, String mensagemTransacao) {
        super(codigoPessoa, valorTrancacao, dataTrancacao, codigoBeneficiario, mensagemTransacao);
    }

    public TransacaoPixUpdateRequest(BigDecimal valorTrancacao, UUID codigoBeneficiario, String mensagemTransacao) {
        super(null, valorTrancacao, null, codigoBeneficiario, mensagemTransacao);
    }
}
