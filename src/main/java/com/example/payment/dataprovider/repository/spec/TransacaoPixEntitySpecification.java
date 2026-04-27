package com.example.payment.dataprovider.repository.spec;

import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import com.example.payment.domain.entity.TransacaoPixQueryRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

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


    private static Specification<TransacaoPixEntity> getCriterioCodigoBeneficiario(UUID codigoBeneficiario) {
        return (root, query, criteriaBuilder) -> {
            if (codigoBeneficiario == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("codigoBeneficiario"), codigoBeneficiario.toString());
        };
    }

    private static Specification<TransacaoPixEntity> getCriterioCodigoPessoa(UUID codigoPessoa) {
        return (root, query, criteriaBuilder) -> {
            if (codigoPessoa == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("codigoPessoa"), codigoPessoa.toString());
        };

    }

    private static Specification<TransacaoPixEntity> getCriterioMensagemTransacao(String mensagemTransacao) {
        return (root, query, criteriaBuilder) -> {
            if (mensagemTransacao == null) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("mensagemTransacao")),
                    "%" + mensagemTransacao + "%");
        };

    }
}
