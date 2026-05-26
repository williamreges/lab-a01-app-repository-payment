Feature: Atualizar um registro de PIX

  Scenario: Atualizar transação Pix com sucesso
    Given que existe uma transação Pix com o id "5966af19-57f2-4c2c-952a-3299e8e4a4b5" para atualizar
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
    When eu envio uma requisição com id "5966af19-57f2-4c2c-952a-3299e8e4a4b5" para atualizar a transação Pix
    Then o sistema deve retornar o status de transação Pix atualizada com sucesso

  Scenario: Tentar atualizar transação Pix inexistente
    Given que existe uma transação Pix com o id "5966af19-57f2-4c2c-952a-3299e8e4a4b5" para atualizar
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
    When eu envio uma requisição com id "1967162d-e651-4ef7-82d5-8b05148819fd" para atualizar a transação Pix
    Then o sistema deve retornar um erro informando que a transação a atualizar não foi encontrada
