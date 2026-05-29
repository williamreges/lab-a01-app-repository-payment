Feature: : Obter um registro de PIX

  Scenario: Buscar transação Pix existente com sucesso
    Given que existe uma transação Pix com o id "28d07802-87ed-498a-86d7-e670b620c958"
    When eu buscar a transação Pix pelo id "28d07802-87ed-498a-86d7-e670b620c958"
    Then o status da responsa deve ser 200
    Then o sistema deve retornar os dados da transação Pix id "28d07802-87ed-498a-86d7-e670b620c958" com sucesso

  Scenario: Buscar transação Pix inexistente e receber erro
    Given que existe uma transação Pix com o id "28d07802-87ed-498a-86d7-e670b620c958"
    When eu buscar a transação Pix pelo id "a6e09e0c-c389-416e-bebf-c6893c15002c"
    Then o status da responsa deve ser 404
    Then o sistema deve retornar um erro informando que a transação não foi encontrada



