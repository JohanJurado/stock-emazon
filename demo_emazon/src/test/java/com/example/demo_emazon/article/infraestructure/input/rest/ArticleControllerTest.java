package com.example.demo_emazon.article.infraestructure.input.rest;

import com.example.demo_emazon.article.application.dto.ArticleRequest;
import com.example.demo_emazon.article.application.dto.ArticleResponse;
import com.example.demo_emazon.article.application.handler.IArticleHandler;
import com.example.demo_emazon.util.TestData.TestDataArticle;
import com.example.demo_emazon.util.pagination.Pagination;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArticleHandler articleHandler; // Mockeamos la dependencia del controlador

    @Autowired
    private ObjectMapper objectMapper;

    private final ArticleRequest articleRequest = TestDataArticle.getArticleRequest();
    private final ArticleResponse articleResponse = TestDataArticle.getArticleResponse();

    @Test
    void testCreateArticle() throws Exception {

        // Mockeamos el comportamiento del handler
        Mockito.when(articleHandler.createArticle(any(ArticleRequest.class))).thenReturn(articleResponse);

        // Simulamos la petición POST
        mockMvc.perform(post("/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleRequest))) // Convertimos el request en JSON
                .andExpect(status().isCreated()) // Verificamos que el status sea 201 CREATED
                .andExpect(jsonPath("$.idArticle").value(TestDataArticle.getArticleResponse().getIdArticle())) // Verificamos que la respuesta contiene el ID correcto
                .andExpect(jsonPath("$.nameArticle").value(TestDataArticle.getArticleResponse().getNameArticle()))
                .andExpect(jsonPath("$.descriptionArticle").value(TestDataArticle.getArticleResponse().getDescriptionArticle()));
    }

    @Test
    void testListArticles() throws Exception {
        // Simulamos una lista paginada de artículos
        Pagination<ArticleResponse> paginationResponse = new Pagination<>(
                List.of(articleResponse), 1, 10, 1);

        // Mockeamos el comportamiento del handler para la paginación
        Mockito.when(articleHandler.listArticles(anyInt(), anyInt(), any(String.class), any(String.class)))
                .thenReturn(paginationResponse);

        // Simulamos la petición GET
        mockMvc.perform(get("/article")
                        .param("page", "1")
                        .param("size", "10")
                        .param("sortDirection", "ASC")
                        .param("model", "Article"))
                .andExpect(status().isOk()) // Verificamos que el status sea 200 OK
                .andExpect(jsonPath("$.content[0].idArticle").value(TestDataArticle.getArticleResponse().getIdArticle())) // Verificamos que el primer artículo tiene el ID correcto
                .andExpect(jsonPath("$.content[0].nameArticle").value(TestDataArticle.getArticleResponse().getNameArticle()))
                .andExpect(jsonPath("$.content[0].descriptionArticle").value(TestDataArticle.getArticleResponse().getDescriptionArticle()))
                .andExpect(jsonPath("$.pageNumber").value(1))
                .andExpect(jsonPath("$.pageSize").value(10))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1L));
    }
}