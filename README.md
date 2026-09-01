# API de Votação Cooperativa (Server-Driven UI)

API REST desenvolvida como solução para gerenciamento e participação em sessões de votação de pautas cooperativas via dispositivos móveis. O projeto utiliza o conceito de **Server-Driven UI (SDUI)**, onde o backend fornece os payloads JSON estruturados que orientam o aplicativo móvel na renderização dinâmica de telas do tipo `FORMULARIO` e `SELECAO`.

## 🚀 Funcionalidades Principais

- **Cadastro de Pautas:** Criação de novas pautas para discussão e votação.
- **Abertura de Sessão:** Abertura de sessões de votação com tempo limite configurável (padrão: 1 minuto).
- **Recebimento de Votos:** Votação simplificada (`SIM`/`NAO`) com garantia de voto único por associado/CPF por pauta.
- **Contabilização & Resultado:** Apuração atômica e em tempo real do resultado das pautas.

## 🏆 Diferenciais e Tarefas Bônus

- **Validação Externa de CPF (Bônus 1):** Integração com serviço externo via `WebClient` e resiliência com Resilience4j para verificar a elegibilidade do associado (`ABLE_TO_VOTE` / `UNABLE_TO_VOTE`).
- **Alta Performance (Bônus 2):** Processamento assíncrono de votos em massa com **RabbitMQ** e atualização de contadores de alto desempenho em cache via **Redis**, suportando centenas de milhares de votos com baixa latência.
- **Versionamento de API (Bônus 3):** Estratégia de versionamento por caminho de URI (`/v1/pautas`).

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3 (Web, Data JPA, Validation, AMQP, Redis)
- **Banco de Dados:** PostgreSQL (Produção/Docker) / H2 (Testes)
- **Mensageria & Cache:** RabbitMQ e Redis
- **Testes:** JUnit 5, Mockito e Testcontainers
- **Documentação:** OpenAPI / Swagger
