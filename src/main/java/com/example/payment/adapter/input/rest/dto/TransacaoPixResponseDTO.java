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
public class TransacaoPixResponseDTO {

    @JsonProperty("codigoTrancacao")
    private UUID codigoTrancacao;

    @JsonProperty("codigoPessoa")
    private UUID codigoPessoa;

    @JsonProperty("valorTrancacao")
    private BigDecimal valorTrancacao;

    @JsonProperty("dataTrancacao")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataTrancacao;

    @JsonProperty("codigoBeneficiario")
    private UUID codigoBeneficiario;

    @JsonProperty("mensagemTransacao")
    private String mensagemTransacao;

    public TransacaoPixResponseDTO() {
    }

    public TransacaoPixResponseDTO(UUID codigoTrancacao, UUID codigoPessoa, BigDecimal valorTrancacao, LocalDateTime dataTrancacao, UUID codigoBeneficiario, String mensagemTransacao) {
        this.codigoTrancacao = codigoTrancacao;
        this.codigoPessoa = codigoPessoa;
        this.valorTrancacao = valorTrancacao;
        this.dataTrancacao = dataTrancacao;
        this.codigoBeneficiario = codigoBeneficiario;
        this.mensagemTransacao = mensagemTransacao;
    }

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

    public UUID getCodigoTrancacao() {
        return codigoTrancacao;
    }

    public void setCodigoTrancacao(UUID codigoTrancacao) {
        this.codigoTrancacao = codigoTrancacao;
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
