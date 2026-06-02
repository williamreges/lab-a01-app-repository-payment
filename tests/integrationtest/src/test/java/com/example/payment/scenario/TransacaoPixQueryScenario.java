package com.example.payment.scenario;

import com.example.payment.common.RestClient;
import io.restassured.response.Response;

import java.util.Map;

public class TransacaoPixQueryScenario {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix";

    private final RestClient restClient;

    public TransacaoPixQueryScenario() {
        this.restClient = new RestClient(BASE_URI, SERVER_PORT);
    }

    public void gerarMassaTransacoesPix() {
        // Neste exemplo a massa já foi carregada pelo init.sql.
    }

    public void adicionarPaginacao(int page, int size) {
        restClient.addPagination(page, size);
    }

    public void adicionarOrdenacao(String nomeAtributo, String direcao) {
        restClient.addSort(nomeAtributo, direcao);
    }

    public void adicionarParametros(Map<String, String> parametros) {
        restClient.addParameters(parametros);
    }

    public void executarConsulta() {
        restClient.executeGet(ENDPOINT);
    }

    public Response getResponse() {
        return restClient.getResponse();
    }

    public void limpar() {
        restClient.clearRequestData();
    }
}
