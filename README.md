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

## Modelagem do Banco de Dados

O sistema utiliza um banco de dados MySQL com as seguintes tabelas:

### Sites
Armazena informações sobre os sites/blogs agregados:
```sql
CREATE TABLE sites (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    feed_url VARCHAR(255),
    description TEXT
);
```

### Authors
Armazena informações sobre os autores dos posts:
```sql
CREATE TABLE authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    mini_biografia TEXT,
    avatar VARCHAR(255)
);
```

### Posts
Armazena os posts agregados dos sites:
```sql
CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    summary TEXT,
    url VARCHAR(255) NOT NULL,
    site_id BIGINT,
    index_date DATETIME,
    pub_date DATETIME,
    author_id BIGINT,
    FOREIGN KEY (site_id) REFERENCES sites(id),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);
```

### Clicks
Registra os cliques realizados nos posts:
```sql
CREATE TABLE clicks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    click_date DATETIME NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id)
);
```

### Relacionamentos
- Um **Site** pode ter vários **Posts**
- Um **Author** pode ter vários **Posts**
- Um **Post** pode ter vários **Clicks**
- A URL do post só é exposta quando um clique é registrado

### Observações Importantes
1. A URL do post (`posts.url`) não é exposta na listagem de posts
2. A URL só é retornada quando um clique é registrado via endpoint `/api/v2/post/clique/{id}`
3. Cada clique é registrado com data/hora para análise posterior
4. As chaves estrangeiras garantem a integridade referencial dos dados

## Decisões de Design

1. **Paginação**: Implementada para melhor performance na listagem de posts
2. **OpenAPI**: Documentação automática da API
3. **Docker**: Facilita o ambiente de desenvolvimento

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

Exemplo de resposta:
```json
{
  "content": [
    {
      "id": 4,
      "title": "Aprenda Spring Boot",
      "summary": "Aprenda Spring Boot no youtube",
      "site": {
        "id": 4,
        "name": "Google",
        "feedUrl": "https://www.youtube.com/watch?v=YY_hf0FOIcU&t=210s&ab_channel=FernandaKipper%7CDev",
        "description": "Youtube"
      },
      "indexDate": "2025-05-13T11:50:57",
      "pubDate": "2025-05-13T11:50:57",
      "author": {
        "id": 4,
        "name": "Josêmar"
      }
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 4,
  "last": true,
  "first": true,
  "numberOfElements": 4,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "empty": false
}
```

### GET /api/v2/post/{id}/click
Registra um clique no post e retorna a URL original.

## Dificuldades Encontradas

1. **Configuração do Docker**: Ajustes necessários para garantir a persistência dos dados
2. **Mapeamento JPA**: Cuidado especial com as relações entre entidades
3. **Paginação**: Implementação da busca com ordenação por data