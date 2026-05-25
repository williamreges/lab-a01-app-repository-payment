package com.example.payment.bdd.scenario;

import com.example.payment.builder.TransacaoPixEntityTestDataBuilder;
import com.example.payment.dataprovider.repository.TransacaoPixRepositoryJPA;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ScenarioScope
public class TransacaoPixGetScenario {

    @Autowired
    private TransacaoPixRepositoryJPA repositoryJPA;

    private UUID codigoTransacao;
    private Response response;

    public void gerarNovoRegistroPixDataBase() {
        var newEntity = TransacaoPixEntityTestDataBuilder.builder()
                .comCodigoTrancacao(null)
                .build();
        codigoTransacao = repositoryJPA
                .save(newEntity)
                .getCodigoTrancacao();
    }

    public UUID obterCodigoTransacaoPix() {
        return codigoTransacao;
    }

    public void addCodigoTransacaoPix(UUID uuid) {
        this.codigoTransacao = uuid;
    }

    public void deleteAll() {
        repositoryJPA.deleteAll();
    }

    public void addReponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
