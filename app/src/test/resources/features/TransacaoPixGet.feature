Feature: : Obter um registro de PIX

  Scenario: Buscar transação Pix existente com sucesso
    Given que existe uma transação Pix com o id "0547d050-04fb-45ce-a6e2-4165aaae444f"
    When eu buscar a transação Pix pelo id "0547d050-04fb-45ce-a6e2-4165aaae444f"
    Then o sistema deve retornar os dados da transação Pix com sucesso

  Scenario: Buscar transação Pix inexistente e receber erro
    Given que existe uma transação Pix com o id "0547d050-04fb-45ce-a6e2-4165aaae444f"
    When eu buscar a transação Pix pelo id "a6e09e0c-c389-416e-bebf-c6893c15002c"
    Then o sistema deve retornar um erro informando que a transação não foi encontrada



