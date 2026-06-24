package com.example.payment.bdd.common;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class RestClient {

    private Response response;
    private Integer page;
    private Integer size;
    private String sort;
    private Map<String, String> queryParams;
    private Map<String, Object> headers;
    private Object requestBody;

    private final int serverPort;
    private final String baseUri;

    public RestClient(String baseUri, int serverPort) {
        this.baseUri = baseUri;
        this.serverPort = serverPort;
    }

    public void addHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public void addParameters(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public void addBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public void addPagination(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public void addSort(String nomeAtributo, String direcao) {
        if (nomeAtributo == null || nomeAtributo.isBlank()) {
            this.sort = null;
            return;
        }

        String dir = switch (direcao) {
            case "crescente" -> "ASC";
            case "decrescendo" -> "DESC";
            default -> throw new IllegalArgumentException("Direção inválida: " + direcao);
        };

        this.sort = String.format("%s,%s", nomeAtributo.trim(), dir);
    }

    public Response getResponse() {
        return response;
    }

    public Response executeGet(String endpoint, Object... pathParams) {
        response = configRequestSpecification()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get(endpoint, pathParams)
                .then()
                .extract()
                .response();

        return response;
    }

    public Response executePost(String endpoint, Object... pathParams) {
        response = configRequestSpecification()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint, pathParams)
                .then()
                .extract()
                .response();

        return response;
    }

    public Response executePut(String endpoint, Object... pathParams) {
        response = configRequestSpecification()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(endpoint, pathParams)
                .then()
                .extract()
                .response();

        return response;
    }

    public Response executePatch(String endpoint, Object... pathParams) {
        response = configRequestSpecification()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(endpoint, pathParams)
                .then()
                .extract()
                .response();

        return response;
    }

    public Response executeDelete(String endpoint, Object... pathParams) {
        response = configRequestSpecification()
                .when()
                .delete(endpoint, pathParams)
                .then()
                .extract()
                .response();

        return response;
    }

    public void clearRequestData() {
        this.page = null;
        this.size = null;
        this.sort = null;
        this.queryParams = null;
        this.headers = null;
        this.requestBody = null;
    }

    private RequestSpecification configRequestSpecification() {
        RequestSpecification requestSpecification = given()
                .baseUri(baseUri)
                .port(serverPort)
                .log().all()
                .header("correlationID", UUID.randomUUID().toString());

        addHeadersRequest(requestSpecification);
        addQueryParamsRequest(requestSpecification);
        addPageRequest(requestSpecification);
        addSortRequest(requestSpecification);

        return requestSpecification;
    }

    private void addHeadersRequest(RequestSpecification requestSpecification) {
        if (hasHeaders()) {
            requestSpecification.headers(headers);
        }
    }

    private void addQueryParamsRequest(RequestSpecification requestSpecification) {
        if (hasQueryParams()) {
            requestSpecification.queryParams(queryParams);
        }
    }

    private void addSortRequest(RequestSpecification requestSpecification) {
        if (hasSort()) {
            requestSpecification.queryParam("sort", sort);
        }
    }

    private void addPageRequest(RequestSpecification requestSpecification) {
        if (hasPagination()) {
            requestSpecification
                    .queryParam("page", page)
                    .queryParam("size", size);
        }
    }

    private boolean hasHeaders() {
        return Objects.nonNull(headers) && !headers.isEmpty();
    }

    private boolean hasQueryParams() {
        return Objects.nonNull(queryParams) && !queryParams.isEmpty();
    }

    private boolean hasPagination() {
        return page != null && size != null;
    }

    private boolean hasSort() {
        return sort != null;
    }
}
