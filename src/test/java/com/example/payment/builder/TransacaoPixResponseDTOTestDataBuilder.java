package com.example.payment.builder;

import com.example.payment.adapter.input.rest.dto.TransacaoPixResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoPixResponseDTOTestDataBuilder {

    private UUID codigoTrancacao = UUID.randomUUID();
    private UUID codigoPessoa = UUID.randomUUID();
    private BigDecimal valorTrancacao = new BigDecimal("100.00");
    private LocalDateTime dataTrancacao = LocalDateTime.now();
    private UUID codigoBeneficiario = UUID.randomUUID();
    private String mensagemTransacao = "Pagamento de teste";

    public static TransacaoPixResponseDTOTestDataBuilder builder() {
        return new TransacaoPixResponseDTOTestDataBuilder();
    }

    public TransacaoPixResponseDTOTestDataBuilder comCodigoTrancacao(UUID codigoTrancacao) {
        this.codigoTrancacao = codigoTrancacao;
        return this;
    }

    public TransacaoPixResponseDTOTestDataBuilder comCodigoPessoa(UUID codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
        return this;
    }

    public TransacaoPixResponseDTOTestDataBuilder comValorTrancacao(BigDecimal valorTrancacao) {
        this.valorTrancacao = valorTrancacao;
        return this;
    }

    public TransacaoPixResponseDTOTestDataBuilder comDataTrancacao(LocalDateTime dataTrancacao) {
        this.dataTrancacao = dataTrancacao;
        return this;
    }

    public TransacaoPixResponseDTOTestDataBuilder comCodigoBeneficiario(UUID codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
        return this;
    }

    public TransacaoPixResponseDTOTestDataBuilder comMensagemTransacao(String mensagemTransacao) {
        this.mensagemTransacao = mensagemTransacao;
        return this;
    }

    public TransacaoPixResponseDTO build() {
        return new TransacaoPixResponseDTO(
                codigoTrancacao,
                codigoPessoa,
                valorTrancacao,
                dataTrancacao,
                codigoBeneficiario,
                mensagemTransacao
        );
    }
}
