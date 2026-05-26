package com.example.payment.bdd.steps;

import com.example.payment.adapter.input.rest.dto.TransacaoPixUpdateRequestDTO;
import com.example.payment.bdd.scenario.TransacaoPixPutScenario;
import com.example.payment.infraestructure.exception.model.ResponseExceptionCustom;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.DocStringType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TransacaoPixPutStepdefs {

    private static final String ENDPOINT = "/transacao-pix/";

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TransacaoPixPutScenario pixPutScenario;

    @Given("que existe uma transação Pix com o id para atualizar")
    public void queExisteUmaTransacaoPixComOId() {
        pixPutScenario.gerarNovoRegistroPixDataBase();
    }

    @And("que possuo os dados válidos para atualizar a transação Pix")
    public void quePossuoOsDadosValidosParaAtualizarATransacaoPix(TransacaoPixUpdateRequestDTO dto) {
        pixPutScenario.gerarDadosValidos(dto);
    }

    @When("eu envio uma requisição para atualizar a transação Pix")
    public void euEnvioUmaRequisicaoParaAtualizarATransacaoPix() {
        var response =
                given()
                        .port(serverPort)
                        .contentType(ContentType.JSON)
                        .body(pixPutScenario.getRequestBody())
                        .when()
                        .put(ENDPOINT + pixPutScenario.obterCodigoTransacaoPix())
                        .then()
                        .extract()
                        .response();
        pixPutScenario.addResponse(response);
    }

    @Then("o sistema deve retornar o status de transação Pix atualizada com sucesso")
    public void oSistemaDeveRetornarOsDadosDaTransacaoPixAtualizadaComSucesso() {
        pixPutScenario.getResponse().then().assertThat().statusCode(200);
    }

    @DocStringType
    public TransacaoPixUpdateRequestDTO defineTransacaoPixRequestDTO(String docString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.readValue(docString, TransacaoPixUpdateRequestDTO.class);
    }

    @Given("que não existe uma transação Pix para atualizar com o id {string}")
    public void queNaoExisteUmaTransacaoPixComOId(String codigoTransacaoPix) {
        pixPutScenario.addCodigoTransacaoPix(UUID.fromString(codigoTransacaoPix));
    }

    @Then("o sistema deve retornar um erro informando que a transação a atualizar não foi encontrada")
    public void oSistemaDeveRetornarUmErroInformandoQueATransacaoNaoFoiEncontrada() {
        pixPutScenario.getResponse().then().assertThat().statusCode(404);
        Assertions.assertNotNull(pixPutScenario.getResponse().as(ResponseExceptionCustom.class));
    }

    @After
    public void doSomethingAfter(Scenario scenario) {
        pixPutScenario.deleteAll();
    }
}
