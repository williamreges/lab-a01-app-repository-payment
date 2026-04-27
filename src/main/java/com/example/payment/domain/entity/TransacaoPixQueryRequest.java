package com.example.payment.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade de consulta para transação PIX.
 */
public record TransacaoPixQueryRequest(
        UUID codigoTrancacao,
        UUID codigoPessoa,
        BigDecimal valorTrancacao,
        LocalDateTime dataTrancacao,
        UUID codigoBeneficiario,
        String mensagemTransacao
) {
}
