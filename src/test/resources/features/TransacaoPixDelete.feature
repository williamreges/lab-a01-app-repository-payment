Feature: : Deletar um registro de PIX

  Scenario: Deletar transação Pix existente com sucesso
    Given que existe uma transação Pix com o id "8733e18a-f101-4b96-b4a3-d63e5cd0fdeb" paa deletar
    When eu envio uma requisição para deletar a transação Pix pelo id "8733e18a-f101-4b96-b4a3-d63e5cd0fdeb"
    Then o sistema deve retornar confirmação de deleção com sucesso

  Scenario: Tentar deletar transação Pix inexistente
    Given que existe uma transação Pix com o id "8733e18a-f101-4b96-b4a3-d63e5cd0fdeb" paa deletar
    When eu envio uma requisição para deletar a transação Pix pelo id "56d5cf1f-39b8-424b-9445-f1d0f5ca85e0"
    Then o sistema deve retornar um erro informando que a transação não foi encontrada para deletar
