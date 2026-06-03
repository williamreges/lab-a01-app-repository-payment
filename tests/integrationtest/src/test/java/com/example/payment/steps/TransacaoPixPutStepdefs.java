package com.example.payment.steps;

import com.example.payment.scenario.TransacaoPixPutScenario;
import com.example.payment.scenario.dto.TransacaoPixUpdateRequestDTO;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class TransacaoPixPutStepdefs {

    private TransacaoPixPutScenario pixPutScenario;

    @Before
    public void init() {
        pixPutScenario = new TransacaoPixPutScenario();
    }

    @Given("que existe uma nova transação Pix cadastrada para atualizar")
    public void queExisteUmaNovaTransacaoPixCadastradaParaAtualizar() {
        pixPutScenario.prepararTransacaoExistente();
    }

    @And("que possuo os dados válidos para atualizar a transação Pix")
    public void quePossuoOsDadosValidosParaAtualizarATransacaoPix(TransacaoPixUpdateRequestDTO dto) {
        pixPutScenario.definirDadosValidosParaAtualizacao(dto);
    }

    @When("eu envio uma requisição para atualizar a transação Pix gerada")
    public void euEnvioUmaRequisicaoParaAtualizarATransacaoPixGerada() {
        pixPutScenario.executarAtualizacaoDaTransacaoGerada();
    }

    @When("eu envio uma requisição para atualizar a transação Pix pelo id {string}")
    public void euEnvioUmaRequisicaoParaAtualizarATransacaoPixPeloId(String codigoTransacao) {
        pixPutScenario.executarAtualizacaoPorId(codigoTransacao);
    }

    @Then("o sistema deve retornar o status de transação Pix atualizada com sucesso")
    public void oSistemaDeveRetornarOStatusDeTransacaoPixAtualizadaComSucesso() {
        assertThat(pixPutScenario.getResponse().statusCode()).isEqualTo(200);
    }

    @Then("o sistema deve retornar um erro informando que a transação a atualizar não foi encontrada")
    public void oSistemaDeveRetornarUmErroInformandoQueATransacaoAAtualizarNaoFoiEncontrada() {
        assertThat(pixPutScenario.getResponse().statusCode()).isEqualTo(404);
    }

    @After
    public void tearDown() {
        pixPutScenario.limpar();
    }
}
