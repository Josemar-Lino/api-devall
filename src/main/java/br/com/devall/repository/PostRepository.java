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
     * Busca posts com base em conteúdo e site.
     * 
     * @param content Texto para buscar no título ou resumo (opcional)
     * @param siteId ID do site para filtrar (opcional)
     * @param pageable Configuração de paginação
     * @return Página de posts que correspondem aos critérios
     */
    @Query("SELECT p FROM Post p WHERE " +
           "(:content IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :content, '%')) OR " +
           "LOWER(p.summary) LIKE LOWER(CONCAT('%', :content, '%'))) " +
           "AND (:siteId IS NULL OR p.site.id = :siteId) " +
           "ORDER BY p.pubDate DESC")
    Page<Post> findByContentAndSite(@Param("content") String content, 
                                  @Param("siteId") Long siteId, 
                                  Pageable pageable);
} 