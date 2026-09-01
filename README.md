# Coop Votação API

API REST para gerenciamento de pautas, sessões de votação e apuração. Implementada com foco em simplicidade e execução local: Java 21, Spring Boot e banco em memória H2.

## Tecnologias
- Java 21
- Spring Boot (Web, Data JPA, Validation)
- H2 Database (in-memory) - configurado em src/main/resources/application.yml
- Lombok (anotações como @Getter, @Builder, @NoArgsConstructor, @AllArgsConstructor)
- Maven

## Arquitetura
Projeto organizado em camadas:
- controller: adaptadores HTTP
- service: lógica de negócio
- repository: integração com JPA/H2
- domain/dto: entidades e objetos de transferência

As classes de domínio, DTOs e entidades usam Lombok para gerar builders e getters (ex.: .builder(), getTitulo()). Certifique-se de que as anotações Lombok estejam presentes quando modificar modelos.

## Configuração
O arquivo de configuração deve estar em:

```
src/main/resources/application.yml
```

Isso garante carregamento correto de contextos de execução e teste.

## Como executar
1. Construir:

```
mvn -DskipTests package
```

2. Rodar:

```
mvn -DskipTests spring-boot:run
```

3. Testes:

```
mvn test
```

## H2 Console
Quando a aplicação estiver rodando, o console H2 (se ativado em application.yml) normalmente fica em:

```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
```

## Endpoints principais (exemplos)
- GET /pautas - lista pautas
- POST /pautas - cria pauta
- POST /pautas/{id}/sessoes - abre sessão de votação
- POST /pautas/{id}/votos - registra voto

Exemplo curl (criar pauta):

```
curl -X POST -H "Content-Type: application/json" \
  -d '{"titulo":"Assembleia"}' http://localhost:8080/pautas
```

## Notas
- Removidas referências a tecnologias não utilizadas (ex.: RabbitMQ, Redis).
- Mantém-se H2 para facilitar execução local e em CI; para produção, configure um banco persistente e variáveis de ambiente apropriadas.

Contribuições são bem-vindas — abra PRs com mudanças pequenas e testes.
