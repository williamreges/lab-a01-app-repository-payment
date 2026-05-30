package com.example.payment.scenario;

import com.example.payment.common.QueryPageFeature;
import io.restassured.response.Response;


public class TransacaoPixGetScenario extends QueryPageFeature {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix/{id}";

    public TransacaoPixGetScenario() {
        super(BASE_URI, SERVER_PORT);
    }

    public void gerarMassaTransacaoPix(String codigoTransacao) {
        //TODO pode até gerar uma massa a partir desse método
    }

    public Response requisicaoRest(String codigoTransacao) {
        return execulte(ENDPOINT, codigoTransacao);
    }
}
