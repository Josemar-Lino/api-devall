package br.com.devall.dto;

import br.com.devall.model.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para representar um post na resposta da API.
 * Contém apenas os campos necessários para exibição, sem informações sensíveis.
 */
@Data
@JsonIgnoreProperties({"url"})
public class PostResponseDTO {
    private Long id;
    private String title;
    private String summary;
    private SiteDTO site;
    private LocalDateTime indexDate;
    private LocalDateTime pubDate;
    private AuthorDTO author;

    /**
     * Converte uma entidade Post para PostResponseDTO.
     * 
     * @param post Entidade Post a ser convertida
     * @return PostResponseDTO com os dados do post
     */
    public static PostResponseDTO fromEntity(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setSummary(post.getSummary());
        dto.setSite(SiteDTO.fromEntity(post.getSite()));
        dto.setIndexDate(post.getIndexDate());
        dto.setPubDate(post.getPubDate());
        dto.setAuthor(AuthorDTO.fromEntity(post.getAuthor()));
        return dto;
    }
} 