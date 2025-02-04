package com.example.payment.entrypoint.model.request;


import java.math.BigDecimal;
import java.util.Date;

public record TransacaoPixQueryRequest(


        String codigoTrancacao,

        String codigoPessoa,

        BigDecimal valorTrancacao,

        Date dataTrancacao,

        String codigoBeneficiario
) {
}
