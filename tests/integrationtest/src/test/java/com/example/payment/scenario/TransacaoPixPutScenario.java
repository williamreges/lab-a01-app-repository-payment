package com.example.payment.scenario;

import com.example.payment.common.RestClient;
import com.example.payment.scenario.dto.TransacaoPixRequestDTO;
import com.example.payment.scenario.dto.TransacaoPixUpdateRequestDTO;
import com.example.payment.loader.TransacaoPixTestDataClient;
import io.restassured.response.Response;

public class TransacaoPixPutScenario {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix/{id}";

    private final RestClient restClient;
    private final TransacaoPixTestDataClient testDataClient;

    private String codigoTransacaoGerado;

    public TransacaoPixPutScenario() {
        this.restClient = new RestClient(BASE_URI, SERVER_PORT);
        this.testDataClient = new TransacaoPixTestDataClient();
    }

    public void prepararTransacaoExistente() {
        this.codigoTransacaoGerado = testDataClient.criarTransacaoPix();
    }

    public void prepararTransacaoExistente(TransacaoPixRequestDTO requestDTO) {
        this.codigoTransacaoGerado = testDataClient.criarTransacaoPix(requestDTO);
    }

    public void definirDadosValidosParaAtualizacao(TransacaoPixUpdateRequestDTO dto) {
        restClient.addBody(dto);
    }

    public void executarAtualizacaoDaTransacaoGerada() {
        restClient.executePut(ENDPOINT, codigoTransacaoGerado);
    }

    public void executarAtualizacaoPorId(String codigoTransacao) {
        restClient.executePut(ENDPOINT, codigoTransacao);
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
