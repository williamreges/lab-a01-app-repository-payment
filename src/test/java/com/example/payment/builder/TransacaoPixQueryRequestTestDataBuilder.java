package com.example.payment.builder;

import com.example.payment.domain.entity.TransacaoPixQueryRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoPixQueryRequestTestDataBuilder {

    private UUID codigoTrancacao = UUID.randomUUID();
    private UUID codigoPessoa = UUID.randomUUID();
    private BigDecimal valorTrancacao = new BigDecimal("100.00");
    private LocalDateTime dataTrancacao = LocalDateTime.now();
    private UUID codigoBeneficiario = UUID.randomUUID();
    private String mensagemTransacao = "Pagamento de teste";

    public static TransacaoPixQueryRequestTestDataBuilder builder() {
        return new TransacaoPixQueryRequestTestDataBuilder();
    }

    public TransacaoPixQueryRequestTestDataBuilder comCodigoTrancacao(UUID codigoTrancacao) {
        this.codigoTrancacao = codigoTrancacao;
        return this;
    }

    public TransacaoPixQueryRequestTestDataBuilder comCodigoPessoa(UUID codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
        return this;
    }

    public TransacaoPixQueryRequestTestDataBuilder comValorTrancacao(BigDecimal valorTrancacao) {
        this.valorTrancacao = valorTrancacao;
        return this;
    }

    public TransacaoPixQueryRequestTestDataBuilder comDataTrancacao(LocalDateTime dataTrancacao) {
        this.dataTrancacao = dataTrancacao;
        return this;
    }

    public TransacaoPixQueryRequestTestDataBuilder comCodigoBeneficiario(UUID codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
        return this;
    }

    public TransacaoPixQueryRequestTestDataBuilder comMensagemTransacao(String mensagemTransacao) {
        this.mensagemTransacao = mensagemTransacao;
        return this;
    }

    public TransacaoPixQueryRequest build() {
        return new TransacaoPixQueryRequest(
                codigoTrancacao,
                codigoPessoa,
                valorTrancacao,
                dataTrancacao,
                codigoBeneficiario,
                mensagemTransacao
        );
    }
}
