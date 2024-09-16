package com.example.demo_emazon.article.infraestructure.out.jpa.adapter;

import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.article.infraestructure.out.jpa.entity.ArticleEntity;
import com.example.demo_emazon.article.infraestructure.out.jpa.mapper.IArticleEntityMapper;
import com.example.demo_emazon.article.infraestructure.out.jpa.repository.IArticleJpaRepository;
import com.example.demo_emazon.util.TestData.TestDataArticle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleJpaAdapterTest {

    @Mock
    private IArticleJpaRepository articleJpaRepository;

    @Mock
    private IArticleEntityMapper articleEntityMapper;

    @InjectMocks
    private ArticleJpaAdapter articleJpaAdapter;

    @Test
    void testSave() {
        // Datos de entrada
        Article inputArticle = TestDataArticle.getArticle();
        ArticleEntity articleEntity = TestDataArticle.getArticleEntity();
        Article savedArticle = TestDataArticle.getArticle();

        // Simula la conversión del artículo a entidad
        when(articleEntityMapper.toArticleEntity(inputArticle)).thenReturn(articleEntity);

        // Simula el guardado en el repositorio
        when(articleJpaRepository.save(articleEntity)).thenReturn(articleEntity);

        // Simula la conversión de la entidad guardada de vuelta al artículo
        when(articleEntityMapper.toArticle(articleEntity)).thenReturn(savedArticle);

        // Llamada al metodo a probar
        Article result = articleJpaAdapter.save(inputArticle);

        // Verificaciones
        assertNotNull(result); // Verifica que el resultado no sea nulo.
        assertEquals(savedArticle, result); // Verifica que el resultado sea igual al artículo guardado.

        // Verificaciones de mocks
        verify(articleEntityMapper).toArticleEntity(inputArticle);
        verify(articleJpaRepository).save(articleEntity);
        verify(articleEntityMapper).toArticle(articleEntity);
    }

    @Test
    void testFindAll() {
        // Datos simulados
        ArticleEntity entity1 = TestDataArticle.getArticleEntity();
        ArticleEntity entity2 = TestDataArticle.getArticleEntity();
        List<ArticleEntity> entities = List.of(entity1, entity2);

        Article article1 = TestDataArticle.getArticle();
        Article article2 = TestDataArticle.getArticle();
        List<Article> expectedArticles = List.of(article1, article2);

        // Simula la respuesta del repositorio
        when(articleJpaRepository.findAll()).thenReturn(entities);

        // Simula la conversión de las entidades a artículos
        when(articleEntityMapper.toArticle(entity1)).thenReturn(article1);
        when(articleEntityMapper.toArticle(entity2)).thenReturn(article2);

        // Llamada al metodo a probar
        List<Article> result = articleJpaAdapter.findAll();

        // Verificaciones
        assertNotNull(result);
        assertEquals(expectedArticles.size(), result.size());
        assertEquals(expectedArticles, result);

        // Verificaciones de mocks
        verify(articleJpaRepository).findAll();
        verify(articleEntityMapper).toArticle(entity1);
        verify(articleEntityMapper).toArticle(entity2);
    }
}