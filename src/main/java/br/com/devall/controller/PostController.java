package br.com.devall.controller;

import br.com.devall.dto.PostResponseDTO;
import br.com.devall.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

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
     * 
     * @param content Texto para buscar no título ou resumo (opcional)
     * @param site ID do site para filtrar (opcional)
     * @param page Número da página (padrão: 1)
     * @return Lista de posts que correspondem aos critérios
     */
    @GetMapping("/post")
    public ResponseEntity<List<PostResponseDTO>> getPosts(
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Long site,
            @RequestParam(defaultValue = "1") int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        return ResponseEntity.ok(postService.findPosts(content, site, pageRequest));
    }

    /**
     * Endpoint para registrar um clique em um post.
     * Redireciona o usuário para a URL original do post.
     * 
     * @param id ID do post que recebeu o clique
     * @return Redirecionamento para a URL do post
     */
    @GetMapping("/post/{id}/click")
    public RedirectView registerClick(@PathVariable Long id) {
        String url = postService.registerClick(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        redirectView.setStatusCode(HttpStatus.FOUND);
        return redirectView;
    }
} 