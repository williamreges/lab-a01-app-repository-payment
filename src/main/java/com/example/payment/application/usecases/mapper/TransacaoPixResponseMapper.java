package com.example.payment.application.usecases.mapper;

import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import com.example.payment.entrypoint.model.response.TransacaoPixResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransacaoPixResponseMapper {

    TransacaoPixResponseMapper INSTANCE = Mappers.getMapper(TransacaoPixResponseMapper.class);

    TransacaoPixResponse mapperToResponse(TransacaoPixEntity entity);
}
