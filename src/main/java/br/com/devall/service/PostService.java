package br.com.devall.service;

import br.com.devall.dto.PostResponseDTO;
import br.com.devall.model.Click;
import br.com.devall.model.Post;
import br.com.devall.repository.ClickRepository;
import br.com.devall.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Serviço que implementa a lógica de negócio relacionada a posts.
 * Gerencia operações como busca de posts e registro de cliques.
 */
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ClickRepository clickRepository;

    /**
     * Busca posts com base em critérios de filtro.
     * A busca é feita por similaridade (like) nos campos título e resumo.
     * Os posts são sempre ordenados por data de publicação em ordem decrescente.
     * 
     * @param content Texto para buscar no título ou resumo
     * @param pageable Configuração de paginação
     * @return Página de posts convertidos para DTO
     */
    public Page<PostResponseDTO> findPosts(String content, Pageable pageable) {
        // Cria um novo Pageable com ordenação por data de publicação
        Pageable sortedPageable = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(Sort.Direction.DESC, "pubDate")
        );

        return postRepository.findByContent(content, sortedPageable)
                .map(PostResponseDTO::fromEntity);
    }

    /**
     * Registra um clique em um post e retorna sua URL.
     * 
     * @param postId ID do post que recebeu o clique
     * @return URL original do post
     * @throws ResponseStatusException se o post não for encontrado
     */
    @Transactional
    public String registerClick(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Post não encontrado com ID: " + postId
                ));

        Click click = new Click();
        click.setPost(post);
        clickRepository.save(click);

        return post.getUrl();
    }
} 