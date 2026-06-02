package com.example.payment.scenario;

import com.example.payment.common.RestFeature;


public class TransacaoPixPutScenario extends RestFeature {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix/{id}";

    private String codigoTransacao;

    public TransacaoPixPutScenario() {
        super(BASE_URI, SERVER_PORT);
    }

    public void prepararTransacaoExistente(String codigoTransacao) {
        this.codigoTransacao = codigoTransacao;
        // criar massa aqui, se necessário
    }

    public void executarAtualizacao() {
        executePut(ENDPOINT, codigoTransacao);
    }
}

