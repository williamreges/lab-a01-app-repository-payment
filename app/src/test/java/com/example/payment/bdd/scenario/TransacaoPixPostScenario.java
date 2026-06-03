package com.example.payment.bdd.scenario;

import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class TransacaoPixPostScenario {
    private String requestBody;
    private Response response;

    public void addRequestBody(String jsonBody) {
        this.requestBody = jsonBody;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void addResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

}
