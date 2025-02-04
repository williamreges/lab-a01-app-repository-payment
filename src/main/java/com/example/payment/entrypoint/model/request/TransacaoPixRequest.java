package com.example.payment.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransacaoPixRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("codigoPessoa")
    @NotNull(message = "codigoPessoa can not null")
    private String codigoPessoa;

    @NotNull(message = "valorTrancacao can not null")
    @JsonProperty("valorTrancacao")
    private BigDecimal valorTrancacao;

    @JsonProperty("dataTrancacao")
    private Date dataTrancacao;

    @JsonProperty("codigoBeneficiario")
    private String codigoBeneficiario;

    @JsonProperty("mensagemTransacao")
    private String mensagemTransacao;

    public TransacaoPixRequest(String codigoPessoa, BigDecimal valorTrancacao, Date dataTrancacao, String codigoBeneficiario, String mensagemTransacao) {
        this.codigoPessoa = codigoPessoa;
        this.valorTrancacao = valorTrancacao;
        this.dataTrancacao = dataTrancacao;
        this.codigoBeneficiario = codigoBeneficiario;
        this.mensagemTransacao = mensagemTransacao;
    }

    public @NotNull(message = "codigoPessoa can not null") String getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(@NotNull(message = "codigoPessoa can not null") String codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public @NotNull(message = "valorTrancacao can not null") BigDecimal getValorTrancacao() {
        return valorTrancacao;
    }

    public void setValorTrancacao(@NotNull(message = "valorTrancacao can not null") BigDecimal valorTrancacao) {
        this.valorTrancacao = valorTrancacao;
    }

    public Date getDataTrancacao() {
        return dataTrancacao;
    }

    public void setDataTrancacao(Date dataTrancacao) {
        this.dataTrancacao = dataTrancacao;
    }

    public String getCodigoBeneficiario() {
        return codigoBeneficiario;
    }

    public void setCodigoBeneficiario(String codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
    }

    public String getMensagemTransacao() {
        return mensagemTransacao;
    }

    public void setMensagemTransacao(String mensagemTransacao) {
        this.mensagemTransacao = mensagemTransacao;
    }
}
