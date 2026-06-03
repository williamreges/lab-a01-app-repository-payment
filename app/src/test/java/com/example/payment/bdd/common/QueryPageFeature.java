package com.example.payment.bdd.common;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static io.restassured.RestAssured.given;


public abstract class QueryPageFeature {

    @LocalServerPort
    private int serverPort;

    private Response response;
    private Integer page;
    private Integer size;
    private String sort;
    private Map<String, String> parametros;


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

    public Response executeRequisicaoRest(String endpoint) {
        var requestSpecification =
                given()
                        .port(serverPort)
                        .header("correlationID", UUID.randomUUID().toString());

        adicionarPaginacao(requestSpecification);

        adicionarQueryParams(requestSpecification);

        adicionarOrdenacao(requestSpecification);

        return requestSpecification
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    private void adicionarOrdenacao(RequestSpecification requestSpecification) {
        if (existeOrdenacao()) {
            requestSpecification
                    .queryParam("sort", getSort());
        }
    }

    private void adicionarQueryParams(RequestSpecification requestSpecification) {
        if (existeParametrosParaFiltragem()) {
            requestSpecification.queryParams(
                    getFiltros());
        }
    }

    private void adicionarPaginacao(RequestSpecification requestSpecification) {
        if (existePaginacao()) {
            requestSpecification
                    .queryParam("page", getPage())
                    .queryParam("size", getSize());
        }
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
