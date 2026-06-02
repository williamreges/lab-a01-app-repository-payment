package com.example.payment.steps;

import com.example.payment.scenario.TransacaoPixPostScenario;
import com.example.payment.scenario.dto.TransacaoPixRequestDTO;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class TransacaoPixPostStepdefs {

    private TransacaoPixPostScenario pixPostScenario;

    @Before
    public void init() {
        pixPostScenario = new TransacaoPixPostScenario();
    }

    @Given("que possuo os dados válidos de uma transação Pix")
    public void quePossuoOsDadosValidosDeUmaTransacaoPix(TransacaoPixRequestDTO jsonBody) {
        pixPostScenario.adicionaBodyParaPersistencia(jsonBody);
    }

    @When("eu envio uma requisição para criar a transação Pix")
    public void euEnvioUmaRequisicaoParaCriarATransacaoPix() {
        pixPostScenario.euEnvioUmaRequisicaoParaCriarATransacaoPix();
    }

    @Then("o sistema deve retornar {int} após o post da transação Pix")
    public void oSistemaDeveRetornarAposCriarATransacaoPix(int status) {
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

    @Given("que possuo dados nulos como codigoPessoa e valorTransacao para uma transação Pix")
    public void quePossuoDadosInvalidosParaUmaTransacaoPix(TransacaoPixRequestDTO jsonBodyInvalid) {
        pixPostScenario.adicionaBodyParaPersistencia(jsonBodyInvalid);
    }

    @Then("o sistema deve retornar um erro informando que os dados são inválidos")
    public void oSistemaDeveRetornarUmErroInformandoQueOsDadosSaoInvalidos() {
        assertThat(pixPostScenario.getResponse().asString())
                .contains("messages");
    }

    @After
    public void tearDown() {
        pixPostScenario.limpar();
    }
}
