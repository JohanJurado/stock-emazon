package com.example.demo_emazon.article.domain.usecase;

import com.example.demo_emazon.article.domain.exception.BrandNotFoundException;
import com.example.demo_emazon.article.domain.exception.CategoryNotFoundException;
import com.example.demo_emazon.article.domain.exception.MaximumRelatedCategoriesException;
import com.example.demo_emazon.article.domain.exception.MinimumOfRelatedCategoriesException;
import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.article.domain.spi.IArticlePersistencePort;
import com.example.demo_emazon.article.domain.util.ExceptionConstantsArticle;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.domain.spi.IBrandPersistencePort;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.spi.ICategoryPersistencePort;
import com.example.demo_emazon.util.Constants;
import com.example.demo_emazon.util.TestData.TestDataArticle;
import com.example.demo_emazon.util.TestData.TestDataBrand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    @Test
    void createArticle_ShouldThrowMinimumOfRelatedCategoriesException_WhenNoCategories() {
        Article article = TestDataArticle.getArticleWithoutCategories();
        Brand brand = TestDataBrand.getBrand();

        when(brandPersistencePort.findByName(article.getBrand().getNameBrand())).thenReturn(brand);

        MinimumOfRelatedCategoriesException exception = assertThrows(MinimumOfRelatedCategoriesException.class, () -> {
            articleUseCase.createArticle(article);
        });

        assertEquals(ExceptionConstantsArticle.MINIUM_OF_RELATED_CATEGORIES.getMessage(), exception.getMessage());
    }

    @Test
    void createArticle_ShouldThrowMaximumRelatedCategoriesException_WhenTooManyCategories() {
        Article article = TestDataArticle.getArticle();
        article.setCategories(TestDataArticle.getArticleWith4Categories().getCategories());
        Brand brand = TestDataBrand.getBrand();

        when(brandPersistencePort.findByName(article.getBrand().getNameBrand())).thenReturn(brand);

        // Ejecutar y verificar la excepción
        MaximumRelatedCategoriesException exception = assertThrows(MaximumRelatedCategoriesException.class, () -> {
            articleUseCase.createArticle(article);
        });

        assertEquals(ExceptionConstantsArticle.MAXIUM_RELATED_CATEGORIES.getMessage(), exception.getMessage());
    }

    @Test
    void createArticle_ShouldThrowCategoryNotFoundException_WhenCategoryDoesNotExist() {
        Article article = TestDataArticle.getArticle();
        article.setCategories(List.of(Constants.CATEGORY_WITHOUT_NAME));

        Brand brand = TestDataBrand.getBrand();

        when(brandPersistencePort.findByName(article.getBrand().getNameBrand())).thenReturn(brand);


        // Configurar mocks para devolver null para una categoría
        when(categoryPersistencePort.findByName(Constants.CATEGORY_WITHOUT_NAME.getNameCategory())).thenReturn(null);

        // Ejecutar y verificar la excepción
        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> {
            articleUseCase.createArticle(article);
        });

        assertEquals("Category not found" + Constants.CATEGORY_WITHOUT_NAME.getNameCategory(), exception.getMessage());
    }

    @Test
    void createArticle_ShouldThrowBrandNotFoundException_WhenBrandDoesNotExist() {
        Article article = TestDataArticle.getArticle();

        // Configurar mocks para devolver null para la marca
        when(brandPersistencePort.findByName(article.getBrand().getNameBrand())).thenReturn(null);

        // Ejecutar y verificar la excepción
        BrandNotFoundException exception = assertThrows(BrandNotFoundException.class, () -> {
            articleUseCase.createArticle(article);
        });

        assertEquals("Brand not found: ", exception.getMessage());
    }

    @Test
    void createArticle_Success() {
        Article article = TestDataArticle.getArticle();
        Category category1 = article.getCategories().get(0);
        Category category2 = article.getCategories().get(1);
        Category category3 = article.getCategories().get(2);

        // Configurar mocks
        when(brandPersistencePort.findByName(article.getBrand().getNameBrand())).thenReturn(article.getBrand());
        when(categoryPersistencePort.findByName(category1.getNameCategory())).thenReturn(category1);
        when(categoryPersistencePort.findByName(category2.getNameCategory())).thenReturn(category2);
        when(categoryPersistencePort.findByName(category3.getNameCategory())).thenReturn(category3);
        when(articlePersistencePort.save(any(Article.class))).thenReturn(article);

        // Ejecutar el caso de uso
        Article result = articleUseCase.createArticle(article);

        // Verificar resultados
        assertNotNull(result);
        verify(brandPersistencePort, times(1)).findByName(article.getBrand().getNameBrand());
        verify(categoryPersistencePort, times(1)).findByName(category1.getNameCategory());
        verify(categoryPersistencePort, times(1)).findByName(category2.getNameCategory());
        verify(articlePersistencePort, times(1)).save(article);
    }
}
