package br.com.devall.controller;

import br.com.devall.dto.PageResponseDTO;
import br.com.devall.dto.PostResponseDTO;
import br.com.devall.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller que expõe os endpoints relacionados a posts.
 * Implementa as operações de busca e registro de cliques.
 */
@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * Endpoint para buscar posts com filtros.
     * A busca é feita por similaridade (like) nos campos título e resumo.
     * 
     * @param content Texto para buscar no título ou resumo (opcional)
     * @param page Número da página (padrão: 1)
     * @return Página de posts que correspondem aos critérios
     */
    @GetMapping("/post")
    public ResponseEntity<PageResponseDTO<PostResponseDTO>> getPosts(
            @RequestParam(required = false) String content,
            @RequestParam(defaultValue = "1") int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<PostResponseDTO> posts = postService.findPosts(content, pageRequest);
        return ResponseEntity.ok(PageResponseDTO.fromPage(posts));
    }

    /**
     * Endpoint para registrar um clique em um post.
     * Registra o clique e retorna a URL do post para redirecionamento.
     * 
     * @param id ID do post que recebeu o clique
     * @return URL do post para redirecionamento
     */
    @GetMapping("/post/clique/{id}")
    public ResponseEntity<Map<String, String>> registerClick(@PathVariable Long id) {
        String url = postService.registerClick(id);
        return ResponseEntity.ok(Map.of("url", url));
    }
} 