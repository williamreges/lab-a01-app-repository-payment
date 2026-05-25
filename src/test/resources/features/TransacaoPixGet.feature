Feature: : Obter um registro de PIX

  Scenario: Buscar transação Pix existente com sucesso
    Given que existe uma transação Pix com o id
    When eu buscar a transação Pix pelo id
    Then o sistema deve retornar os dados da transação Pix com sucesso

  Scenario: Buscar transação Pix inexistente e receber erro
    Given que não existe uma transação Pix com o id "b989b12b-f4bf-4d7c-b25a-9c4219b56ef0"
    When eu buscar a transação Pix pelo id
    Then o sistema deve retornar um erro informando que a transação não foi encontrada



