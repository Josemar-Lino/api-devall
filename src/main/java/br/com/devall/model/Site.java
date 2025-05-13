package br.com.devall.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidade que representa um site ou blog no sistema.
 * Contém informações como nome, URL e feed RSS.
 */
@Data
@Entity
@Table(name = "sites")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Nome do site/blog

    @Column(nullable = false)
    private String url; // URL principal do site

    @Column(name = "feed_url")
    private String feedUrl; // URL do feed RSS do site

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Descrição do site

    @Column(name = "iframe")
    private Boolean iframe = false;
} 