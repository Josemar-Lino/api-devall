package br.com.devall.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entidade que representa um post no sistema.
 * Contém informações como título, resumo, URL e relacionamentos com Site e Author.
 */
@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // Título do post

    @Column(columnDefinition = "TEXT")
    private String summary; // Resumo do conteúdo do post

    @Column(nullable = false)
    private String url; // URL original do post

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site; // Site de origem do post

    @Column(name = "index_date")
    private LocalDateTime indexDate; // Data em que o post foi indexado no sistema

    @Column(name = "pub_date")
    private LocalDateTime pubDate; // Data de publicação original do post

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author; // Autor do post
} 