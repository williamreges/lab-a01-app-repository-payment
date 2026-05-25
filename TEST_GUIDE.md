# Testes Unitários - JUnit 5, Java 17+ e Spring Boot 3.4.x

## 📋 Visão Geral

Este guia apresenta a estrutura completa de testes unitários configurada para o projeto de pagamento PIX, utilizando:
- **JUnit 5** (Jupiter)
- **Java 17+** (compatível com Java 21)
- **Spring Boot 3.4.x**
- **Mockito** para mocagem
- **AssertJ** para assertions fluentes (opcional)

## 🚀 Configuração Inicial

### Dependências já configuradas no `pom.xml`
O projeto já inclui `spring-boot-starter-test` que fornece:
- JUnit 5 (Jupiter)
- Mockito
- AssertJ
- JsonPath
- MockMvc

### Estrutura de Diretórios
```
src/test/java/com/example/payment/
├── adapter/
│   ├── input/rest/
│   │   ├── controller/
│   │   │   └── TransacaoPixControllerTest.java
│   │   └── dto/
│   │       └── DTOTest.java
│   └── output/
├── application/
│   ├── usecases/
│   │   └── mapper/
│   │       └── TransacaoPixResponseMapperTest.java
│   └── exception/
├── domain/
│   └── exception/
│       └── BusinessExceptionTest.java
├── dataprovider/
├── TestConfig.java
└── TestDataBuilder.java
```

## 📝 Tipos de Testes Implementados

### 1. Testes de Controller (@WebMvcTest)
**Arquivo**: `TransacaoPixControllerTest.java`

Testa endpoints HTTP com MockMvc:
- Requisições POST (criar)
- Requisições GET (consultar)
- Requisições PUT (atualizar)
- Requisições DELETE (deletar)
- Validação de status HTTP
- Validação de respostas JSON

```java
@WebMvcTest(TransacaoPixController.class)
class TransacaoPixControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private TransacaoPersistencePort transacaoPersistencePort;
    
    @Test
    void testSaveTransacaoWithSuccess() throws Exception {
        // Arrange, Act, Assert
    }
}
```

### 2. Testes de Mapper
**Arquivo**: `TransacaoPixResponseMapperTest.java`

Testa conversão entre entidades:
- Mapeamento de Domain para DTO
- Tratamento de nulls
- Precisão de valores
- Imutabilidade

### 3. Testes de Exceções
**Arquivo**: `BusinessExceptionTest.java`

Testa hierarquia e comportamento de exceções:
- Lançamento de exceções
- Captura de exceções
- Mensagens de erro
- Stack trace

### 4. Testes de DTOs
**Arquivo**: `DTOTest.java`

Testa classes DTO:
- Setters e Getters
- Valores limites
- Conversão para Domain
- Validação de campos

## 🛠️ Utilitários de Teste

### TestDataBuilder
Facilita a criação de dados de teste com padrão Builder:

```java
// Exemplo 1: Criar com valores padrão
TransacaoPixRequestDTO dto = TestDataBuilder
    .aTransacaoPixRequestDTO()
    .build();

// Exemplo 2: Customizar valores
TransacaoPixResponse response = TestDataBuilder
    .aTransacaoPixResponse()
    .withValor(new BigDecimal("500.00"))
    .withStatus("PENDENTE")
    .build();
```

### TestConfig
Configuração de beans de teste para injeção em testes de integração.

## 🧪 Executando os Testes

### Executar todos os testes
```bash
./mvnw test
```


### Executar testes de integração (Failsafe)
```bash
mvn test-compile failsafe:integration-test failsafe:verify -Dfailsafe.skipAfterFailureCount=1
```
Esse comando executa os testes de integração localizados em `src/test/java/bdd` (ou, se aplicável, em `src/integrationtest/java`) usando o plugin Failsafe. Ele compila os testes, executa os testes de integração e verifica os resultados. O parâmetro `-Dfailsafe.skipAfterFailureCount=1` faz com que a execução pare após a primeira falha, facilitando a identificação rápida de problemas.

**Resumo dos estágios:**
- `test-compile`: compila os testes
- `failsafe:integration-test`: executa testes de integração (`*IT.java`, `*ITCase.java`, etc.)
- `failsafe:verify`: verifica e reporta falhas
- `-Dfailsafe.skipAfterFailureCount=1`: interrompe após a primeira falha

**Localização dos testes de integração:**
- `src/test/java/bdd`

### Executar testes de uma classe específica
```bash
./mvnw test -Dtest=TransacaoPixControllerTest
```

### Executar testes com um padrão
```bash
./mvnw test -Dtest=*Controller*
```

### Executar com cobertura de código
```bash
./mvnw test jacoco:report
```

### Executar testes em paralelo (mais rápido)
```bash
./mvnw test -DthreadCount=4
```

## 📊 Anotações JUnit 5 Principais

### @Test
Marca um método como teste.

### @DisplayName
Define nome amigável para o teste:
```java
@DisplayName("Deve criar transação com sucesso")
void testSaveTransacaoWithSuccess() { }
```

### @Nested
Agrupa testes relacionados:
```java
@Nested
@DisplayName("POST /transacao-pix - Criar Transação")
class SaveTransacaoTest { }
```

### @BeforeEach
Executa antes de cada teste (setup):
```java
@BeforeEach
void setUp() {
    // Inicializar dados de teste
}
```

### @ParameterizedTest
Executa teste com múltiplos parâmetros:
```java
@ParameterizedTest
@ValueSource(strings = { "100.00", "50.25", "999.99" })
void testMultipleValues(String valor) { }
```

## 🎯 Boas Práticas

### 1. Padrão AAA (Arrange, Act, Assert)
```java
@Test
void testSalvar() {
    // Arrange - Preparar dados
    TransacaoPixRequestDTO dto = new TransacaoPixRequestDTO();
    dto.setValor(new BigDecimal("100.00"));
    
    // Act - Executar ação
    String resultado = controller.save(dto);
    
    // Assert - Validar resultado
    assertNotNull(resultado);
    assertEquals(transacaoId, resultado);
}
```

### 2. Um Assert por Teste
Cada teste deve validar um comportamento específico. Se precisar de múltiplos asserts, use testes separados.

### 3. Nomes Descritivos
```java
// ❌ Ruim
void test1() { }

// ✅ Bom
@DisplayName("Deve validar erro 404 quando transação não existe")
void testGetByIdNotFound() { }
```

### 4. Mock vs Stub
- **Mock**: Objetos que verificam interações (verify)
- **Stub**: Objetos que retornam valores fixos

```java
// Mock - verifica se foi chamado
@MockBean
private TransacaoPersistencePort port;

// Stub - retorna valor
when(port.getById("123")).thenReturn(transacao);
```

### 5. Evitar Testes Interdependentes
Cada teste deve ser independente e poder executar em qualquer ordem.

### 6. Testes de Comportamento, não Implementação
```java
// ❌ Ruim - testa implementação
void testPrivateMethod() { }

// ✅ Bom - testa comportamento público
void testSaveReturnsPersistenceId() { }
```

## 🔍 Verificação com Mockito

### when().thenReturn() - Stubbing
```java
when(port.getById("123")).thenReturn(transacao);
```

### doNothing/doThrow - Métodos Void
```java
doNothing().when(port).delete("123");
doThrow(new EntityNotFoundBusinessException()).when(port).delete("999");
```

### verify() - Verificar Interações
```java
verify(port, times(1)).getById("123");
verify(port, never()).save(any());
verify(port, atLeastOnce()).query(any(), any());
```

### ArgumentMatchers
```java
when(port.save(any(TransacaoPixRequest.class))).thenReturn(id);
when(port.getById(anyString())).thenReturn(transacao);
when(port.query(any(), eq(pageable))).thenReturn(page);
```

## 🔗 Anotações Spring Boot Test

### @WebMvcTest
Para testes de controller sem carregar todo o contexto Spring:
```java
@WebMvcTest(TransacaoPixController.class)
class TransacaoPixControllerTest { }
```

### @SpringBootTest
Para testes de integração com todo o contexto:
```java
@SpringBootTest
class IntegrationTest { }
```

### @MockBean
Cria mock gerenciado pelo Spring:
```java
@MockBean
private TransacaoPersistencePort port;
```

### @ExtendWith
Extensão do JUnit 5:
```java
@ExtendWith(SpringExtension.class)
class MyTest { }
```

## 💡 Exemplos Práticos

### Teste com Paginação
```java
@Test
void testQueryWithPagination() throws Exception {
    // Arrange
    Page<TransacaoPixResponse> page = new PageImpl<>(
        List.of(response1, response2),
        PageRequest.of(0, 10),
        2
    );
    when(port.query(any(), any())).thenReturn(page);
    
    // Act & Assert
    mockMvc.perform(get("/transacao-pix?page=0&size=10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(2)));
}
```

### Teste de Exceção
```java
@Test
void testNotFound() throws Exception {
    // Arrange
    when(port.getById("999")).thenThrow(
        new EntityNotFoundBusinessException("Não encontrada")
    );
    
    // Act & Assert
    mockMvc.perform(get("/transacao-pix/999"))
        .andExpect(status().isNotFound());
}
```

### Teste com RequestBody
```java
@Test
void testSaveWithValidation() throws Exception {
    // Act & Assert
    mockMvc.perform(post("/transacao-pix")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
        .andExpect(status().isOk())
        .andExpect(result -> {
            String id = result.getResponse().getContentAsString();
            assertNotNull(id);
        });
}
```

## 📦 Cobertura de Código

### Configurar Jacoco
Adicione ao `pom.xml`:
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.8</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

### Gerar relatório
```bash
./mvnw test jacoco:report
# Relatório em: target/site/jacoco/index.html
```

## 🐛 Troubleshooting

### Teste não encontra classe de teste
- Certifique-se que a classe termina com `Test` ou `Tests`
- Coloque em `src/test/java`

### Erro "No qualifying bean"
- Use `@MockBean` para mockar a dependência
- Ou use `@Import(TestConfig.class)` para adicionar beans

### Timeout em testes
- Adicione `@Timeout(2)` para timeout de 2 segundos
- Verificar se há chamadas bloqueantes

### MockMvc retorna 404
- Verifique se a anotação `@RestController` está no controller
- Verifique o path mapping

## 🔗 Referências

- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)
- [AssertJ Assertions](https://assertj.github.io/assertj-core-features-highlight.html)

## 📝 Próximos Passos

1. **Ampliar Cobertura**: Adicione testes para repository, service e especificações
2. **Testes de Integração**: Crie testes com `@SpringBootTest` e banco de dados em memória
3. **Testes de Performance**: Use JMH para medir desempenho
4. **Testes Parametrizados**: Use `@ParameterizedTest` para múltiplos cenários
5. **CI/CD**: Configure pipeline para executar testes automaticamente

---

**Última atualização**: 27 de abril de 2026
**Versão**: 1.0
