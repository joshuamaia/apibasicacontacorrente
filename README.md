# API desafio Conta Corrente

## 1. Criar Conta

Para acessar, urlProject/rest/conta/criar

Colocar no header Content-Type = application/json .

Exemplo do corpo do envio abaixo:

{"pessoa" : {"id":1},
    "saldo":5000.45,
    "limiteSaqueDiario":2000,
    "flagAtivo":true,
    "tipoConta":12}

## 2. Depósito

Para acessar, urlProject/rest/conta/deposito

Colocar no header Content-Type = application/json .

Exemplo do corpo do envio abaixo:

{"conta" : {"id":1},
    "valor":234.45}
	
## 3. Consultar saldo

Para acessar, urlProject/rest/conta/saldo/idConta

Exemplo urlProject/rest/conta/saldo/1

## 4. Saque

Para acessar, urlProject/rest/conta/saque

Colocar no header Content-Type = application/json .

Exemplo do corpo do envio abaixo:

{"conta" : {"id":1},
    "valor":345.45}
	
## 5. Bloquear conta

Para acessar, urlProject/rest/conta/bloqueio/idConta

Exemplo urlProject/rest/conta/bloqueio/2

## 6. Extrato conta

Para acessar, urlProject/rest/conta/extrato/idConta

Exemplo urlProject/rest/conta/extrato/2

## 7. Extrato conta por Data início e Data Fim

Para acessar, urlProject/rest/conta/extrato/idConta/data_inicio/data_fim

### A data deve ser no formato dd:MM:yyyy_HH:mm:ss

Exemplo urlProject/rest/conta/extrato/2/10:05:2018_20:34:00/10:05:2018_20:35:00
