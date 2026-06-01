package com.example.payment.scenario;

import com.example.payment.common.RestFeature;
import io.restassured.response.Response;


public class TransacaoPixPostScenario extends RestFeature {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix";


    public TransacaoPixPostScenario() {
        super(BASE_URI, SERVER_PORT);
    }

    public void adicionaBodyParaPersistencia(String jsonBody) {
        addBody(jsonBody);
    }

    public Response euEnvioUmaRequisicaoParaCriarATransacaoPix() {
        return executePost(ENDPOINT);
    }


}
