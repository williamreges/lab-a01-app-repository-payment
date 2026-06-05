@TransacaoPixGet
Feature: Obter um registro de PIX

  Scenario: Buscar transação Pix existente com sucesso
    Given que existe uma nova transação Pix cadastrada para consulta
    When eu buscar a transação Pix gerada
    Then o status da resposta deve ser 200
    And o sistema deve retornar os dados da transação Pix gerada com sucesso

  Scenario: Buscar transação Pix inexistente e receber erro
    When eu buscar a transação Pix pelo id "a6e09e0c-c389-416e-bebf-c6893c15002c"
    Then o status da resposta deve ser 404
    And o sistema deve retornar um erro informando que a transação não foi encontrada
