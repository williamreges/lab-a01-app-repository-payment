package com.example.payment.steps;

import com.example.payment.scenario.TransacaoPixDeleteScenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class TransacaoPixDeleteStepdefs {

    private TransacaoPixDeleteScenario pixDeleteScenario;

    @Before
    public void init() {
        pixDeleteScenario = new TransacaoPixDeleteScenario();
    }

    @Given("que existe uma transação Pix com o id {string} para deletar")
    public void queExisteUmaTransacaoPixComOIdParaDeletar(String codigoTransacao) {
        pixDeleteScenario.prepararTransacaoPixExistente(codigoTransacao);
    }

    @When("eu envio uma requisição para deletar a transação Pix pelo id {string}")
    public void euEnvioUmaRequisicaoParaDeletarATransacaoPixPeloId(String codigoTransacao) {
        pixDeleteScenario.deletarTransacaoPixPorId(codigoTransacao);
    }

    @Then("o sistema deve retornar {int} após o delete da transação Pix")
    public void oSistemaDeveRetornarUmErroInformandoQueATransacaoNaoFoiEncontradaParaDeletar(int status) {
        assertThat(pixDeleteScenario.getResponse().statusCode()).isEqualTo(status);
    }

    @After
    public void tearDown() {
        pixDeleteScenario.limpar();
    }
}
