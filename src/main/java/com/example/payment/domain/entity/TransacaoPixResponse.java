package com.example.payment.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade de resposta para transa\u00e7\u00e3o PIX.
 * Sem annotations de framework - dom\u00ednio limpo.
 */
public record TransacaoPixResponse(
        UUID codigoTrancacao,
        UUID codigoPessoa,
        BigDecimal valorTrancacao,
        LocalDateTime dataTrancacao,
        UUID codigoBeneficiario,
        String mensagemTransacao
) {
}
