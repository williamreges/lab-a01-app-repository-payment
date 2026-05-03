package com.example.payment.adapter.input.rest.controller;

import com.example.payment.builder.TransacaoPixRequestDTOTestDataBuilder;
import com.example.payment.builder.TransacaoPixResponseTestDataBuilder;
import com.example.payment.builder.TransacaoPixUpdateRequestDTOTestDataBuilder;
import com.example.payment.domain.entity.TransacaoPixQueryRequest;
import com.example.payment.domain.entity.TransacaoPixRequest;
import com.example.payment.domain.entity.TransacaoPixResponse;
import com.example.payment.domain.entity.TransacaoPixUpdateRequest;
import com.example.payment.domain.port.TransacaoPersistencePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("TransacaoPixController - Testes")
@WebMvcTest(TransacaoPixController.class)
@ContextConfiguration(classes = {
        TransacaoPixController.class
})
class TransacaoPixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransacaoPersistencePort transacaoPersistencePort;

    @Autowired
    private ObjectMapper objectMapper;

    private String transacaoId;

    @BeforeEach
    void setUp() {
        transacaoId = UUID.randomUUID().toString();
    }

    @Nested
    @DisplayName("POST - Criar Transação")
    class SaveTransacaoTest {

        @Test
        @DisplayName("Deve salvar transação com sucesso")
        void testSaveSuccess() throws Exception {
            var requestDTO = TransacaoPixRequestDTOTestDataBuilder.builder().build();

            when(transacaoPersistencePort.save(any(TransacaoPixRequest.class)))
                    .thenReturn(transacaoId);

            mockMvc.perform(post("/transacao-pix")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print());

            verify(transacaoPersistencePort, times(1)).save(any(TransacaoPixRequest.class));
        }

    }

    @Nested
    @DisplayName("GET - Obter Transação")
    class GetByIdTest {

        @Test
        @DisplayName("Deve retornar transação quando existe")
        void testGetByIdSuccess() throws Exception {
            var response = TransacaoPixResponseTestDataBuilder.builder().build();

            when(transacaoPersistencePort.getById(transacaoId))
                    .thenReturn(response);

            mockMvc.perform(get("/transacao-pix/{id}", transacaoId))
                    .andExpect(status().isOk())
                    .andDo(print());

            verify(transacaoPersistencePort, times(1)).getById(transacaoId);
        }

    }

    @Nested
    @DisplayName("PUT - Atualizar Transação")
    class UpdateTransacaoTest {

        @Test
        @DisplayName("Deve atualizar transação com sucesso")
        void testUpdateSuccess() throws Exception {
            var updateDTO = TransacaoPixUpdateRequestDTOTestDataBuilder.builder()
                    .comValorTrancacao(new BigDecimal("150.00"))
                    .comMensagemTransacao("Atualizada")
                    .build();

            doNothing().when(transacaoPersistencePort)
                    .update(anyString(), any(TransacaoPixUpdateRequest.class));

            mockMvc.perform(put("/transacao-pix/{id}", transacaoId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(updateDTO)))
                    .andExpect(status().isOk())
                    .andDo(print());

            verify(transacaoPersistencePort, times(1))
                    .update(anyString(), any(TransacaoPixUpdateRequest.class));
        }

    }

    @Nested
    @DisplayName("DELETE - Deletar Transação")
    class DeleteTransacaoTest {

        @Test
        @DisplayName("Deve deletar transação com sucesso")
        void testDeleteSuccess() throws Exception {
            doNothing().when(transacaoPersistencePort).delete(transacaoId);

            mockMvc.perform(delete("/transacao-pix/{id}", transacaoId))
                    .andExpect(status().isOk())
                    .andDo(print());

            verify(transacaoPersistencePort, times(1)).delete(transacaoId);
        }

    }

    @Nested
    @DisplayName("GET - Consultar com Paginação")
    class QueryTransacaoTest {

        @Test
        @DisplayName("Deve retornar lista paginada")
        void testQuerySuccess() throws Exception {
            var response1 = TransacaoPixResponseTestDataBuilder.builder().build();
            var page = new PageImpl<>(
                    List.of(response1),
                    PageRequest.of(0, 10),
                    1
            );

            when(transacaoPersistencePort.query(any(TransacaoPixQueryRequest.class), any()))
                    .thenReturn(page);

            mockMvc.perform(get("/transacao-pix")
                            .param("page", "0")
                            .param("size", "10"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content", hasSize(1)))
                    .andDo(print());
        }

        @Test
        @DisplayName("Deve retornar página vazia")
        void testQueryEmpty() throws Exception {
            Page<TransacaoPixResponse> emptyPage = new PageImpl<>(
                    List.of(),
                    PageRequest.of(0, 10),
                    0
            );

            when(transacaoPersistencePort.query(any(TransacaoPixQueryRequest.class), any()))
                    .thenReturn(emptyPage);

            mockMvc.perform(get("/transacao-pix"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content", hasSize(0)));
        }
    }
}
