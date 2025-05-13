package br.com.devall.dto;

import br.com.devall.model.Site;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO para representar um site na resposta da API.
 * Contém apenas os campos necessários para exibição.
 */
@Data
@JsonIgnoreProperties({"url"})
public class SiteDTO {
    private Long id;
    private String name;
    private String feedUrl;
    private String description;

    /**
     * Converte uma entidade Site para SiteDTO.
     * 
     * @param site Entidade Site
     * @return SiteDTO com os dados do site
     */
    public static SiteDTO fromEntity(Site site) {
        if (site == null) {
            return null;
        }
        SiteDTO dto = new SiteDTO();
        dto.setId(site.getId());
        dto.setName(site.getName());
        dto.setFeedUrl(site.getFeedUrl());
        dto.setDescription(site.getDescription());
        return dto;
    }
} 