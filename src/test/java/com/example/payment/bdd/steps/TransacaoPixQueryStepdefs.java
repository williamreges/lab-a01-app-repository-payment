package com.example.payment.bdd.steps;

import com.example.payment.bdd.scenario.TransacaoPixQueryPageFeature;
import com.example.payment.builder.TransacaoPixEntityTestDataBuilder;
import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransacaoPixQueryStepdefs {

    private static final String ENDPOINT = "/transacao-pix";

    @Autowired
    private TransacaoPixQueryPageFeature pixQueryScenario;

    @DataTableType
    public TransacaoPixEntity authorEntry(Map<String, String> entry) {
        return TransacaoPixEntityTestDataBuilder.builder()
                .comCodigoTrancacao(UUID.fromString(entry.get("codigoTrancacao")))
                .comCodigoPessoa(UUID.fromString(entry.get("codigoPessoa")))
                .comValorTrancacao(new BigDecimal(entry.get("valorTrancacao")))
                .comDataTrancacao(LocalDateTime.parse(entry.get("dataTrancacao")))
                .comCodigoBeneficiario(UUID.fromString(entry.get("codigoBeneficiario")))
                .comMensagemTransacao(entry.get("mensagemTransacao"))
                .build();
    }

    @Given("que existem transações Pix cadastradas")
    public void queExistemTransacoesPixCadastradas(List<TransacaoPixEntity> transacaoPixEntities) {
        pixQueryScenario.criarTransacoesPixNoBanco(transacaoPixEntities);
    }

    @Given("que não existem transações Pix cadastradas")
    public void queNaoExistemTransacoesPixCadastradas() {
        pixQueryScenario.limparBanco();
    }

    @When("eu faço uma requisição GET paginada page {int} e size {int} para listar as transações Pix")
    public void euFaçoUmaRequisiçãoGETPaginadaPageESizeParaListarAsTransacoesPix(int page, int size) {
        pixQueryScenario.addNumeroDaPaginaComQuantidadeDePaginas(page, size);
    }

    @And("eu faço uma requisição GET para listar as transações Pix")
    public void euFaçoUmaRequisiçãoGETParaListarAsTransacoesPix() {
        var response = pixQueryScenario.executeRequisicaoRest(ENDPOINT);
        pixQueryScenario.addResponse(response);
    }

    @And("eu faço uma requisição GET paginada ordenado por {string} de forma {string}")
    public void euFaçoUmaRequisiçãoGETPaginadaOrdenadoPorDataTransacaoDeFormaDecrescente(String nomeAtributo, String direcao) {
        pixQueryScenario.addOrdenacaoPaginacao(nomeAtributo, direcao);
    }

    @And("eu faço uma filtragem por parametros para listar as transações Pix")
    public void euFaçoUmaFiltragemPorParametrosParaListarAsTransaçõesPix(Map<String, String> parametros) {
        pixQueryScenario.addParametrosFiltros(parametros);
    }

    @Then("o sistema deve retornar uma lista paginada page {int} e size {int} de transações Pix")
    public void oSistemaDeveRetornarUmaListaPaginadaDeTransacoesPix(int page, int size) {
        var pageDTO = pixQueryScenario.getResponse().as(JsonNode.class);
        assertEquals(page, pageDTO.get("number").asInt());
        assertEquals(size, pageDTO.get("size").asInt());
    }

    @Then("o sistema deve confirmar uma lista ordenada de transações Pix")
    public void oSistemaDeveRetornarUmaListaPaginadaEOrdenadaDeTransaçõesPix() {
        var pageDTO = pixQueryScenario.getResponse().as(JsonNode.class);
        assertTrue(pageDTO.get("pageable").get("sort").get("sorted").asBoolean());
    }

    @Then("devo validar que retornou {int} registros de transações Pix")
    public void devoValidarQueRetornouRegistrosDeTransaçõesPix(int quantidadeRegistros) {
        var pageDTO = pixQueryScenario.getResponse().as(JsonNode.class);
        assertEquals(quantidadeRegistros, pageDTO.get("numberOfElements").asInt());
    }

    @Then("o status da responsa deve ser {int}")
    public void oStatusDaResponsaDeveSer(int statusCode) {
        pixQueryScenario.getResponse().then().assertThat().statusCode(statusCode);
    }

    @Then("o sistema deve retornar uma lista vazia")
    public void oSistemaDeveRetornarUmaListaVazia() {
        assertTrue(pixQueryScenario.getResponse().asString().contains("[]"));
    }

    @After
    public void doSomethingAfter(Scenario scenario) {
        pixQueryScenario.limparBanco();
    }
}
