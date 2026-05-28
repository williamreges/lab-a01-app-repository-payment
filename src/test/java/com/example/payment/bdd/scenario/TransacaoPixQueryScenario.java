package com.example.payment.bdd.scenario;

import com.example.payment.dataprovider.repository.TransacaoPixRepositoryJPA;
import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@ScenarioScope
public class TransacaoPixQueryScenario {
    private Response response;

    @Autowired
    private TransacaoPixRepositoryJPA repositoryJPA;

    private Integer page;
    private Integer size;
    private String sort;
    private Map<String, String> parametros;


    public void criarTransacoesPixNoBanco(@MonotonicNonNull List<TransacaoPixEntity> dataTable) {
        repositoryJPA.saveAll(dataTable);
    }

    public void addResponse(Response response) {
        this.response = response;
    }

    public void addNumeroDaPaginaComQuantidadeDePaginas(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public void addOrdenacaoPaginacao(String nomeAtributo, String direcao) {
        if (nomeAtributo == null || nomeAtributo.isBlank()) {
            this.sort = null;
            return;
        }

        var dir = switch (direcao) {
            case "crescente" -> "ASC";
            case "decrescendo" -> "DESC";
            default -> "UNSORTED";
        };
        this.sort = String.format("%s,%s", nomeAtributo.trim(), dir);
    }

    public void addParametrosFiltros(Map<String, String> parametros) {
        this.parametros = parametros;
    }

    public Response getResponse() {
        return response;
    }

    public boolean existePaginacao() {
        return page != null && size != null;
    }

    public boolean existeOrdenacao() {
        return sort != null;
    }

    public boolean existeParametrosParaFiltragem() {
        return Objects.nonNull(parametros) && !parametros.isEmpty();
    }

    public void limparBanco() {
        repositoryJPA.deleteAll();
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getSize() {
        return this.size;
    }

    public String getSort() {
        return sort;
    }

    public Map<String, ?> getFiltros() {
        return parametros;
    }
}
