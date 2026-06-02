package com.example.payment.scenario;

import com.example.payment.common.RestClient;
import com.example.payment.scenario.dto.TransacaoPixUpdateRequestDTO;
import io.restassured.response.Response;

public class TransacaoPixPutScenario {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix/{id}";

    private final RestClient restClient;
    private String codigoTransacao;

    public TransacaoPixPutScenario() {
        this.restClient = new RestClient(BASE_URI, SERVER_PORT);
    }

    public void prepararTransacaoExistente(String codigoTransacao) {
        this.codigoTransacao = codigoTransacao;

        // TODO criar massa de teste aqui
    }

    public void definirDadosValidosParaAtualizacao(TransacaoPixUpdateRequestDTO dto) {
        restClient.addBody(dto);
    }

    public void executarAtualizacao() {
        restClient.executePut(ENDPOINT, codigoTransacao);
    }

    public Response getResponse() {
        return restClient.getResponse();
    }

    public void limpar() {
        restClient.clearRequestData();
    }
}

