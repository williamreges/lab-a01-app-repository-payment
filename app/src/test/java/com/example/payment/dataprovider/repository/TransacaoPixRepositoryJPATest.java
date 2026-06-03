package com.example.payment.dataprovider.repository;

import com.example.payment.JpaTestApplication;
import com.example.payment.builder.TransacaoPixEntityTestDataBuilder;
import com.example.payment.dataprovider.repository.entity.TransacaoPixEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = JpaTestApplication.class)
@DataJpaTest(properties = {
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update",
        "spring.flyway.enabled=false"
})
@DisplayName("TransacaoPixRepositoryJPA - Testes")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransacaoPixRepositoryJPATest {

    @Autowired
    private TransacaoPixRepositoryJPA repositoryJPA;

    private TransacaoPixEntity entity;

    @BeforeEach
    void setUp() {
        entity = TransacaoPixEntityTestDataBuilder.builder()
                .comCodigoTrancacao(null)
                .build();
        repositoryJPA.save(entity);
    }

    @Test
    @Order(1)
    @DisplayName("test fyndBydId")
    void testeFindById() {
        var responseEntity = repositoryJPA.findById(entity.getCodigoTrancacao());
        Assertions.assertNotNull(responseEntity);
    }

    @Test
    @Order(2)
    @DisplayName("test findByCodigoPessoa")
    void testeFindByCodigoPessoa() {
        var entities = repositoryJPA.findById(entity.getCodigoTrancacao());
        Assertions.assertFalse(entities.isEmpty());
    }

    @Test
    @Order(3)
    @DisplayName("test update Entity")
    void testUpdateEntity() {
        var mensagem = "teste update";
        entity.setMensagemTransacao(mensagem);
        var entityUpdate = repositoryJPA.save(entity);
        Assertions.assertEquals(mensagem, entityUpdate.getMensagemTransacao());
    }

    @AfterEach
    void clean() {
        repositoryJPA.delete(entity);
    }
}