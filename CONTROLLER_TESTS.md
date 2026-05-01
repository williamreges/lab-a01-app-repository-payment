# Testes Unitários - Controller PIX Payment

## ✅ Testes Criados

### 1. **TransacaoPixControllerTest** 
📁 Localização: `src/test/java/com/example/payment/adapter/input/rest/controller/TransacaoPixControllerTest.java`

Testes para todos os endpoints REST do controller:

#### 🔹 POST `/transacao-pix` - Criar Transação
```java
✓ Deve salvar transação com sucesso
✓ Deve retornar erro 400 quando codigoPessoa é nulo
```

#### 🔹 GET `/transacao-pix/{id}` - Obter Transação
```java
✓ Deve retornar transação quando existe
✓ Deve retornar 404 quando não encontra
```

#### 🔹 PUT `/transacao-pix/{id}` - Atualizar Transação
```java
✓ Deve atualizar transação com sucesso
✓ Deve retornar 404 quando não encontra
```

#### 🔹 DELETE `/transacao-pix/{id}` - Deletar Transação
```java
✓ Deve deletar transação com sucesso
✓ Deve retornar 404 quando não encontra
```

#### 🔹 GET `/transacao-pix` - Consultar com Paginação
```java
✓ Deve retornar lista paginada
✓ Deve retornar página vazia
```

---

## 📊 Estrutura dos Testes

### Anotações Utilizadas
- **@WebMvcTest** - Testa controller sem carregar todo o contexto Spring
- **@MockBean** - Mocka a PORT de persistência
- **@Nested** - Agrupa testes por funcionalidade (POST, GET, PUT, DELETE, QUERY)
- **@DisplayName** - Nome amigável para cada teste

### Padrão AAA (Arrange, Act, Assert)
```java
@Test
@DisplayName("Deve salvar transação com sucesso")
void testSaveSuccess() throws Exception {
    // Arrange - Preparar dados
    TransacaoPixRequestDTO requestDTO = new TransacaoPixRequestDTO(...);
    when(transacaoPersistencePort.save(any())).thenReturn(transacaoId);
    
    // Act - Fazer requisição HTTP
    mockMvc.perform(post("/transacao-pix")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
    
    // Assert - Validar resultado
            .andExpect(status().isOk());
}
```

---

## 🚀 Como Executar

### Executar todos os testes do controller
```bash
./mvnw test -Dtest=TransacaoPixControllerTest
```

### Executar um teste específico
```bash
./mvnw test -Dtest=TransacaoPixControllerTest#testSaveSuccess
```

### Executar com saída detalhada
```bash
./mvnw test -Dtest=TransacaoPixControllerTest -v
```

### Executar todos os testes do projeto
```bash
./mvnw test
```

### Gerar relatório de cobertura
```bash
./mvnw test jacoco:report
# Abrir: target/site/jacoco/index.html
```

---

## 🧪 Tecnologias Utilizadas

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| **JUnit 5** | 5.10+ | Framework de testes |
| **Mockito** | 5.x | Mocagem de objetos |
| **Spring Boot Test** | 3.4.2 | Testes Spring |
| **MockMvc** | 3.4.2 | Testes de endpoints HTTP |
| **Java** | 21 | Linguagem |

---

## 📚 Utilitários Fornecidos

### TestDataBuilder
Cria dados de teste com padrão Builder:

```java
// Criar DTO com valores padrão
TransacaoPixRequestDTO dto = TestDataBuilder
    .aTransacaoPixRequestDTO()
    .build();

// Customizar valores
TransacaoPixRequestDTO dto = TestDataBuilder
    .aTransacaoPixRequestDTO()
    .withValorTrancacao(new BigDecimal("500.00"))
    .withMensagemTransacao("Pagamento teste")
    .build();
```

### TestConfig
Configuração de beans de teste:
```java
@Import(TestConfig.class)
class MeuTeste {
    // Beans de teste disponíveis
}
```

---

## 📋 Testes Adicionais Criados

| Arquivo | Localização | Descrição |
|---------|-------------|-----------|
| **BusinessExceptionTest** | `domain/exception/` | Testes de exceções de negócio |
| **TransacaoPixRequestDTOTest** | `adapter/input/rest/dto/` | Testes de validação de DTO |
| **TestDataBuilder** | `com.example.payment/` | Builder para dados de teste |

---

## ✨ Características dos Testes

✅ **@WebMvcTest** - Testa apenas a camada controller  
✅ **Mockito** - Mocka dependências (TransacaoPersistencePort)  
✅ **MockMvc** - Simula requisições HTTP  
✅ **Assertions** - Valida status HTTP e respostas JSON  
✅ **@Nested** - Agrupa testes por endpoint  
✅ **@DisplayName** - Nomes descritivos em português  
✅ **Setup** - @BeforeEach inicializa dados comuns  
✅ **Isolados** - Cada teste é independente  

---

## 🔍 Exemplo de Teste Completo

```java
@Nested
@DisplayName("POST - Criar Transação")
class SaveTransacaoTest {

    @Test
    @DisplayName("Deve salvar transação com sucesso")
    void testSaveSuccess() throws Exception {
        // Arrange
        TransacaoPixRequestDTO requestDTO = new TransacaoPixRequestDTO(
                codigoPessoa,
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                codigoBeneficiario,
                "Descrição"
        );

        when(transacaoPersistencePort.save(any(TransacaoPixRequest.class)))
                .thenReturn(transacaoId);

        // Act & Assert
        mockMvc.perform(post("/transacao-pix")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(transacaoPersistencePort, times(1)).save(any(TransacaoPixRequest.class));
    }

    @Test
    @DisplayName("Deve retornar erro 400 quando codigoPessoa é nulo")
    void testSaveWithoutCodigoPessoa() throws Exception {
        // Arrange
        TransacaoPixRequestDTO requestDTO = new TransacaoPixRequestDTO(
                null,  // codigoPessoa obrigatório
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                codigoBeneficiario,
                "Descrição"
        );

        // Act & Assert
        mockMvc.perform(post("/transacao-pix")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }
}
```

---

## 🎯 Próximos Passos

1. **Ampliar cobertura** - Adicione mais casos de teste (edge cases)
2. **Testes de Integração** - Use `@SpringBootTest` com banco H2
3. **Testes de Performance** - Use JMH para medir latência
4. **CI/CD** - Configure pipeline para executar testes automaticamente
5. **Relatórios** - Configure Jacoco para cobertura de código

---

## 🔗 Referências

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)
- [MockMvc Documentation](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html)

---

**Status**: ✅ Testes compilando e executando com sucesso  
**Versão**: 1.0  
**Última atualização**: 27 de abril de 2026
