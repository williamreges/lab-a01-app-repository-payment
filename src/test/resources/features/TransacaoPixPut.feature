Feature: Atualizar um registro de PIX

  Scenario: Atualizar transação Pix com sucesso
    Given que existe uma transação Pix com o id para atualizar
    And que possuo os dados válidos para atualizar a transação Pix
      """
      {
        "codigoPessoa": "fbc5fbc7-9b55-4058-af41-fa94ae092ae8",
        "valorTrancacao": 50000.50,
        "dataTrancacao": "2026-04-30T00:00:00",
        "codigoBeneficiario": "6444c4e8-9e0e-45cc-8c09-5cec0534c012",
        "mensagemTransacao": "PIX teste de atualizacao"
      }
      """
    When eu envio uma requisição para atualizar a transação Pix
    Then o sistema deve retornar o status de transação Pix atualizada com sucesso

  Scenario: Tentar atualizar transação Pix inexistente
    Given que não existe uma transação Pix para atualizar com o id "b989b12b-f4bf-4d7c-b25a-9c4219b56ef0"
    And que possuo os dados válidos para atualizar a transação Pix
      """
      {
        "codigoPessoa": "fbc5fbc7-9b55-4058-af41-fa94ae092ae8",
        "valorTrancacao": 50000.50,
        "dataTrancacao": "2026-04-30T00:00:00",
        "codigoBeneficiario": "6444c4e8-9e0e-45cc-8c09-5cec0534c012",
        "mensagemTransacao": "PIX teste de atualizacao com erro"
      }
      """
    When eu envio uma requisição para atualizar a transação Pix
    Then o sistema deve retornar um erro informando que a transação a atualizar não foi encontrada
