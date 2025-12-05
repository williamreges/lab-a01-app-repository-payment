package com.example.payment.dataprovider.repository.spec;

import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import com.example.payment.entrypoint.model.request.TransacaoPixQueryRequest;
import org.springframework.data.jpa.domain.Specification;

public final class TransacaoPixEntitySpecification {

    private TransacaoPixEntitySpecification() {
    }

    public static Specification<TransacaoPixEntity> buildSpecification(TransacaoPixQueryRequest transacaoPixRequest) {
        return Specification.allOf(
                TransacaoPixEntitySpecification.getCriterioCodigoBeneficiario(transacaoPixRequest.codigoBeneficiario()),
                TransacaoPixEntitySpecification.getCriterioCodigoPessoa(transacaoPixRequest.codigoPessoa()),
                TransacaoPixEntitySpecification.getCriterioMensagemTransacao(transacaoPixRequest.mensagemTransacao())
        );
    }


    private static Specification<TransacaoPixEntity> getCriterioCodigoBeneficiario(String codigoBeneficiario) {
        return (root, query, criteriaBuilder) -> {
            if (codigoBeneficiario == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("codigoBeneficiario"), codigoBeneficiario);
        };
    }

    private static Specification<TransacaoPixEntity> getCriterioCodigoPessoa(String codigoPessoa) {
        return (root, query, criteriaBuilder) -> {
            if (codigoPessoa == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("codigoPessoa"),
                    codigoPessoa);
        };

    }

    private static Specification<TransacaoPixEntity> getCriterioMensagemTransacao(String mensagemTransacao) {
        return (root, query, criteriaBuilder) -> {
            if (mensagemTransacao == null) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("mensagemTransacao")),
                   "%" +  mensagemTransacao + "%");
        };

    }
}
