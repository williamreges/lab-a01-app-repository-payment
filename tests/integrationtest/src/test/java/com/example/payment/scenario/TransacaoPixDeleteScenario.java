package com.example.payment.scenario;

import com.example.payment.common.RestClient;
import io.restassured.response.Response;

public class TransacaoPixDeleteScenario {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix/{id}";

    private final RestClient restClient;

    public TransacaoPixDeleteScenario() {
        this.restClient = new RestClient(BASE_URI, SERVER_PORT);
    }

    public void prepararTransacaoPixExistente(String codigoTransacao) {
        // Se necessário, preparar a massa via REST.
        // Caso o ambiente já possua massa fixa, não precisa fazer nada aqui.
    }

    public void deletarTransacaoPixPorId(String codigoTransacao) {
        restClient.executeDelete(ENDPOINT, codigoTransacao);
    }

    public Response getResponse() {
        return restClient.getResponse();
    }

    public void limpar() {
        restClient.clearRequestData();
    }
}
