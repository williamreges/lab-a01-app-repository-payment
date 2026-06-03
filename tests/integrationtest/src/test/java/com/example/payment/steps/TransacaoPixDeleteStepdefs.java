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
        this.pixDeleteScenario = new TransacaoPixDeleteScenario();
    }

    @Given("que existe uma nova transação Pix cadastrada para deletar")
    public void queExisteUmaNovaTransacaoPixCadastradaParaDeletar() {
        pixDeleteScenario.prepararTransacaoPixExistente();
    }

    @When("eu envio uma requisição para deletar a transação Pix gerada")
    public void euEnvioUmaRequisicaoParaDeletarATransacaoPixGerada() {
        pixDeleteScenario.deletarTransacaoPixGerada();
    }

    @When("eu envio uma requisição para deletar a transação Pix pelo id {string}")
    public void euEnvioUmaRequisicaoParaDeletarATransacaoPixPeloId(String codigoTransacao) {
        pixDeleteScenario.deletarTransacaoPixPorId(codigoTransacao);
    }

    @Then("o sistema deve retornar confirmação de deleção com sucesso")
    public void oSistemaDeveRetornarConfirmacaoDeDelecaoComSucesso() {
        assertThat(pixDeleteScenario.getResponse().statusCode()).isEqualTo(200);
    }

    @Then("o sistema deve retornar um erro informando que a transação não foi encontrada para deletar")
    public void oSistemaDeveRetornarUmErroInformandoQueATransacaoNaoFoiEncontradaParaDeletar() {
        assertThat(pixDeleteScenario.getResponse().statusCode()).isEqualTo(404);
    }

    @After
    public void tearDown() {
        pixDeleteScenario.limpar();
    }
}
