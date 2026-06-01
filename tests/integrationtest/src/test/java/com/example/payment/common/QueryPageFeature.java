package com.example.payment.common;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;


public abstract class QueryPageFeature {

    private Response response;
    private Integer page;
    private Integer size;
    private String sort;
    private Map<String, String> parametros;
    private Map<String, Object> heades;
    private Integer serverPort;
    private final String baseUri;

    protected QueryPageFeature(String baseUri, Integer serverPort) {
        this.baseUri = baseUri;
        this.serverPort = serverPort;
    }

    public void adHeaders(Map<String, Object> headers){
        this.heades = headers;
    }

    public void addParameters(Map<String, String> parameters) {
        this.parametros = parameters;
    }

    public void addNumberPageAndSize(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public void addOrderPage(String nomeAtributo, String direcao) {
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

    public void addResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public Response execute(String endpoint, Object... queries) {
        var requestSpecification =
                given().baseUri(baseUri)
                        .port(serverPort);

        addHeadersRequest(requestSpecification);
        addQueryParamsRequest(requestSpecification);
        addPageRequest(requestSpecification);
        addOrderRequest(requestSpecification);

        return requestSpecification
                .when()
                .get(endpoint, queries)
                .then()
                .extract()
                .response();
    }

    private void addHeadersRequest(RequestSpecification requestSpecification) {
        if (existHeaders()) {
            requestSpecification.headers(
                    getHeaders());
        }
    }

    private void addQueryParamsRequest(RequestSpecification requestSpecification) {
        if (existParametersParaFiltrate()) {
            requestSpecification.queryParams(
                    getFiltrates());
        }
    }

    private void addOrderRequest(RequestSpecification requestSpecification) {
        if (existOrdnance()) {
            requestSpecification
                    .queryParam("sort", getSort());
        }
    }

    private void addPageRequest(RequestSpecification requestSpecification) {
        if (existPagination()) {
            requestSpecification
                    .queryParam("page", getPage())
                    .queryParam("size", getSize());
        }
    }

    public boolean existHeaders() {
        return Objects.nonNull(heades) && !heades.isEmpty();
    }

    public boolean existParametersParaFiltrate() {
        return Objects.nonNull(parametros) && !parametros.isEmpty();
    }

    public boolean existPagination() {
        return page != null && size != null;
    }

    public boolean existOrdnance() {
        return sort != null;
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

    public Map<String, ?> getHeaders() {
        return heades;
    }

    public Map<String, ?> getFiltrates() {
        return parametros;
    }
}
