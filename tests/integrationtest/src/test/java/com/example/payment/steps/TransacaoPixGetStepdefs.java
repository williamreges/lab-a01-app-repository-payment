package com.example.payment.steps;

import com.example.payment.scenario.TransacaoPixGetScenario;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class TransacaoPixGetStepdefs {

    private TransacaoPixGetScenario pixGetScenario;

    @Before
    public void init() {
        pixGetScenario = new TransacaoPixGetScenario();
    }

    @Given("que existe uma transação Pix com o id {string}")
    public void queExisteUmaTransacaoPixComOId(String codigoTransacao) {
        pixGetScenario.gerarMassaTransacaoPix(codigoTransacao);
    }

    @When("eu buscar a transação Pix pelo id {string}")
    public void euBuscarATransacaoPixPeloId(String codigoTransacao) {
        pixGetScenario.requisicaoRest(codigoTransacao);
    }

    @Then("o sistema deve retornar os dados da transação Pix id {string} com sucesso")
    public void oSistemaDeveRetornarOsDadosDaTransacaoPixComSucesso(String codigoTransacao) {
        JsonNode responseDTO = pixGetScenario.getResponse().as(JsonNode.class);
        assertThat(responseDTO.get("codigoTrancacao").asText()).isEqualTo(codigoTransacao);
    }

    @Then("o status da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        pixGetScenario.getResponse()
                .then()
                .assertThat()
                .statusCode(statusCode);
    }

    @Then("o sistema deve retornar um erro informando que a transação não foi encontrada")
    public void oSistemaDeveRetornarUmErroInformandoQueATransacaoNaoFoiEncontrada() {
        JsonNode responseDTO = pixGetScenario.getResponse().as(JsonNode.class);
        assertThat(responseDTO.get("messages")).isNotEmpty();
    }

    @After
    public void tearDown() {
        pixGetScenario.limpar();
    }
}
