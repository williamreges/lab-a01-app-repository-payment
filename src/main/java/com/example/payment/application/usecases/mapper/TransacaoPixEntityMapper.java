package com.example.payment.application.usecases.mapper;

import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import com.example.payment.entrypoint.model.request.TransacaoPixRequest;
import com.example.payment.entrypoint.model.request.TransacaoPixUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransacaoPixEntityMapper {

    TransacaoPixEntityMapper INSTANCE = Mappers.getMapper(TransacaoPixEntityMapper.class);

    TransacaoPixEntity mapperToEntity(TransacaoPixRequest request);

    void mapperUpdateToEntity(TransacaoPixUpdateRequest updateRequest, @MappingTarget TransacaoPixEntity entity);
}
