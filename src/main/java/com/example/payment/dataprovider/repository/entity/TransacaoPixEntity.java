package com.example.payment.dataprovider.repository.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacao_pix")
public class TransacaoPixEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "codigo_trancacao", nullable = false)
    private String codigoTrancacao;

    @Column(name = "codigo_pessoa", nullable = false)
    private String codigoPessoa;

    @Column(name = "valor_trancacao", nullable = false)
    private BigDecimal valorTrancacao;

    @Column(name = "data_trancacao")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataTrancacao;

    @Column(name = "codigo_beneficiario")
    private String codigoBeneficiario;

    @Column(name = "mensagem_transacao")
    private String mensagemTransacao;

    public TransacaoPixEntity() {
    }

    public TransacaoPixEntity(String codigoTrancacao, String codigoPessoa, BigDecimal valorTrancacao, LocalDateTime dataTrancacao, String codigoBeneficiario, String mensagemTransacao) {
        this.codigoTrancacao = codigoTrancacao;
        this.codigoPessoa = codigoPessoa;
        this.valorTrancacao = valorTrancacao;
        this.dataTrancacao = dataTrancacao;
        this.codigoBeneficiario = codigoBeneficiario;
        this.mensagemTransacao = mensagemTransacao;
    }

    public String getCodigoTrancacao() {
        return codigoTrancacao;
    }

    public void setCodigoTrancacao(String codigoTrancacao) {
        this.codigoTrancacao = codigoTrancacao;
    }

    public String getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(String codigoPessoa) {
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
