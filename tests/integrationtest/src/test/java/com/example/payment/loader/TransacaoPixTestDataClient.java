package com.example.payment.loader;


import com.example.payment.builder.TransacaoPixRequestDTOTestDataBuilder;
import com.example.payment.common.RestClient;
import com.example.payment.scenario.dto.TransacaoPixRequestDTO;

public class TransacaoPixTestDataClient {

    private static final int SERVER_PORT = 8000;
    private static final String BASE_URI = "http://localhost";
    private static final String ENDPOINT = "/transacao-pix";

    private final RestClient restClient;

    public TransacaoPixTestDataClient() {
        this.restClient = new RestClient(BASE_URI, SERVER_PORT);
    }

    public String criarTransacaoPix() {
        var request =
                TransacaoPixRequestDTOTestDataBuilder.builder()
                        .comMensagemTransacao("Teste de massa gerado por cenários")
                        .build();
        return criarTransacaoPix(request);
    }

    public String criarTransacaoPix(TransacaoPixRequestDTO request) {
        restClient.addBody(request);
        restClient.executePost(ENDPOINT);

        var responseBody = restClient.getResponse().asString();
        restClient.clearRequestData();
        return responseBody;
    }
}
