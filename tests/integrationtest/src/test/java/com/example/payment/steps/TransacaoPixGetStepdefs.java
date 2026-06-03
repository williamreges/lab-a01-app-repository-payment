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

    @Given("que existe uma nova transação Pix cadastrada para consulta")
    public void queExisteUmaNovaTransacaoPixCadastradaParaConsulta() {
        pixGetScenario.prepararTransacaoPixExistente();
    }

    @When("eu buscar a transação Pix gerada")
    public void euBuscarATransacaoPixGerada() {
        pixGetScenario.buscarTransacaoPixGerada();
    }

    @When("eu buscar a transação Pix pelo id {string}")
    public void euBuscarATransacaoPixPeloId(String codigoTransacao) {
        pixGetScenario.buscarTransacaoPixPorId(codigoTransacao);
    }

    @Then("o sistema deve retornar os dados da transação Pix gerada com sucesso")
    public void oSistemaDeveRetornarOsDadosDaTransacaoPixGeradaComSucesso() {
        JsonNode responseDTO = pixGetScenario.getResponse().as(JsonNode.class);

        assertThat(responseDTO.get("codigoTrancacao").asText())
                .isEqualTo(pixGetScenario.getCodigoTransacaoGerado());
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
