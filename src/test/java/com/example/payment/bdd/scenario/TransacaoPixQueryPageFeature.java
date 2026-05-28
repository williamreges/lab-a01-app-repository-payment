package com.example.payment.bdd.scenario;

import com.example.payment.bdd.common.QueryPageFeature;
import com.example.payment.dataprovider.repository.TransacaoPixRepositoryJPA;
import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import io.cucumber.spring.ScenarioScope;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ScenarioScope
public class TransacaoPixQueryPageFeature extends QueryPageFeature {

    @Autowired
    private TransacaoPixRepositoryJPA repositoryJPA;

    public void criarTransacoesPixNoBanco(@MonotonicNonNull List<TransacaoPixEntity> dataTable) {
        repositoryJPA.saveAll(dataTable);
    }

    public void limparBanco() {
        repositoryJPA.deleteAll();
    }
}
