package com.example.demo_emazon.article.application.handler;

import com.example.demo_emazon.article.application.dto.ArticleRequest;
import com.example.demo_emazon.article.application.dto.ArticleResponse;
import com.example.demo_emazon.article.application.mapper.IArticleMapper;
import com.example.demo_emazon.article.domain.api.IArticleServicePort;
import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.util.Constants;
import com.example.demo_emazon.util.TestData.TestDataArticle;
import com.example.demo_emazon.util.TestData.TestDataBrand;
import com.example.demo_emazon.util.TestData.TestDataCategory;
import com.example.demo_emazon.util.pagination.Pagination;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleHandlerTest {

    @Mock
    private IArticleMapper articleMapper;

    @Mock
    private IArticleServicePort articleServicePort;

    @InjectMocks
    private ArticleHandler articleHandler;

    @Test
    void createArticle() {

        ArticleRequest articleRequest = TestDataArticle.getArticleRequest();
        Brand brand = TestDataBrand.getBrand();
        List<Category> categories = TestDataCategory.getListCategories();
        Article article = TestDataArticle.getArticle();

        when(articleServicePort.findByNameBrand(articleRequest.getBrandName())).thenReturn(brand);
        when(articleServicePort.findByNamesCategory(articleRequest.getCategoryNames())).thenReturn(categories);
        when(articleMapper.toArticle(any(ArticleRequest.class))).thenReturn(article);
        when(articleServicePort.createArticle(any(Article.class))).thenReturn(article);
        when(articleMapper.toResponse(any(Article.class))).thenReturn(new ArticleResponse());

        // Llamada al metodo a probar
        ArticleResponse response = articleHandler.createArticle(articleRequest);

        // Verificaciones
        assertNotNull(response);  // Verificar que la respuesta no sea nula

        // Verificar que los metodos se llamaron correctamente
        verify(articleServicePort, times(1)).findByNameBrand(articleRequest.getBrandName());
        verify(articleServicePort, times(1)).findByNamesCategory(articleRequest.getCategoryNames());
        verify(articleMapper, times(1)).toArticle(articleRequest);
        verify(articleServicePort, times(1)).createArticle(article);
        verify(articleMapper, times(1)).toResponse(article);

    }

    @Test
    void testListArticles() {
        // Datos de entrada para el test
        int number = Constants.PAGE_IN_RANGE;
        int size = Constants.PAGE_SIZE_2;
        String sortDirection = Constants.SORT_ASC;
        String model = Constants.MODEL_ARTICLE;

        // Datos simulados del servicio
        Pagination<Article> articlePage = new Pagination<>(List.of(TestDataArticle.getArticle()), number, size, 1);

        // Datos simulados que el mapper debería devolver
        Pagination<ArticleResponse> expectedPagination = new Pagination<>(List.of(TestDataArticle.getArticleResponse()), number, size, 1);

        // Simulación del comportamiento del servicio
        when(articleServicePort.listArticles(number, size, sortDirection, model)).thenReturn(articlePage);

        // Simulación del comportamiento del mapper
        when(articleMapper.toResposePagination(articlePage)).thenReturn(expectedPagination);

        // Llamada al metodo
        Pagination<ArticleResponse> result = articleHandler.listArticles(number, size, sortDirection, model);

        // Verificaciones
        assertNotNull(result); // Verifica que el resultado no sea nulo.
        assertEquals(expectedPagination, result); // Verifica que el resultado sea igual al esperado.

        // Verifica que se llamaron los mocks correctamente
        verify(articleServicePort).listArticles(number, size, sortDirection, model);
        verify(articleMapper).toResposePagination(articlePage);
    }
}