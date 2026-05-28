@tag
Feature: Consultar lista paginada de transações Pix

  Scenario: Buscar lista paginada de transações Pix com sucesso
    Given que existem transações Pix cadastradas
      | codigoTrancacao                      | codigoPessoa                         | valorTrancacao | dataTrancacao       | codigoBeneficiario                   | mensagemTransacao            |
      | 28d07802-87ed-498a-86d7-e670b620c958 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-26T21:14:59 | e41b55c7-51f5-4035-9ecd-cfd3e7584146 | PIX para compra de carro     |
      | 5007c5c7-16b2-43f1-bd73-25083f659157 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-25T23:00:45 | c6a7224e-1c1b-4e82-9129-3739d12f7a43 | PIX para compra de moto      |
      | 52fa9f41-d1a8-472a-9754-8926c4e470bd | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | -30000.50      | 2026-05-25T22:58:24 | ba726966-155c-4c4b-ba7d-449de890c858 | PIX para compra de jetsky    |
      | 65a06c49-1ed8-46ff-805a-58802e8b0bf9 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-26T15:30:27 | 515e9c2a-74b2-45db-a3fd-b6dd025bd0a4 | PIX para compra de barco     |
      | 69e38f23-3d04-4a9d-b522-b2debab1f075 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 50000.50       | 2026-04-30T00:00:00 | 6444c4e8-9e0e-45cc-8c09-5cec0534c012 | PIX teste de atualizacao     |
      | 7c8dc029-a019-4a8c-8831-9dd24b2be3a7 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-26T15:30:14 | b158ab2d-7c43-456e-b520-277a18332bc1 | PIX para compra de bicicleta |
      | 7f0b831a-6261-4a69-88d8-2e4ffd41d37b | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-25T22:58:42 | be8a9cbd-9687-40d4-b3bf-923ac3b81b06 | PIX para compra de aviao     |
      | c0d6403c-c559-43f3-8f13-2ee9d16941ef | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-26T21:15:00 | e403f6cd-6d25-4e56-ada2-cf02e0c86500 | PIX para compra de carro     |
      | d324c0d1-efb4-4e0d-bb38-89796af75772 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.00       | 2026-04-27T19:30:05 | b0f3bd68-437a-4061-8e23-d43739ec9a1b | PIX para compra de carreta   |
      | d8001537-3b78-45d6-84d9-5868a694beef | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 30000.50       | 2026-05-25T22:42:24 | 6daf24da-fd9d-4ff1-8cb5-2f05f7f42795 | PIX para compra de carro     |
    When eu faço uma requisição GET paginada page 1 e size 5 para listar as transações Pix
    And eu faço uma requisição GET para listar as transações Pix
    Then o status da responsa deve ser 200
    And o sistema deve retornar uma lista paginada page 1 e size 5 de transações Pix

  Scenario: Buscar lista por Ordenação
    Given que existem transações Pix cadastradas
      | codigoTrancacao                      | codigoPessoa                         | valorTrancacao | dataTrancacao       | codigoBeneficiario                   | mensagemTransacao            |
      | 28d07802-87ed-498a-86d7-e670b620c958 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2024-05-26T21:14:59 | e41b55c7-51f5-4035-9ecd-cfd3e7584146 | PIX para compra de carro     |
      | 5007c5c7-16b2-43f1-bd73-25083f659157 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2024-05-25T23:00:45 | c6a7224e-1c1b-4e82-9129-3739d12f7a43 | PIX para compra de moto      |
      | 52fa9f41-d1a8-472a-9754-8926c4e470bd | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | -30000.50      | 2024-05-25T22:58:24 | ba726966-155c-4c4b-ba7d-449de890c858 | PIX para compra de jetsky    |
      | 65a06c49-1ed8-46ff-805a-58802e8b0bf9 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2025-05-26T15:30:27 | 515e9c2a-74b2-45db-a3fd-b6dd025bd0a4 | PIX para compra de barco     |
      | 69e38f23-3d04-4a9d-b522-b2debab1f075 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 50000.50       | 2025-04-30T00:00:00 | 6444c4e8-9e0e-45cc-8c09-5cec0534c012 | PIX teste de atualizacao     |
      | 7c8dc029-a019-4a8c-8831-9dd24b2be3a7 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2025-05-26T15:30:14 | b158ab2d-7c43-456e-b520-277a18332bc1 | PIX para compra de bicicleta |
      | 7f0b831a-6261-4a69-88d8-2e4ffd41d37b | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-25T22:58:42 | be8a9cbd-9687-40d4-b3bf-923ac3b81b06 | PIX para compra de aviao     |
      | c0d6403c-c559-43f3-8f13-2ee9d16941ef | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-26T21:15:00 | e403f6cd-6d25-4e56-ada2-cf02e0c86500 | PIX para compra de carro     |
      | d324c0d1-efb4-4e0d-bb38-89796af75772 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.00       | 2026-04-27T19:30:05 | b0f3bd68-437a-4061-8e23-d43739ec9a1b | PIX para compra de carreta   |
      | d8001537-3b78-45d6-84d9-5868a694beef | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 30000.50       | 2026-05-25T22:42:24 | 6daf24da-fd9d-4ff1-8cb5-2f05f7f42795 | PIX para compra de carro     |
    When eu faço uma requisição GET paginada page 1 e size 5 para listar as transações Pix
    And eu faço uma requisição GET paginada ordenado por "dataTrancacao" de forma "decrescendo"
    And eu faço uma requisição GET para listar as transações Pix
    Then o status da responsa deve ser 200
    And o sistema deve confirmar uma lista ordenada de transações Pix

    When eu faço uma requisição GET paginada page 1 e size 5 para listar as transações Pix
    And eu faço uma requisição GET paginada ordenado por "dataTrancacao" de forma "crescente"
    And eu faço uma requisição GET para listar as transações Pix
    Then o status da responsa deve ser 200
    And o sistema deve confirmar uma lista ordenada de transações Pix

  Scenario: Buscar lista por Filtros
    Given que existem transações Pix cadastradas
      | codigoTrancacao                      | codigoPessoa                         | valorTrancacao | dataTrancacao       | codigoBeneficiario                   | mensagemTransacao            |
      | 28d07802-87ed-498a-86d7-e670b620c958 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2024-05-26T21:14:59 | e41b55c7-51f5-4035-9ecd-cfd3e7584146 | PIX para compra de carro     |
      | 5007c5c7-16b2-43f1-bd73-25083f659157 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2024-05-25T23:00:45 | c6a7224e-1c1b-4e82-9129-3739d12f7a43 | PIX para compra de moto      |
      | 52fa9f41-d1a8-472a-9754-8926c4e470bd | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | -30000.50      | 2024-05-25T22:58:24 | ba726966-155c-4c4b-ba7d-449de890c858 | PIX para compra de jetsky    |
      | 65a06c49-1ed8-46ff-805a-58802e8b0bf9 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2025-05-26T15:30:27 | 515e9c2a-74b2-45db-a3fd-b6dd025bd0a4 | PIX para compra de barco     |
      | 69e38f23-3d04-4a9d-b522-b2debab1f075 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 50000.50       | 2025-04-30T00:00:00 | 6444c4e8-9e0e-45cc-8c09-5cec0534c012 | PIX teste de atualizacao     |
      | 7c8dc029-a019-4a8c-8831-9dd24b2be3a7 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2025-05-26T15:30:14 | e41b55c7-51f5-4035-9ecd-cfd3e7584146 | PIX para compra de bicicleta |
      | 7f0b831a-6261-4a69-88d8-2e4ffd41d37b | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-25T22:58:42 | e41b55c7-51f5-4035-9ecd-cfd3e7584146 | PIX para compra de aviao     |
      | c0d6403c-c559-43f3-8f13-2ee9d16941ef | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.50       | 2026-05-26T21:15:00 | e41b55c7-51f5-4035-9ecd-cfd3e7584146 | PIX para compra de carro     |
      | d324c0d1-efb4-4e0d-bb38-89796af75772 | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 40000.00       | 2026-04-27T19:30:05 | b0f3bd68-437a-4061-8e23-d43739ec9a1b | PIX para compra de carreta   |
      | d8001537-3b78-45d6-84d9-5868a694beef | fbc5fbc7-9b55-4058-af41-fa94ae092ae8 | 30000.50       | 2026-05-25T22:42:24 | 6daf24da-fd9d-4ff1-8cb5-2f05f7f42795 | PIX para compra de carro     |
    When eu faço uma requisição GET paginada page 0 e size 10 para listar as transações Pix
    And eu faço uma filtragem por parametros para listar as transações Pix
      | codigoBeneficiario | e41b55c7-51f5-4035-9ecd-cfd3e7584146 |
    And eu faço uma requisição GET para listar as transações Pix
    Then o status da responsa deve ser 200
    And devo validar que retornou 4 registros de transações Pix


  Scenario: Buscar lista paginada sem resultados
    Given que não existem transações Pix cadastradas
    When eu faço uma requisição GET paginada page 0 e size 10 para listar as transações Pix
    And eu faço uma requisição GET para listar as transações Pix
    Then o status da responsa deve ser 200
    Then o sistema deve retornar uma lista vazia
