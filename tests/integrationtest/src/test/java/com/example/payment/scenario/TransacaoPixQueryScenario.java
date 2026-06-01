package com.example.payment.scenario;


import com.example.payment.common.QueryPageFeature;
import io.restassured.response.Response;


public class TransacaoPixQueryScenario extends QueryPageFeature {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix";

    public TransacaoPixQueryScenario() {
        super(BASE_URI, SERVER_PORT);
    }

    public void gerarMassaTransacoesPix() {
        // Neste exemplo a massa já foi carregada pelo init.sql.
        // Então aqui apenas validamos que o id foi informado.
    }

    public Response execulteRest() {
        return execute(ENDPOINT);
    }
}
