-- db.transacao_pix definição

CREATE TABLE `transacao_pix` (
  `codigo_trancacao` varchar(36) NOT NULL,
  `codigo_pessoa` varchar(36) NOT NULL,
  `valor_trancacao` decimal(10,2) NOT NULL,
  `data_trancacao` datetime DEFAULT NULL,
  `codigo_beneficiario` varchar(36) DEFAULT NULL,
  `mensagem_transacao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo_trancacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;