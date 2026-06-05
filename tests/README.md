# 🚀 Testes E2E com Cucumber

---

## 🎯 Objetivo

Testar de forma integrada os endpoints REST da API `lab-a01-app-repository-payment`, com foco em transações PIX. Os testes validam a criação, consulta, atualização, exclusão e listagem de transações, conferindo tanto a persistência no MySQL quanto os retornos de objetos e coleções.

### ✅ Pré-requisitos

- Java 21
- Maven
- Docker

## ⚙️ Cucumber

O projeto usa Cucumber com JUnit Platform para executar os testes de integração como uma suíte. A classe `RunCucumberTest` carrega os arquivos `.feature` em `integrationtest/src/test/resources/features`, aplica o pacote de glue `com.example.payment` e gera relatórios HTML e JSON em `integrationtest/target/cucumber-reports`.

O uso do Cucumber é importante porque:
- permite descrever os testes como cenários legíveis em linguagem natural
- valida os passos dos casos de transação PIX diretamente contra a API REST
- facilita a comunicação entre a equipe técnica e os requisitos de negócio

### 🌱 Projeto gerado com Cucumber

O módulo de testes foi inicialmente criado com o archetype Cucumber:

```shell
mvn archetype:generate \
  "-DarchetypeGroupId=io.cucumber" \
  "-DarchetypeArtifactId=cucumber-archetype" \
  "-DarchetypeVersion=7.31.0" \
  "-DartifactId=integrationtest" \
  "-DgroupId=lab-a01-app-repository-payment" \
  "-Dpackage=com.example.payment" \
  "-Dversion=1.0.0-SNAPSHOT" \
  "-DinteractiveMode=false"
```

O comando acima gera a estrutura básica de pastas e arquivos mostrada na imagem abaixo:

![img](docs/img.png)

### 📦 Estrutura de Cucumber

- [/features](integrationtest/src/test/resources/features): contém os arquivos `.feature` que descrevem os cenários de teste em linguagem natural.
- [/steps](integrationtest/src/test/java/com/example/payment/steps): implementa a lógica dos passos Given/When/Then usados pelas features.
- [RunCucumberTest.class](integrationtest/src/test/java/com/example/payment/RunCucumberTest.java): configura a suíte Cucumber, localiza as features e define o pacote de glue.
- [cucumber.properties](integrationtest/src/test/resources/cucumber.properties): ajusta opções de execução e geração de relatórios sem alterar o código.
- [Relatórios](integrationtest/target/cucumber-reports): armazena o resultado da execução em HTML e JSON.


### 📦 Dependências relevantes no `pom.xml`

O arquivo [integrationtest/pom.xml](integrationtest/pom.xml) declara as dependências do Cucumber e do JUnit, além do plugin `maven-surefire-plugin` para rodar a suíte de testes.

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-bom</artifactId>
            <version>7.31.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>org.junit</groupId>
            <artifactId>junit-bom</artifactId>
            <version>5.14.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit-platform-engine</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-suite</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 🧪 RunCucumberTest.class - Classe de execução do Cucumber

O projeto utiliza Cucumber com JUnit Platform via a suíte [RunCucumberTest](integrationtest/src/test/java/com/example/payment/RunCucumberTest.java). A classe:
- localiza os arquivos `.feature` em `integrationtest/src/test/resources/features`
- define o pacote de glue `com.example.payment`
- habilita a execução dos passos em `integrationtest/src/test/java/com/example/payment/steps`
- gera relatórios HTML e JSON em `integrationtest/target/cucumber-reports`

```java
package com.example.payment;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParametersResource;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, " +
        "html:target/cucumber-reports/cucumber-report.html, " +
        "json:target/cucumber-reports/cucumber.json")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.payment")
@ConfigurationParametersResource("cucumber.properties")
public class RunCucumberTest {
}
```

## ✨ Customizando o projeto de teste
Este projeto parte de um módulo base Cucumber e foi adaptado para executar os testes E2E específicos da API REST de transações PIX. A customização inclui:
- ajuste dos `steps` para chamar a API via REST;
- inclusão de builders, commons, loaders, dtos e clientes HTTP para suportar os cenários de teste;
- configuração de relatórios e parâmetros de execução para a suíte de integração.

### 🧩 Organização das pastas

A estrutura principal do projeto de testes está em [integrationtest](integrationtest) e está organozado da seguinte forma:

```text
com/example/payment/
├── RunCucumberTest.java
├── builder/
│   ├── TransacaoPixRequestDTOTestDataBuilder.java
│   └── TransacaoPixUpdateRequestDTOTestDataBuilder.java
├── common/
│   └── RestClient.java
├── converter/
│   └── DocStringTypeConverter.java
├── loader/
│   └── TransacaoPixTestDataClient.java
├── scenario/
│   ├── TransacaoPixPostScenario.java
│   ├── TransacaoPixGetScenario.java
│   ├── TransacaoPixPutScenario.java
│   ├── TransacaoPixDeleteScenario.java
│   ├── TransacaoPixQueryScenario.java
│   └── dto/
│       ├── TransacaoPixRequestDTO.java
│       └── TransacaoPixUpdateRequestDTO.java
└── steps/
    ├── TransacaoPixPostStepdefs.java
    ├── TransacaoPixGetStepdefs.java
    ├── TransacaoPixPutStepdefs.java
    ├── TransacaoPixDeleteStepdefs.java
    └── TransacaoPixQueryStepdefs.java

resources/
├── cucumber.properties
└── features/
    ├── TransacaoPixDelete.feature
    ├── TransacaoPixGet.feature
    ├── TransacaoPixPost.feature
    ├── TransacaoPixPut.feature
    └── TransacaoPixQuery.feature
```

### ▶️ Comandos úteis

- Rodar todos os testes de integração:

```bash
mvn -f integrationtest/pom.xml test
```

- Rodar uma feature específica por tag:

```bash
mvn -f integrationtest/pom.xml test -Dcucumber.filter.tags="@tag"
```

- Gerar relatórios: o HTML e o JSON são criados em `integrationtest/target/cucumber-reports`.

### ✨ Personalizações e dicas

- Para alterar o local das `features` ou do `glue`, atualize `RunCucumberTest`.
- Use `integrationtest/src/test/resources/cucumber.properties` para sobrescrever opções do Cucumber, por exemplo:
  - `cucumber.publish.enabled=false`
  - `cucumber.execution.parallel.enabled=true`

---
Arquivo gerado automaticamente: resumo dos casos Cucumber e instruções de configuração.

## Referências

### Cucumber Docs
- [Cucumber io](https://cucumber.io/)
- [Cucumber Documents](https://cucumber.io/docs/cucumber/)
- [Create an empty Cucumber project](https://cucumber.io/docs/guides/10-minute-tutorial/#create-an-empty-cucumber-project)

### GitHub Docs
- [Cucumber](https://github.com/cucumber)
- [Cucumber Java](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-java)
- [Cucumber Spring](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-spring)
- [Cucumber JUnit Platform Engine](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-junit-platform-engine)
- [Cucumber Database](https://github.com/cucumber/cucumber-jvm/tree/main/datatable)
- [Cucumber Expressions](https://github.com/cucumber/cucumber-expressions#readme)
- [Sharing State](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-spring#sharing-state)
- [Languages](https://cucumber.io/docs/gherkin/languages/)
- [Release Notes](https://github.com/cucumber/cucumber-jvm/tree/main/release-notes)
