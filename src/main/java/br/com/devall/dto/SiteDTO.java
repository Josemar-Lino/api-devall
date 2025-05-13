package br.com.devall.dto;

import br.com.devall.model.Site;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para representar um site na resposta da API.
 * Contém informações básicas do site para exibição.
 */
@Data
public class SiteDTO {
    private Long id;
    private String name;
    private String url;
    private String feedUrl;
    private String description;

    /**
     * Converte uma entidade Site para SiteDTO.
     * 
     * @param site Entidade Site a ser convertida
     * @return SiteDTO com os dados do site
     */
    public static SiteDTO fromEntity(Site site) {
        SiteDTO dto = new SiteDTO();
        dto.setId(site.getId());
        dto.setName(site.getName());
        dto.setUrl(site.getUrl());
        dto.setFeedUrl(site.getFeedUrl());
        dto.setDescription(site.getDescription());
        return dto;
    }
} 