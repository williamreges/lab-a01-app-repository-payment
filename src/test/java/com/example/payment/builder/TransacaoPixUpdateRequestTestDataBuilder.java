package com.example.payment.builder;

import com.example.payment.domain.entity.TransacaoPixUpdateRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoPixUpdateRequestTestDataBuilder {

    private UUID codigoPessoa = UUID.randomUUID();
    private BigDecimal valorTrancacao = new BigDecimal("100.00");
    private LocalDateTime dataTrancacao = LocalDateTime.now();
    private UUID codigoBeneficiario = UUID.randomUUID();
    private String mensagemTransacao = "Pagamento de teste";

    public static TransacaoPixUpdateRequestTestDataBuilder builder() {
        return new TransacaoPixUpdateRequestTestDataBuilder();
    }

    public TransacaoPixUpdateRequestTestDataBuilder comCodigoPessoa(UUID codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
        return this;
    }

    public TransacaoPixUpdateRequestTestDataBuilder comValorTrancacao(BigDecimal valorTrancacao) {
        this.valorTrancacao = valorTrancacao;
        return this;
    }

    public TransacaoPixUpdateRequestTestDataBuilder comDataTrancacao(LocalDateTime dataTrancacao) {
        this.dataTrancacao = dataTrancacao;
        return this;
    }

    public TransacaoPixUpdateRequestTestDataBuilder comCodigoBeneficiario(UUID codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
        return this;
    }

    public TransacaoPixUpdateRequestTestDataBuilder comMensagemTransacao(String mensagemTransacao) {
        this.mensagemTransacao = mensagemTransacao;
        return this;
    }

    public TransacaoPixUpdateRequest build() {
        return new TransacaoPixUpdateRequest(
                codigoPessoa,
                valorTrancacao,
                dataTrancacao,
                codigoBeneficiario,
                mensagemTransacao
        );
    }
}
