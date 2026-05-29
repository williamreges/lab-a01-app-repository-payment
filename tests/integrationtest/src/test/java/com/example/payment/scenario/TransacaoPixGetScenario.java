package com.example.payment.scenario;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.UUID;

import static io.restassured.RestAssured.given;


public class TransacaoPixGetScenario {

    private static final int SERVER_PORT = 8000;
    private static final String ENDPOINT = "/transacao-pix/";

    private Response response;

    public void gerarMassaTransacaoPix(String codigoTransacao) {
        //TODO pode até gerar uma massa a partir desse método
//        this.codigoTransacao = codigoTransacao;
    }

    public void addReponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public Response execulteRequisicaoRest(String codigoTransacao) {
        return given()
                .port(SERVER_PORT)
                .contentType(ContentType.JSON)
                .headers("correlationID", UUID.randomUUID().toString())
                .when()
                .get(ENDPOINT + codigoTransacao)
                .then()
                .extract()
                .response();
    }
}
