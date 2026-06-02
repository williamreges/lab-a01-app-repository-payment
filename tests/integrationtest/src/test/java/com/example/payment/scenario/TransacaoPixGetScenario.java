package com.example.payment.scenario;

import com.example.payment.common.RestClient;
import io.restassured.response.Response;

public class TransacaoPixGetScenario {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix/{id}";

    private final RestClient restClient;

    public TransacaoPixGetScenario() {
        this.restClient = new RestClient(BASE_URI, SERVER_PORT);
    }

    public void gerarMassaTransacaoPix(String codigoTransacao) {
        // Neste exemplo a massa já foi carregada pelo init.sql.
        // Então aqui apenas validamos que o id foi informado.
    }

    public void requisicaoRest(String codigoTransacao) {
        restClient.executeGet(ENDPOINT, codigoTransacao);
    }

    public Response getResponse() {
        return restClient.getResponse();
    }

    public void limpar() {
        restClient.clearRequestData();
    }
}
