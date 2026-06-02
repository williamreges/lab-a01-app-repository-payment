package com.example.payment.steps;

import com.example.payment.scenario.TransacaoPixQueryScenario;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TransacaoPixQueryStepdefs {


    private TransacaoPixQueryScenario pixQueryScenario;

    @Before
    public void init(Scenario scenario) {
        this.pixQueryScenario = new TransacaoPixQueryScenario();
    }

    @Given("que existem transações Pix cadastradas")
    public void queExistemTransacoesPixCadastradas() {
        pixQueryScenario.gerarMassaTransacoesPix();
    }

    @Given("que não existem transações Pix cadastradas")
    public void queNaoExistemTransacoesPixCadastradas() {
        //Nesse exemplo realmente massa não existe pra esse teste
    }

    @When("eu faço uma requisição GET paginada page {int} e size {int} para listar as transações Pix")
    public void euFaçoUmaRequisiçãoGETPaginadaPageESizeParaListarAsTransacoesPix(int page, int size) {
        pixQueryScenario.addNumberPageAndSize(page, size);
    }

    @And("eu faço uma requisição GET para listar as transações Pix")
    public void euFaçoUmaRequisiçãoGETParaListarAsTransacoesPix() {
        var response = pixQueryScenario.execulteRest();
        pixQueryScenario.addResponse(response);
    }

    @And("eu faço uma requisição GET paginada ordenado por {string} de forma {string}")
    public void euFaçoUmaRequisiçãoGETPaginadaOrdenadoPorDataTransacaoDeFormaDecrescente(String nomeAtributo, String direcao) {
        pixQueryScenario.addSort(nomeAtributo, direcao);
    }

    @And("eu faço uma filtragem por parametros para listar as transações Pix")
    public void euFaçoUmaFiltragemPorParametrosParaListarAsTransaçõesPix(Map<String, String> parametros) {
        pixQueryScenario.addParameters(parametros);
    }

    @Then("o sistema deve retornar uma lista paginada page {int} e size {int} de transações Pix")
    public void oSistemaDeveRetornarUmaListaPaginadaDeTransacoesPix(int page, int size) {
        var pageDTO = pixQueryScenario.getResponse().as(JsonNode.class);
        assertThat(page).isEqualTo(
                pageDTO.get("number")
                        .asInt());

        assertThat(size).isEqualTo(
                pageDTO.get("size")
                        .asInt());
    }

    @Then("o sistema deve confirmar uma lista ordenada de transações Pix")
    public void oSistemaDeveRetornarUmaListaPaginadaEOrdenadaDeTransaçõesPix() {
        var pageDTO = pixQueryScenario.getResponse().as(JsonNode.class);
        assertThat((pageDTO
                .get("pageable")
                .get("sort")
                .get("sorted")
                .asBoolean()))
                .isTrue();
    }

    @Then("devo validar que retornou {int} registros de transações Pix")
    public void devoValidarQueRetornouRegistrosDeTransaçõesPix(int quantidadeRegistros) {
        var pageDTO = pixQueryScenario.getResponse().as(JsonNode.class);
        assertThat(pageDTO
                .get("numberOfElements").asInt())
                .isEqualTo(quantidadeRegistros);
    }

    @Then("o status da responsa da lista deve ser {int}")
    public void oStatusDaResponsaDeveSer(int statusCode) {
        pixQueryScenario.getResponse()
                .then()
                .assertThat()
                .statusCode(statusCode);
    }

    @Then("o sistema deve retornar uma lista vazia")
    public void oSistemaDeveRetornarUmaListaVazia() {
        assertThat(pixQueryScenario
                .getResponse()
                .asString()).contains("[]");
    }

}
