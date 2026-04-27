package com.example.payment.adapter.input.rest.dto;

import com.example.payment.domain.entity.TransacaoPixUpdateRequest;

/**
 * DTO para transferência de dados de atualização na API.
 */
public class TransacaoPixUpdateRequestDTO extends TransacaoPixRequestDTO {


    /**
     * Converte para TransacaoPixUpdateRequest (domain model).
     */
    @Override
    public TransacaoPixUpdateRequest toDomain() {
        return new TransacaoPixUpdateRequest(
                getCodigoPessoa(),
                getValorTrancacao(),
                getDataTrancacao(),
                getCodigoBeneficiario(),
                getMensagemTransacao()
        );
    }
}
