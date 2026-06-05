@TransacaoPixPut
Feature: Atualizar uma transação Pix

  Scenario: Atualizar transação Pix existente com sucesso
    Given que existe uma nova transação Pix cadastrada para atualizar
    And que possuo os dados válidos para atualizar a transação Pix
      """
      {
        "codigoPessoa": "90e7f156-d3e6-4b8c-868c-4ef89a6af898",
        "valorTrancacao": 250.00,
        "dataTrancacao": "2025-01-10T10:15:30",
        "codigoBeneficiario": "8a87b7c1-776b-4458-8bb3-7db2591a7cd6",
        "mensagemTransacao": "Pagamento atualizado com sucesso"
      }
      """
    When eu envio uma requisição para atualizar a transação Pix gerada
    Then o sistema deve retornar o status de transação Pix atualizada com sucesso

  Scenario: Tentar atualizar uma transação Pix inexistente
    And que possuo os dados válidos para atualizar a transação Pix
      """
      {
        "codigoPessoa": "07b0bef5-4f88-4537-81ee-92c5ef86f341",
        "valorTrancacao": 250.00,
        "dataTrancacao": "2025-01-10T10:15:30",
        "codigoBeneficiario": "d7afe8cd-40b4-4e10-bd47-cefafab705f2",
        "mensagemTransacao": "Pagamento atualizado com sucesso"
      }
      """
    When eu envio uma requisição para atualizar a transação Pix pelo id "a6e09e0c-c389-416e-bebf-c6893c15002c"
    Then o sistema deve retornar um erro informando que a transação a atualizar não foi encontrada
