package br.com.devall.dto;

import br.com.devall.model.Author;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para representar um autor na resposta da API.
 * Contém informações básicas do autor para exibição.
 */
@Data
public class AuthorDTO {
    private Long id;
    private String name;
    private String miniBiografia;
    private String avatar;

    /**
     * Converte uma entidade Author para AuthorDTO.
     * 
     * @param author Entidade Author a ser convertida
     * @return AuthorDTO com os dados do autor
     */
    public static AuthorDTO fromEntity(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setMiniBiografia(author.getMiniBiografia());
        dto.setAvatar(author.getAvatar());
        return dto;
    }
} 