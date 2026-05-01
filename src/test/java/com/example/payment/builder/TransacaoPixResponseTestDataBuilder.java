package com.example.payment.builder;

import com.example.payment.domain.entity.TransacaoPixResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoPixResponseTestDataBuilder {

    private UUID codigoTrancacao = UUID.randomUUID();
    private UUID codigoPessoa = UUID.randomUUID();
    private BigDecimal valorTrancacao = new BigDecimal("100.00");
    private LocalDateTime dataTrancacao = LocalDateTime.now();
    private UUID codigoBeneficiario = UUID.randomUUID();
    private String mensagemTransacao = "Pagamento de teste";

    public static TransacaoPixResponseTestDataBuilder builder() {
        return new TransacaoPixResponseTestDataBuilder();
    }

    public TransacaoPixResponseTestDataBuilder comCodigoTrancacao(UUID codigoTrancacao) {
        this.codigoTrancacao = codigoTrancacao;
        return this;
    }

    public TransacaoPixResponseTestDataBuilder comCodigoPessoa(UUID codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
        return this;
    }

    public TransacaoPixResponseTestDataBuilder comValorTrancacao(BigDecimal valorTrancacao) {
        this.valorTrancacao = valorTrancacao;
        return this;
    }

    public TransacaoPixResponseTestDataBuilder comDataTrancacao(LocalDateTime dataTrancacao) {
        this.dataTrancacao = dataTrancacao;
        return this;
    }

    public TransacaoPixResponseTestDataBuilder comCodigoBeneficiario(UUID codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
        return this;
    }

    public TransacaoPixResponseTestDataBuilder comMensagemTransacao(String mensagemTransacao) {
        this.mensagemTransacao = mensagemTransacao;
        return this;
    }

    public TransacaoPixResponse build() {
        return new TransacaoPixResponse(
                codigoTrancacao,
                codigoPessoa,
                valorTrancacao,
                dataTrancacao,
                codigoBeneficiario,
                mensagemTransacao
        );
    }
}
