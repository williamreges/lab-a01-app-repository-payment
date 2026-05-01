package com.example.payment.builder;

import com.example.payment.domain.entity.TransacaoPixRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoPixRequestTestDataBuilder {

    private UUID codigoPessoa = UUID.randomUUID();
    private BigDecimal valorTrancacao = new BigDecimal("100.00");
    private LocalDateTime dataTrancacao = LocalDateTime.now();
    private UUID codigoBeneficiario = UUID.randomUUID();
    private String mensagemTransacao = "Pagamento de teste";

    public static TransacaoPixRequestTestDataBuilder builder() {
        return new TransacaoPixRequestTestDataBuilder();
    }

    public TransacaoPixRequestTestDataBuilder comCodigoPessoa(UUID codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
        return this;
    }

    public TransacaoPixRequestTestDataBuilder comValorTrancacao(BigDecimal valorTrancacao) {
        this.valorTrancacao = valorTrancacao;
        return this;
    }

    public TransacaoPixRequestTestDataBuilder comDataTrancacao(LocalDateTime dataTrancacao) {
        this.dataTrancacao = dataTrancacao;
        return this;
    }

    public TransacaoPixRequestTestDataBuilder comCodigoBeneficiario(UUID codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
        return this;
    }

    public TransacaoPixRequestTestDataBuilder comMensagemTransacao(String mensagemTransacao) {
        this.mensagemTransacao = mensagemTransacao;
        return this;
    }

    public TransacaoPixRequest build() {
        return new TransacaoPixRequest(
                codigoPessoa,
                valorTrancacao,
                dataTrancacao,
                codigoBeneficiario,
                mensagemTransacao
        );
    }
}
