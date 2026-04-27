package com.example.payment.adapter.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.payment.domain.entity.TransacaoPixRequest;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para transferência de dados de entrada na API.
 * Annotations de Jackson e Jakarta ficam aqui, não no domain.
 */
public class TransacaoPixRequestDTO {

    @JsonProperty("codigoPessoa")
    @NotNull(message = "codigoPessoa can not null")
    private UUID codigoPessoa;

    @NotNull(message = "valorTrancacao can not null")
    @JsonProperty("valorTrancacao")
    private BigDecimal valorTrancacao;

    @JsonProperty("dataTrancacao")
    private LocalDateTime dataTrancacao;

    @JsonProperty("codigoBeneficiario")
    private UUID codigoBeneficiario;

    @JsonProperty("mensagemTransacao")
    private String mensagemTransacao;

    public TransacaoPixRequestDTO() {
    }

    public TransacaoPixRequestDTO(UUID codigoPessoa, BigDecimal valorTrancacao, LocalDateTime dataTrancacao, UUID codigoBeneficiario, String mensagemTransacao) {
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

    /**
     * Converte para TransacaoPixRequest (domain model).
     */
    public TransacaoPixRequest toDomain() {
        return new TransacaoPixRequest(
            codigoPessoa,
            valorTrancacao,
            dataTrancacao,
            codigoBeneficiario,
            mensagemTransacao
        );
    }
}
