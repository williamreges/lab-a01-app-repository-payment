@TransacaoPixQuery
Feature: Consultar lista paginada de transações Pix

  Scenario: Buscar lista paginada de transações Pix com sucesso
    Given que existem transações Pix cadastradas
    When eu faço uma requisição GET paginada page 1 e size 5 para listar as transações Pix
     And eu faço uma requisição GET para listar as transações Pix
    Then o status da resposta da lista deve ser 200
    Then o sistema deve retornar uma lista paginada page 1 e size 5 de transações Pix

  Scenario: Buscar lista por Ordenação
    Given que existem transações Pix cadastradas
    When eu faço uma requisição GET paginada page 0 e size 10 para listar as transações Pix
     And eu faço uma requisição GET paginada ordenado por "dataTrancacao" de forma "decrescendo"
     And eu faço uma requisição GET para listar as transações Pix
    Then o status da resposta da lista deve ser 200
    Then o sistema deve confirmar uma lista ordenada de transações Pix

    When eu faço uma requisição GET paginada page 1 e size 5 para listar as transações Pix
     And eu faço uma requisição GET paginada ordenado por "dataTrancacao" de forma "crescente"
     And eu faço uma requisição GET para listar as transações Pix
    Then o status da resposta da lista deve ser 200
     And o sistema deve confirmar uma lista ordenada de transações Pix

  Scenario: Buscar lista por Filtros
    Given que existem transações Pix cadastradas
    When eu faço uma requisição GET paginada page 0 e size 10 para listar as transações Pix
    And eu faço uma filtragem por parametros para listar as transações Pix
      | codigoBeneficiario | e41b55c7-51f5-4035-9ecd-cfd3e7584146 |
     And eu faço uma requisição GET para listar as transações Pix
    Then o status da resposta da lista deve ser 200
    Then devo validar que retornou 4 registros de transações Pix

  Scenario: Buscar lista paginada sem resultados
    Given que não existem transações Pix cadastradas
    When eu faço uma requisição GET paginada page 10 e size 20 para listar as transações Pix
     And eu faço uma requisição GET para listar as transações Pix
    Then o status da resposta da lista deve ser 200
    Then o sistema deve retornar uma lista vazia
