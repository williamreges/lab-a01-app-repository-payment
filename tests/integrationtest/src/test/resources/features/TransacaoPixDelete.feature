@TransacaoPixDelete
Feature: Deletar uma transação Pix

  Scenario: Deletar transação Pix existente com sucesso
    Given que existe uma nova transação Pix cadastrada para deletar
    When eu envio uma requisição para deletar a transação Pix gerada
    Then o sistema deve retornar confirmação de deleção com sucesso

