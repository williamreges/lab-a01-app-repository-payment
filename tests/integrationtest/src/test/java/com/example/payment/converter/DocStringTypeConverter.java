package com.example.payment.converter;

import com.example.payment.scenario.dto.TransacaoPixRequestDTO;
import com.example.payment.scenario.dto.TransacaoPixUpdateRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DocStringType;

public class DocStringTypeConverter {

    @DocStringType
    public TransacaoPixRequestDTO defineTransacaoPixRequestDTO(String docString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.readValue(docString, TransacaoPixRequestDTO.class);
    }

    @DocStringType
    public TransacaoPixUpdateRequestDTO defineTransacaoPixUpdateRequestDTO(String docString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.readValue(docString, TransacaoPixUpdateRequestDTO.class);
    }

}
