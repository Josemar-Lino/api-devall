package br.com.devall.repository;

import br.com.devall.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repositório para operações de banco de dados relacionadas a posts.
 * Fornece métodos para buscar posts com filtros e paginação.
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * Busca posts com base em conteúdo.
     * A busca é feita por similaridade (like) nos campos título e resumo.
     * Os posts são ordenados por data de publicação em ordem decrescente.
     * 
     * @param content Texto para buscar no título ou resumo
     * @param pageable Configuração de paginação
     * @return Página de posts que correspondem aos critérios
     */
    @Query("SELECT p FROM Post p WHERE " +
           "(:content IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :content, '%')) OR " +
           "LOWER(p.summary) LIKE LOWER(CONCAT('%', :content, '%'))) " +
           "ORDER BY p.pubDate DESC")
    Page<Post> findByContent(@Param("content") String content, Pageable pageable);
} 