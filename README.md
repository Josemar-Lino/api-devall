# DevAll API

API REST para o agregador de fontes de informação sobre desenvolvimento de software.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- MySQL 8.0
- Docker e Docker Compose
- Maven
- Lombok
- OpenAPI/Swagger

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas:

- **Controller**: Expõe os endpoints da API
- **Service**: Implementa a lógica de negócio
- **Repository**: Gerencia o acesso ao banco de dados
- **Model**: Define as entidades do sistema
- **DTO**: Define os objetos de transferência de dados

## Decisões de Design

1. **Paginação**: Implementada para melhor performance na listagem de posts
2. **DTOs**: Utilizados para controlar quais dados são expostos na API
3. **Transações**: Utilizadas para garantir a consistência dos dados
4. **OpenAPI**: Documentação automática da API
5. **Docker**: Facilita o ambiente de desenvolvimento

## Como Executar

### Pré-requisitos

- Docker
- Docker Compose

### Executando com Docker

1. Clone o repositório
2. Na raiz do projeto, execute:
   ```bash
   docker-compose up -d
   ```

Isso irá:
- Construir a imagem da aplicação
- Iniciar o container do MySQL
- Iniciar o container da aplicação
- Configurar a rede entre os containers
- Criar o banco de dados e inserir os dados iniciais

A API estará disponível em `http://localhost:8080`

### Documentação da API

A documentação Swagger está disponível em:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

## Endpoints Principais

### GET /api/v2/post
Retorna uma lista paginada de posts com busca textual no título ou resumo.

Parâmetros:
- `content`: Texto para busca (opcional)
- `page`: Número da página (padrão: 1)

### GET /api/v2/post/{id}/click
Registra um clique no post e retorna a URL original.

## Dificuldades Encontradas

1. **Configuração do Docker**: Ajustes necessários para garantir a persistência dos dados
2. **Mapeamento JPA**: Cuidado especial com as relações entre entidades
3. **Paginação**: Implementação da busca com ordenação por data

## Comandos Docker Úteis


- Parar os containers:
  ```bash
  docker-compose down
  ```

- Ver logs da aplicação:
  ```bash
  docker-compose logs -f api
  ```

- Ver logs do banco de dados:
  ```bash
  docker-compose logs -f mysql
  ```

- Reconstruir e reiniciar os containers:
  ```bash
  docker-compose up -d --build
  ``` 