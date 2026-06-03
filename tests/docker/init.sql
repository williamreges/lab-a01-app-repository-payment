-- `payment-db`.transacao_pix definition

CREATE TABLE `transacao_pix`
(
    `codigo_trancacao`    binary(16) NOT NULL,
    `codigo_pessoa`       varchar(255)   NOT NULL,
    `valor_trancacao`     decimal(38, 2) NOT NULL,
    `data_trancacao`      datetime     DEFAULT NULL,
    `codigo_beneficiario` varchar(255)   NOT NULL,
    `mensagem_transacao`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`codigo_trancacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO transacao_pix (
    codigo_trancacao,
    codigo_pessoa,
    valor_trancacao,
    data_trancacao,
    codigo_beneficiario,
    mensagem_transacao
) VALUES
      (
          UUID_TO_BIN('28d07802-87ed-498a-86d7-e670b620c958'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          40000.50,
          '2024-05-26 21:14:59',
          'e41b55c7-51f5-4035-9ecd-cfd3e7584146',
          'PIX para compra de carro'
      ),
      (
          UUID_TO_BIN('5007c5c7-16b2-43f1-bd73-25083f659157'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          40000.50,
          '2024-05-25 23:00:45',
          'c6a7224e-1c1b-4e82-9129-3739d12f7a43',
          'PIX para compra de moto'
      ),
      (
          UUID_TO_BIN('52fa9f41-d1a8-472a-9754-8926c4e470bd'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          -30000.50,
          '2024-05-25 22:58:24',
          'ba726966-155c-4c4b-ba7d-449de890c858',
          'PIX para compra de jetsky'
      ),
      (
          UUID_TO_BIN('65a06c49-1ed8-46ff-805a-58802e8b0bf9'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          40000.50,
          '2025-05-26 15:30:27',
          '515e9c2a-74b2-45db-a3fd-b6dd025bd0a4',
          'PIX para compra de barco'
      ),
      (
          UUID_TO_BIN('69e38f23-3d04-4a9d-b522-b2debab1f075'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          50000.50,
          '2025-04-30 00:00:00',
          '6444c4e8-9e0e-45cc-8c09-5cec0534c012',
          'PIX teste de atualizacao'
      ),
      (
          UUID_TO_BIN('7c8dc029-a019-4a8c-8831-9dd24b2be3a7'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          40000.50,
          '2025-05-26 15:30:14',
          'e41b55c7-51f5-4035-9ecd-cfd3e7584146',
          'PIX para compra de bicicleta'
      ),
      (
          UUID_TO_BIN('7f0b831a-6261-4a69-88d8-2e4ffd41d37b'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          40000.50,
          '2026-05-25 22:58:42',
          'e41b55c7-51f5-4035-9ecd-cfd3e7584146',
          'PIX para compra de aviao'
      ),
      (
          UUID_TO_BIN('c0d6403c-c559-43f3-8f13-2ee9d16941ef'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          40000.50,
          '2026-05-26 21:15:00',
          'e41b55c7-51f5-4035-9ecd-cfd3e7584146',
          'PIX para compra de carro'
      ),
      (
          UUID_TO_BIN('d324c0d1-efb4-4e0d-bb38-89796af75772'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          40000.00,
          '2026-04-27 19:30:05',
          'b0f3bd68-437a-4061-8e23-d43739ec9a1b',
          'PIX para compra de carreta'
      ),
      (
          UUID_TO_BIN('d8001537-3b78-45d6-84d9-5868a694beef'),
          'fbc5fbc7-9b55-4058-af41-fa94ae092ae8',
          30000.50,
          '2026-05-25 22:42:24',
          '6daf24da-fd9d-4ff1-8cb5-2f05f7f42795',
          'PIX para compra de carro'
      );