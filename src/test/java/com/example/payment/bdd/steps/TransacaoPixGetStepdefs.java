package com.example.payment.bdd.steps;

import com.example.payment.AppRepositoryPaymentApplication;
import com.example.payment.adapter.input.rest.dto.TransacaoPixResponseDTO;
import com.example.payment.bdd.scenario.TransacaoPixGetScenario;
import com.example.payment.infraestructure.exception.model.ResponseExceptionCustom;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@ContextConfiguration
@CucumberContextConfiguration
@SpringBootTest(classes = AppRepositoryPaymentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransacaoPixGetStepdefs {

    private static final String ENDPOINT = "/transacao-pix/";

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TransacaoPixGetScenario pixGetScenario;


    @Given("que existe uma transação Pix com o id")
    public void queExisteUmaTransaçãoPixComOId() {
        pixGetScenario.gerarNovoRegistroPixDataBase();
    }

    @When("eu buscar a transação Pix pelo id")
    public void euBuscarATransaçãoPixPeloId() {

        var response =
                given()
                        .port(serverPort)
                        .contentType(ContentType.JSON)
                        .headers("correlationID", UUID.randomUUID().toString())
                        .when()
                        .get(ENDPOINT + pixGetScenario.obterCodigoTransacaoPix())
                        .then()
                        .assertThat().statusCode(200)
                        .extract()
                        .response();

        pixGetScenario.addReponse(response);
    }

    @Then("o sistema deve retornar os dados da transação Pix com sucesso")
    public void oSistemaDeveRetornarOsDadosDaTransaçãoPixComSucesso() {
        var responseDTO = pixGetScenario.getResponse().as(TransacaoPixResponseDTO.class);
        Assertions.assertNotNull(responseDTO);
    }

    @Given("que não existe uma transação Pix com o id {string}")
    public void queNãoExisteUmaTransaçãoPixComOId(String codigoTransacaoPix) {
        pixGetScenario.addCodigoTransacaoPix(UUID.fromString(codigoTransacaoPix));
    }

    @When("eu tento buscar a transação Pix pelo id")
    public void euTentoBuscarATransaçãoPixPeloId() {
        var response =
                given()
                        .port(serverPort)
                        .contentType(ContentType.JSON)
                        .headers("correlationID", UUID.randomUUID().toString())
                        .when()
                        .get(ENDPOINT + pixGetScenario.obterCodigoTransacaoPix())
                        .then()
                        .assertThat().statusCode(404)
                        .extract()
                        .response();

        pixGetScenario.addReponse(response);
    }

    @Then("o sistema deve retornar um erro informando que a transação não foi encontrada")
    public void oSistemaDeveRetornarUmErroInformandoQueATransaçãoNãoFoiEncontrada() {
        var responseExceptionDTO = pixGetScenario.getResponse().as(ResponseExceptionCustom.class);
        Assertions.assertNotNull(responseExceptionDTO);
    }

    @After
    public void doSomethingAfter(Scenario scenario) {
        pixGetScenario.deleteAll();
    }

}
