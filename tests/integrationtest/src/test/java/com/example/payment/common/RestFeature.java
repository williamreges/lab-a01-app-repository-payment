package com.example.payment.common;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;


public abstract class RestFeature {

    private Response response;
    private Integer page;
    private Integer size;
    private String sort;
    private Map<String, String> parametros;
    private Map<String, Object> heades;
    private Object requestBody;
    private Integer serverPort;
    private final String baseUri;

    protected RestFeature(String baseUri, Integer serverPort) {
        this.baseUri = baseUri;
        this.serverPort = serverPort;
    }

    public void addHeaders(Map<String, Object> headers){
        this.heades = headers;
    }

    public void addParameters(Map<String, String> parameters) {
        this.parametros = parameters;
    }

    public void addBody(Object requestBody){
        this.requestBody = requestBody;
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

    public Response executeGet(String endpoint, Object... queries) {
        var requestSpecification = configRequestSpecification();
        return requestSpecification
                .when()
                .contentType(ContentType.JSON)
                .get(endpoint, queries)
                .then()
                .extract()
                .response();
    }

    public Response executePost(String endpoint, Object... queries) {
        var requestSpecification = configRequestSpecification();
        return requestSpecification
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(endpoint, queries)
                .then()
                .extract()
                .response();
    }

    private RequestSpecification configRequestSpecification() {
        var requestSpecification =
                given().baseUri(baseUri)
                        .port(serverPort);

        addHeadersRequest(requestSpecification);
        addQueryParamsRequest(requestSpecification);
        addPageRequest(requestSpecification);
        addOrderRequest(requestSpecification);
        return requestSpecification;
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

    private boolean existHeaders() {
        return Objects.nonNull(heades) && !heades.isEmpty();
    }

    private boolean existParametersParaFiltrate() {
        return Objects.nonNull(parametros) && !parametros.isEmpty();
    }

    private boolean existPagination() {
        return page != null && size != null;
    }

    private boolean existOrdnance() {
        return sort != null;
    }

    private Integer getPage() {
        return this.page;
    }

    private Integer getSize() {
        return this.size;
    }

    private String getSort() {
        return sort;
    }

    private Map<String, ?> getHeaders() {
        return heades;
    }

    private Map<String, ?> getFiltrates() {
        return parametros;
    }
}
