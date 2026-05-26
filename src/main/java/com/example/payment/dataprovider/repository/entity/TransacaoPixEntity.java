package com.example.payment.dataprovider.repository.entity;

import com.example.payment.dataprovider.repository.converter.UUIDConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacao_pix")
public class TransacaoPixEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo_trancacao", nullable = false)
    private UUID codigoTrancacao;

    @Column(name = "codigo_pessoa", nullable = false)
    @Convert(converter = UUIDConverter.class)
    private UUID codigoPessoa;

    @Column(name = "valor_trancacao", nullable = false)
    private BigDecimal valorTrancacao;

    @Column(name = "data_trancacao")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataTrancacao;

    @Column(name = "codigo_beneficiario", nullable = false)
    @Convert(converter = UUIDConverter.class)
    private UUID codigoBeneficiario;

    @Column(name = "mensagem_transacao")
    private String mensagemTransacao;

    public TransacaoPixEntity() {
    }

    @PrePersist
    public void prePersist() {
        if (codigoTrancacao == null) {
            codigoTrancacao = UUID.randomUUID();
        }
    }

    public TransacaoPixEntity(UUID codigoTrancacao, UUID codigoPessoa, BigDecimal valorTrancacao, LocalDateTime dataTrancacao, UUID codigoBeneficiario, String mensagemTransacao) {
        this.codigoTrancacao = codigoTrancacao;
        this.codigoPessoa = codigoPessoa;
        this.valorTrancacao = valorTrancacao;
        this.dataTrancacao = dataTrancacao;
        this.codigoBeneficiario = codigoBeneficiario;
        this.mensagemTransacao = mensagemTransacao;
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
