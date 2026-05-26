package com.example.payment.bdd.steps;

import com.example.payment.adapter.input.rest.dto.TransacaoPixResponseDTO;
import com.example.payment.bdd.scenario.TransacaoPixGetScenario;
import com.example.payment.infraestructure.exception.model.ResponseExceptionCustom;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TransacaoPixGetStepdefs {

    private static final String ENDPOINT = "/transacao-pix/";

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TransacaoPixGetScenario pixGetScenario;


    @Given("que existe uma transação Pix com o id {string}")
    public void queExisteUmaTransaçãoPixComOId(String codigoTransacao) {
        pixGetScenario.gerarNovoRegistroPixDataBase(codigoTransacao);
    }

    @When("eu buscar a transação Pix pelo id {string}")
    public void euBuscarATransaçãoPixPeloId(String codigoTransacao) {

        var response =
                given()
                        .port(serverPort)
                        .contentType(ContentType.JSON)
                        .headers("correlationID", UUID.randomUUID().toString())
                        .when()
                        .get(ENDPOINT + codigoTransacao)
                        .then()
                        .extract()
                        .response();

        pixGetScenario.addReponse(response);
    }

    @Then("o sistema deve retornar os dados da transação Pix com sucesso")
    public void oSistemaDeveRetornarOsDadosDaTransaçãoPixComSucesso() {
        pixGetScenario.getResponse().then().assertThat().statusCode(200);
        var responseDTO = pixGetScenario.getResponse().as(TransacaoPixResponseDTO.class);
        Assertions.assertNotNull(responseDTO);
    }

    @Then("o sistema deve retornar um erro informando que a transação não foi encontrada")
    public void oSistemaDeveRetornarUmErroInformandoQueATransaçãoNãoFoiEncontrada() {
        pixGetScenario.getResponse().then().assertThat().statusCode(404);
        var responseExceptionDTO = pixGetScenario.getResponse().as(ResponseExceptionCustom.class);
        Assertions.assertNotNull(responseExceptionDTO);
    }

    @After
    public void doSomethingAfter(Scenario scenario) {
        pixGetScenario.deleteAll();
    }

}
