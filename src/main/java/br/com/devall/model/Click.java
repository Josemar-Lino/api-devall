package br.com.devall.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entidade que representa um clique em um post.
 * Registra quando um usuário clicou em um post específico.
 */
@Data
@Entity
@Table(name = "clicks")
public class Click {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Post que recebeu o clique

    @Column(name = "click_date", nullable = false)
    private LocalDateTime clickDate = LocalDateTime.now(); // Data e hora do clique
} 