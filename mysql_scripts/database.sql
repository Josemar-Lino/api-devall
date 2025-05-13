-- Inclua aqui seu script SQL para criar o banco de dados
-- Crie também alguns registros pra ajudar a nossa revisão? :)

-- O que costumo fazer: crio o banco de dados, depois gero um dump dele 
-- no padrão sql e coloco aqui. Assim fica mais fácil já ter um script
-- com todos os registros.

-- Qualquer coisa, só me chamar, ok? 
-- Meu e-mail é kico@itexto.com.br !

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS devall;
USE devall;

-- Criação das tabelas
CREATE TABLE IF NOT EXISTS sites (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    feed_url VARCHAR(255),
    description TEXT
);

CREATE TABLE IF NOT EXISTS authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    mini_biografia TEXT,
    avatar VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS posts (
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

CREATE TABLE IF NOT EXISTS clicks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    click_date DATETIME NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id)
);

-- Inserção de dados de teste
INSERT INTO sites (name, url, feed_url, description) VALUES
('Dev.to', 'https://dev.to', 'https://dev.to/feed', 'Plataforma para desenvolvedores compartilharem artigos'),
('Medium', 'https://medium.com', 'https://medium.com/feed', 'Plataforma de blogs e artigos');

INSERT INTO authors (name, mini_biografia, avatar) VALUES
('João Silva', 'Desenvolvedor Java com 10 anos de experiência', 'https://example.com/avatar1.jpg'),
('Maria Santos', 'Especialista em Spring Boot e Microserviços', 'https://example.com/avatar2.jpg');

INSERT INTO posts (title, summary, url, site_id, index_date, pub_date, author_id) VALUES
('Introdução ao Spring Boot', 'Aprenda os conceitos básicos do Spring Boot', 'https://dev.to/spring-boot-intro', 1, NOW(), NOW(), 1),
('Microserviços com Java', 'Como construir microserviços escaláveis', 'https://medium.com/microservices-java', 2, NOW(), NOW(), 2),
('JPA e Hibernate', 'Guia completo de JPA e Hibernate', 'https://dev.to/jpa-guide', 1, NOW(), NOW(), 1);