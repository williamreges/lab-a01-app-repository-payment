package com.example.payment.steps;

import com.example.payment.scenario.TransacaoPixPostScenario;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class TransacaoPixPostStepdefs {

    private TransacaoPixPostScenario pixPostScenario;

    @Before
    public void init(Scenario scenario){
        pixPostScenario = new TransacaoPixPostScenario();
    }

    @Given("que possuo os dados válidos de uma transação Pix")
    public void quePossuoOsDadosValidosDeUmaTransacaoPix(String jsonBody) {
        pixPostScenario.adicionaBodyParaPersistencia(jsonBody);
    }

    @When("eu envio uma requisição para criar a transação Pix")
    public void euEnvioUmaRequisicaoParaCriarATransacaoPix() {
        var response = pixPostScenario.euEnvioUmaRequisicaoParaCriarATransacaoPix();
        pixPostScenario.addResponse(response);
    }

    @Then("o sistema deve retornar {int} após o post da transação Pix")
    public void oSistemaDeveRetornarApósCriarATransaçãoPix(int status) {
        pixPostScenario.getResponse()
                .then()
                .assertThat()
                .statusCode(status);
    }

    @Then("o sistema deve retornar o id da transação Pix criado com exito")
    public void oSistemaDeveRetornarOsDadosDaTransacaoPixCriadaComSucesso() {
        assertThat(pixPostScenario.getResponse().asString())
                .isNotBlank();

    }

    @Given("que possuo dados nulos como codiooPessoa e valorTransacao para uma transação Pix")
    public void quePossuoDadosInvalidosParaUmaTransacaoPix(String jsonBodyInvalid) {
        pixPostScenario.adicionaBodyParaPersistencia(jsonBodyInvalid);
    }

    @Then("o sistema deve retornar um erro informando que os dados são inválidos")
    public void oSistemaDeveRetornarUmErroInformandoQueOsDadosSaoInvalidos() {
        assertThat(pixPostScenario
                .getResponse()
                .asString()
                .contains("messages"));
    }
}
