package com.example.payment.scenario;

import com.example.payment.common.RestClient;
import com.example.payment.loader.TransacaoPixTestDataClient;
import com.example.payment.scenario.dto.TransacaoPixRequestDTO;
import io.restassured.response.Response;

public class TransacaoPixDeleteScenario {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix/{id}";

    private final RestClient restClient;
    private final TransacaoPixTestDataClient testDataClient;

    private String codigoTransacaoGerado;

    public TransacaoPixDeleteScenario() {
        this.restClient = new RestClient(BASE_URI, SERVER_PORT);
        this.testDataClient = new TransacaoPixTestDataClient();
    }

    public void prepararTransacaoPixExistente() {
        this.codigoTransacaoGerado = testDataClient.criarTransacaoPix();
    }

    public void prepararTransacaoPixExistente(TransacaoPixRequestDTO requestDTO) {
        this.codigoTransacaoGerado = testDataClient.criarTransacaoPix(requestDTO);
    }

    public void deletarTransacaoPixGerada() {
        restClient.executeDelete(ENDPOINT, codigoTransacaoGerado);
    }

    public void deletarTransacaoPixPorId(String codigoTransacao) {
        restClient.executeDelete(ENDPOINT, codigoTransacao);
    }

    public String getCodigoTransacaoGerado() {
        return codigoTransacaoGerado;
    }

    public Response getResponse() {
        return restClient.getResponse();
    }

    public void limpar() {
        restClient.clearRequestData();
    }
}
