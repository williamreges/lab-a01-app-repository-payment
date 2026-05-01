package com.example.payment.builder;

import com.example.payment.adapter.input.rest.dto.TransacaoPixRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoPixRequestDTOTestDataBuilder {

    private UUID codigoPessoa = UUID.randomUUID();
    private BigDecimal valorTrancacao = new BigDecimal("100.00");
    private LocalDateTime dataTrancacao = LocalDateTime.now();
    private UUID codigoBeneficiario = UUID.randomUUID();
    private String mensagemTransacao = "Pagamento de teste";

    private TransacaoPixRequestDTO dto;

    public static TransacaoPixRequestDTOTestDataBuilder builder() {
        return new TransacaoPixRequestDTOTestDataBuilder();

    }

    public TransacaoPixRequestDTOTestDataBuilder comCodigoPessoa(UUID codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
        return this;
    }

    public TransacaoPixRequestDTOTestDataBuilder comValorTrancacao(BigDecimal valorTrancacao) {
        this.valorTrancacao = valorTrancacao;
        return this;
    }

    public TransacaoPixRequestDTOTestDataBuilder comDataTrancacao(LocalDateTime dataTrancacao) {
        this.dataTrancacao = dataTrancacao;
        return this;
    }

    public TransacaoPixRequestDTOTestDataBuilder comCodigoBeneficiario(UUID codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
        return this;
    }

    public TransacaoPixRequestDTOTestDataBuilder comMensagemTransacao(String mensagemTransacao) {
        this.mensagemTransacao = mensagemTransacao;
        return this;
    }

    public TransacaoPixRequestDTO build() {
        return new TransacaoPixRequestDTO(
                codigoPessoa,
                valorTrancacao,
                dataTrancacao,
                codigoBeneficiario,
                mensagemTransacao
        );
    }
}

