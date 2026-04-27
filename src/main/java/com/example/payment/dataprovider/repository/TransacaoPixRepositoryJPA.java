package com.example.payment.dataprovider.repository;

import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TransacaoPixRepositoryJPA extends JpaRepository<TransacaoPixEntity, UUID>, JpaSpecificationExecutor<TransacaoPixEntity> {

}