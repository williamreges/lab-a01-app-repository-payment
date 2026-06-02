package com.example.payment.scenario;

import com.example.payment.common.RestClient;
import com.example.payment.scenario.dto.TransacaoPixRequestDTO;
import io.restassured.response.Response;

public class TransacaoPixPostScenario {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix";

    private final RestClient restClient;

    public TransacaoPixPostScenario() {
        this.restClient = new RestClient(BASE_URI, SERVER_PORT);
    }

    public void adicionaBodyParaPersistencia(TransacaoPixRequestDTO jsonBody) {
        restClient.addBody(jsonBody);
    }

    public Response euEnvioUmaRequisicaoParaCriarATransacaoPix() {
        return restClient.executePost(ENDPOINT);
    }

    public Response getResponse() {
        return restClient.getResponse();
    }

    public void limpar() {
        restClient.clearRequestData();
    }
}
