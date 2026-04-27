package com.example.payment.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidade de domínio que representa uma transação PIX.
 * Sem annotations de framework - domínio limpo.
 */
public sealed class TransacaoPixRequest permits TransacaoPixUpdateRequest {

    private UUID codigoPessoa;
    private BigDecimal valorTrancacao;
    private LocalDateTime dataTrancacao;
    private UUID codigoBeneficiario;
    private String mensagemTransacao;

    public TransacaoPixRequest(UUID codigoPessoa, BigDecimal valorTrancacao, LocalDateTime dataTrancacao, UUID codigoBeneficiario, String mensagemTransacao) {
        this.codigoPessoa = codigoPessoa;
        this.valorTrancacao = valorTrancacao;
        this.dataTrancacao = dataTrancacao;
        this.codigoBeneficiario = codigoBeneficiario;
        this.mensagemTransacao = mensagemTransacao;
    }

    public UUID getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(UUID codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public BigDecimal getValorTrancacao() {
        return valorTrancacao;
    }

    public void setValorTrancacao(BigDecimal valorTrancacao) {
        this.valorTrancacao = valorTrancacao;
    }

    public LocalDateTime getDataTrancacao() {
        return dataTrancacao;
    }

    public void setDataTrancacao(LocalDateTime dataTrancacao) {
        this.dataTrancacao = dataTrancacao;
    }

    public UUID getCodigoBeneficiario() {
        return codigoBeneficiario;
    }

    public void setCodigoBeneficiario(UUID codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
    }

    public String getMensagemTransacao() {
        return mensagemTransacao;
    }

    public void setMensagemTransacao(String mensagemTransacao) {
        this.mensagemTransacao = mensagemTransacao;
    }
}
