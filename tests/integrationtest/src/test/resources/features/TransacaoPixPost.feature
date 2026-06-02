Feature: : Criar um registro de PIX

Scenario: Criar transação Pix com sucesso
  Given que possuo os dados válidos de uma transação Pix
  """
  {
    "codigoPessoa": "fbc5fbc7-9b55-4058-af41-fa94ae092ae8",
    "valorTrancacao": 30000.5,
    "dataTrancacao": "2026-05-25T20:31:28.538Z",
    "codigoBeneficiario": "c4a954e4-5d08-459c-b5b4-5ce9aa251140",
    "mensagemTransacao": "PIX para compra de carro"
  }
  """
  When eu envio uma requisição para criar a transação Pix
  Then o sistema deve retornar 200 após o post da transação Pix
  Then o sistema deve retornar o id da transação Pix criado com exito

Scenario: Criar transação Pix com dados inválidos
  Given que possuo dados nulos como codigoPessoa e valorTransacao para uma transação Pix
  """
  {
    "dataTrancacao": "2026-05-25T23:02:00.940Z",
    "codigoBeneficiario": "1b2616dc-2fef-4677-9a9a-421244ee070f",
    "mensagemTransacao": "PIX para compra de carro"
  }
  """
  When eu envio uma requisição para criar a transação Pix
  Then o sistema deve retornar 400 após o post da transação Pix
  Then o sistema deve retornar um erro informando que os dados são inválidos
