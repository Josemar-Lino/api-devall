package br.com.devall.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * DTO para representar uma resposta paginada da API.
 */
@Data
public class PageResponseDTO<T> {
    private List<T> content;
    private PageableDTO pageable;
    private int totalPages;
    private long totalElements;
    private boolean last;
    private boolean first;
    private int numberOfElements;
    private int size;
    private int number;
    private SortDTO sort;
    private boolean empty;

    @Data
    public static class PageableDTO {
        private int pageNumber;
        private int pageSize;
        private SortDTO sort;
        private long offset;
        private boolean paged;
        private boolean unpaged;
    }

    @Data
    public static class SortDTO {
        private boolean sorted;
        private boolean unsorted;
        private boolean empty;
    }

    /**
     * Converte uma Page do Spring para PageResponseDTO.
     * 
     * @param page Página do Spring a ser convertida
     * @return PageResponseDTO com os dados da página
     */
    public static <T> PageResponseDTO<T> fromPage(Page<T> page) {
        PageResponseDTO<T> response = new PageResponseDTO<>();
        response.setContent(page.getContent());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setLast(page.isLast());
        response.setFirst(page.isFirst());
        response.setNumberOfElements(page.getNumberOfElements());
        response.setSize(page.getSize());
        response.setNumber(page.getNumber());
        response.setEmpty(page.isEmpty());

        // Configuração do pageable
        PageableDTO pageable = new PageableDTO();
        pageable.setPageNumber(page.getPageable().getPageNumber());
        pageable.setPageSize(page.getPageable().getPageSize());
        pageable.setOffset(page.getPageable().getOffset());
        pageable.setPaged(page.getPageable().isPaged());
        pageable.setUnpaged(!page.getPageable().isPaged());
        response.setPageable(pageable);

        // Configuração do sort
        SortDTO sort = new SortDTO();
        sort.setSorted(page.getSort().isSorted());
        sort.setUnsorted(!page.getSort().isSorted());
        sort.setEmpty(page.getSort().isEmpty());
        response.setSort(sort);
        pageable.setSort(sort);

        return response;
    }
} 