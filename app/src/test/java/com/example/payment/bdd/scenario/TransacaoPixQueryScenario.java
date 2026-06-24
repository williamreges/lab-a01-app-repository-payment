package com.example.payment.bdd.scenario;

import com.example.payment.bdd.common.RestClient;
import com.example.payment.dataprovider.repository.TransacaoPixRepositoryJPA;
import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ScenarioScope
public class TransacaoPixQueryScenario {

    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix";
    private final RestClient restClient;

    @Autowired
    private TransacaoPixRepositoryJPA repositoryJPA;
    private Response response;

    public TransacaoPixQueryScenario(@LocalServerPort int serverPort) {
        this.restClient = new RestClient(BASE_URI, serverPort);
    }

    public void criarTransacoesPixNoBanco(@MonotonicNonNull List<TransacaoPixEntity> dataTable) {
        repositoryJPA.saveAll(dataTable);
    }

    public void limparBanco() {
        repositoryJPA.deleteAll();
    }

    public void addNumeroDaPaginaComQuantidadeDePaginas(int page, int size) {
        restClient.addPagination(page, size);
    }

    public Response executeRequisicaoRest() {
        return restClient.executeGet(ENDPOINT);
    }

    public void addResponse(Response response) {
        this.response = response;
    }

    public void addOrdenacaoPaginacao(String nomeAtributo, String direcao) {
        restClient.addSort(nomeAtributo, direcao);
    }

    public void addParametrosFiltros(Map<String, String> parametros) {
        restClient.addParameters(parametros);
    }

    public Response getResponse() {
        return response;
    }
}
