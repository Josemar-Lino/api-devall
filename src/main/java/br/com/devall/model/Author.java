package br.com.devall.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidade que representa um autor no sistema.
 * Contém informações como nome, mini biografia e avatar.
 */
@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Nome do autor

    @Column(name = "mini_biografia", columnDefinition = "TEXT")
    private String miniBiografia; // Mini biografia do autor

    private String avatar; // URL do avatar do autor
} 