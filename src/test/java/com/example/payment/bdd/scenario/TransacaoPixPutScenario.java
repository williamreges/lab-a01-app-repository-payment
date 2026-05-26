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
public class TransacaoPixPutScenario {

    @Autowired
    private TransacaoPixRepositoryJPA repositoryJPA;

    private Object requestBody;
    private UUID novoCodigoTransacaoPix;
    private Response response;

    public void gerarNovoRegistroPixDataBase() {
        var newEntity = TransacaoPixEntityTestDataBuilder.builder()
                .comCodigoTrancacao(null)
                .build();
        novoCodigoTransacaoPix = repositoryJPA
                .save(newEntity)
                .getCodigoTrancacao();
    }

    public void addCodigoTransacaoPix(UUID codigo) {
        this.novoCodigoTransacaoPix = codigo;
    }

    public UUID obterCodigoTransacaoPix() {
        return novoCodigoTransacaoPix;
    }

    public void gerarDadosValidos(Object dto) {
        this.requestBody = dto;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void addResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void deleteAll() {
        repositoryJPA.deleteAll();
    }
}
