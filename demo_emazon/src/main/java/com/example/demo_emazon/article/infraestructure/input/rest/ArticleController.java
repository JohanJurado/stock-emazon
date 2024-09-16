package com.example.demo_emazon.article.infraestructure.input.rest;

import com.example.demo_emazon.article.application.dto.ArticleRequest;
import com.example.demo_emazon.article.application.dto.ArticleResponse;
import com.example.demo_emazon.article.application.handler.IArticleHandler;
import com.example.demo_emazon.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final IArticleHandler articleHandler;

    @Operation(summary = "Add a new Article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "1 Article added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid arguments supplied",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(@RequestBody ArticleRequest articleRequest){
        ArticleResponse articleResponse = articleHandler.createArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleResponse);
    }

    @Operation(summary = "Get the Articles with their pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Articles with pagination",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
    })
    @GetMapping
    public ResponseEntity<Pagination<ArticleResponse>> listArticles(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortDirection,
            @RequestParam String model
            ){

        Pagination<ArticleResponse> articleResponsePagination = articleHandler.listArticles(page, size, sortDirection, model);
        return ResponseEntity.ok(articleResponsePagination);
    }

}
