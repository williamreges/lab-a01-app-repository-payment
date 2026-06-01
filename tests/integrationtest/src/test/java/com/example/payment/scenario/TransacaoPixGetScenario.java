package com.example.payment.scenario;

import com.example.payment.common.RestFeature;
import io.restassured.response.Response;


public class TransacaoPixGetScenario extends RestFeature {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix/{id}";

    public TransacaoPixGetScenario() {
        super(BASE_URI, SERVER_PORT);
    }

    public void gerarMassaTransacaoPix(String codigoTransacao) {
        // Neste exemplo a massa já foi carregada pelo init.sql.
        // Então aqui apenas validamos que o id foi informado.
    }

    public Response requisicaoRest(String codigoTransacao) {
        return executeGet(ENDPOINT, codigoTransacao);
    }
}
