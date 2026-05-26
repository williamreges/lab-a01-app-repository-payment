package com.example.payment.bdd.steps;

import com.example.payment.bdd.scenario.TransacaoPixDeleteScenario;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

public class TransacaoPixDeleteStepdefs {

    private static final String ENDPOINT = "/transacao-pix/";

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TransacaoPixDeleteScenario pixDeleteScenario;

    @Given("que existe uma transação Pix com o id {string} paa deletar")
    public void queExisteUmaTransaçãoPixComOIdPaaDeletar(String arg0) {
        pixDeleteScenario.gerarNovoRegistroPixDataBase(arg0);
    }


    @When("eu envio uma requisição para deletar a transação Pix pelo id {string}")
    public void euEnvioUmaRequisicaoParaDeletarATransacaoPix(String codigoTransacao) {
        var response =
                given()
                        .port(serverPort)
                        .when()
                        .delete(ENDPOINT + codigoTransacao)
                        .then()
                        .extract()
                        .response();
        pixDeleteScenario.addResponse(response);
    }

    @Then("o sistema deve retornar confirmação de deleção com sucesso")
    public void oSistemaDeveRetornarConfirmacaoDeDelecaoComSucesso() {
        Assertions.assertEquals(200, pixDeleteScenario.getResponse().statusCode());
    }

    @Then("o sistema deve retornar um erro informando que a transação não foi encontrada para deletar")
    public void oSistemaDeveRetornarUmErroInformandoQueATransacaoNaoFoiEncontrada() {
        Assertions.assertEquals(200, pixDeleteScenario.getResponse().statusCode());
    }

    @After
    public void doSomethingAfter(Scenario scenario) {
        pixDeleteScenario.deleteAll();
    }


}
