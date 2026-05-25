package com.example.payment.bdd.steps;

import com.example.payment.bdd.scenario.TransacaoPixPostScenario;
import com.example.payment.infraestructure.exception.model.ResponseExceptionCustom;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TransacaoPixPostStepdefs {

    private static final String ENDPOINT = "/transacao-pix";

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TransacaoPixPostScenario pixPostScenario;

    @Given("que possuo os dados válidos de uma transação Pix")
    public void quePossuoOsDadosValidosDeUmaTransacaoPix(String jsonBody) {
        pixPostScenario.addRequestBody(jsonBody);
    }

    @When("eu envio uma requisição para criar a transação Pix")
    public void euEnvioUmaRequisicaoParaCriarATransacaoPix() {
        var response =
                given()
                        .port(serverPort)
                        .contentType(ContentType.JSON)
                        .headers("correlationID", UUID.randomUUID().toString())
                        .body(pixPostScenario.getRequestBody())
                        .when()
                        .post(ENDPOINT)
                        .then()
                        .extract()
                        .response();

        pixPostScenario.addResponse(response);
    }

    @Then("o sistema deve retornar os dados da transação Pix criada com sucesso")
    public void oSistemaDeveRetornarOsDadosDaTransacaoPixCriadaComSucesso() {
        pixPostScenario.getResponse().then().assertThat().statusCode(200);
        var responseDTO = UUID.fromString(pixPostScenario.getResponse().asString());
        Assertions.assertNotNull(responseDTO);
    }

    @Given("que possuo dados nulos como codiooPessoa e valorTransacao para uma transação Pix")
    public void quePossuoDadosInvalidosParaUmaTransacaoPix(String jsonBodyInvalid) {
        pixPostScenario.addRequestBody(jsonBodyInvalid);
    }

    @Then("o sistema deve retornar um erro informando que os dados são inválidos")
    public void oSistemaDeveRetornarUmErroInformandoQueOsDadosSaoInvalidos() {
        pixPostScenario.getResponse().then().assertThat().statusCode(400);
        var responseExceptionDTO = pixPostScenario.getResponse().as(ResponseExceptionCustom.class);
        Assertions.assertNotNull(responseExceptionDTO);
    }

}
