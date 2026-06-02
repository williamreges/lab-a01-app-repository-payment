package com.example.payment.steps;

import com.example.payment.scenario.TransacaoPixPutScenario;
import com.example.payment.scenario.dto.TransacaoPixUpdateRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DocStringType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TransacaoPixPutStepdefs {

    private TransacaoPixPutScenario pixPutScenario;

    @Before
    public void init() {
        pixPutScenario = new TransacaoPixPutScenario();
    }

    @DocStringType
    public TransacaoPixUpdateRequestDTO defineTransacaoPixRequestDTO(String docString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.readValue(docString, TransacaoPixUpdateRequestDTO.class);
    }

    @Given("que existe uma transação Pix com o id {string} para atualizar")
    public void queExisteUmaTransacaoPixComOId(String codigoTransacao) {
        pixPutScenario.prepararTransacaoExistente(codigoTransacao);
    }

    @And("que possuo os dados válidos para atualizar a transação Pix")
    public void quePossuoOsDadosValidosParaAtualizarATransacaoPix(TransacaoPixUpdateRequestDTO dto) {
        pixPutScenario.definirDadosValidosParaAtualizacao(dto);
    }

    @When("eu envio uma requisição para atualizar a transação Pix")
    public void euEnvioUmaRequisicaoParaAtualizarATransacaoPix() {
        pixPutScenario.executarAtualizacao();
    }

    @Then("o sistema deve retornar o status de transação Pix atualizada com sucesso")
    public void oSistemaDeveRetornarStatusDeSucesso() {
        pixPutScenario.getResponse()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Then("o sistema deve retornar um erro informando que a transação a atualizar não foi encontrada")
    public void oSistemaDeveRetornarErroDeNaoEncontrada() {
        pixPutScenario.getResponse()
                .then()
                .assertThat()
                .statusCode(404);
    }

    @After
    public void tearDown() {
        pixPutScenario.limpar();
    }
}
