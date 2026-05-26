
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
public class TransacaoPixDeleteScenario {

    @Autowired
    private TransacaoPixRepositoryJPA repositoryJPA;

    private Response response;

    public void gerarNovoRegistroPixDataBase(String codigoTransacao) {
        var newEntity = TransacaoPixEntityTestDataBuilder.builder()
                .comCodigoTrancacao(UUID.fromString(codigoTransacao))
                .build();
        repositoryJPA
                .save(newEntity);
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
