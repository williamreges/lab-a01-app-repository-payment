package com.example.payment.steps;

import com.example.payment.scenario.TransacaoPixQueryScenario;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TransacaoPixQueryStepdefs {

    private TransacaoPixQueryScenario pixQueryScenario;

    @Before
    public void init() {
        this.pixQueryScenario = new TransacaoPixQueryScenario();
    }

    @Given("que existem transações Pix cadastradas")
    public void queExistemTransacoesPixCadastradas() {
        pixQueryScenario.gerarMassaTransacoesPix();
    }

    @Given("que não existem transações Pix cadastradas")
    public void queNaoExistemTransacoesPixCadastradas() {
        // Neste exemplo realmente não existe massa para esse teste.
    }

    @When("eu faço uma requisição GET paginada page {int} e size {int} para listar as transações Pix")
    public void euFacoUmaRequisicaoGetPaginadaPageESizeParaListarAsTransacoesPix(int page, int size) {
        pixQueryScenario.adicionarPaginacao(page, size);
    }

    @And("eu faço uma requisição GET para listar as transações Pix")
    public void euFacoUmaRequisicaoGetParaListarAsTransacoesPix() {
        pixQueryScenario.executarConsulta();
    }

    @And("eu faço uma requisição GET paginada ordenado por {string} de forma {string}")
    public void euFacoUmaRequisicaoGetPaginadaOrdenadoPorDeForma(String nomeAtributo, String direcao) {
        pixQueryScenario.adicionarOrdenacao(nomeAtributo, direcao);
    }

    @And("eu faço uma filtragem por parametros para listar as transações Pix")
    public void euFacoUmaFiltragemPorParametrosParaListarAsTransacoesPix(Map<String, String> parametros) {
        pixQueryScenario.adicionarParametros(parametros);
    }

    @Then("o sistema deve retornar uma lista paginada page {int} e size {int} de transações Pix")
    public void oSistemaDeveRetornarUmaListaPaginadaPageESizeDeTransacoesPix(int page, int size) {
        JsonNode pageDTO = pixQueryScenario.getResponse().as(JsonNode.class);

        assertThat(pageDTO.get("number").asInt()).isEqualTo(page);
        assertThat(pageDTO.get("size").asInt()).isEqualTo(size);
    }

    @Then("o sistema deve confirmar uma lista ordenada de transações Pix")
    public void oSistemaDeveConfirmarUmaListaOrdenadaDeTransacoesPix() {
        JsonNode pageDTO = pixQueryScenario.getResponse().as(JsonNode.class);

        assertThat(pageDTO
                .get("pageable")
                .get("sort")
                .get("sorted")
                .asBoolean())
                .isTrue();
    }

    @Then("devo validar que retornou {int} registros de transações Pix")
    public void devoValidarQueRetornouRegistrosDeTransacoesPix(int quantidadeRegistros) {
        JsonNode pageDTO = pixQueryScenario.getResponse().as(JsonNode.class);

        assertThat(pageDTO.get("numberOfElements").asInt())
                .isEqualTo(quantidadeRegistros);
    }

    @Then("o status da resposta da lista deve ser {int}")
    public void oStatusDaRespostaDaListaDeveSer(int statusCode) {
        pixQueryScenario.getResponse()
                .then()
                .assertThat()
                .statusCode(statusCode);
    }

    @Then("o sistema deve retornar uma lista vazia")
    public void oSistemaDeveRetornarUmaListaVazia() {
        assertThat(pixQueryScenario.getResponse().asString())
                .contains("[]");
    }

    @After
    public void tearDown() {
        pixQueryScenario.limpar();
    }
}
