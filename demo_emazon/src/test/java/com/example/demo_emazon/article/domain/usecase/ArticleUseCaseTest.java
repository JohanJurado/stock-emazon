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
import com.example.demo_emazon.category.domain.exception.CategoryAlreadyExistException;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.spi.ICategoryPersistencePort;
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

import java.util.Arrays;
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

    @Test
    void testSortByArticleNameAscending() {
        // Mockear el artículo para devolver los artículos
        when(articlePersistencePort.findAll()).thenReturn(TestDataArticle.getListArticles());

        // Ejecutar el metodo
        Pagination<Article> result = articleUseCase.listArticles(1, 3, "asc", "article");

        // Verificar el contenido
        assertEquals(TestDataArticle.getListArticles().size(), result.getContent().size());
        assertEquals(TestDataArticle.getListArticles().get(0).getNameArticle(), result.getContent().get(0).getNameArticle());
        assertEquals(TestDataArticle.getListArticles().get(1).getNameArticle(), result.getContent().get(1).getNameArticle());
        assertEquals(TestDataArticle.getListArticles().get(2).getNameArticle(), result.getContent().get(2).getNameArticle());
    }

    @Test
    void testSortByBrandDescending() {
        // Mockear los datos de artículos
        when(articlePersistencePort.findAll()).thenReturn(TestDataArticle.getListArticles());

        // Ejecutar el metodo para ordenar por brand en forma descendente
        Pagination<Article> result = articleUseCase.listArticles(1, 3, "desc", "brand");

        // Verificar el contenido
        assertEquals(TestDataArticle.getListArticles().size(), result.getContent().size());
        assertEquals(TestDataArticle.getListArticles().get(0).getBrand().getNameBrand(), result.getContent().get(0).getBrand().getNameBrand()); // El primer elemento será Marca B (descendente)
        assertEquals(TestDataArticle.getListArticles().get(1).getBrand().getNameBrand(), result.getContent().get(1).getBrand().getNameBrand());
    }

    @Test
    void testSortByCategoryAscending() {
        // Mockear los datos de artículos
        when(articlePersistencePort.findAll()).thenReturn(TestDataArticle.getListArticles());

        // Ejecutar el metodo para ordenar por la primera categoría en forma ascendente
        Pagination<Article> result = articleUseCase.listArticles(1, 3, "asc", "category");

        // Verificar el contenido
        assertEquals(TestDataArticle.getListArticles().size(), result.getContent().size());
        assertEquals(TestDataArticle.getListArticles().get(0).getCategories().get(0).getNameCategory(), result.getContent().get(0).getCategories().get(0).getNameCategory());
        assertEquals(TestDataArticle.getListArticles().get(1).getCategories().get(0).getNameCategory(), result.getContent().get(1).getCategories().get(0).getNameCategory());
    }

    @Test
    void testPagination() {
        // Mockear los datos de artículos
        when(articlePersistencePort.findAll()).thenReturn(TestDataArticle.getListArticles());

        // Probar paginación con tamaño de página 2
        Pagination<Article> result = articleUseCase.listArticles(1, 3, "asc", "article");

        // Verificar la paginación
        assertEquals(TestDataArticle.getListArticles().size(), result.getContent().size());  // Debería devolver 2 artículos
        assertEquals(TestDataArticle.getListArticles().get(0).getNameArticle(), result.getContent().get(0).getNameArticle());
        assertEquals(TestDataArticle.getListArticles().get(1).getNameArticle(), result.getContent().get(1).getNameArticle());
    }

    @Test
    void testPageOutOfBounds() {
        // Mockear los datos de artículos
        when(articlePersistencePort.findAll()).thenReturn(TestDataArticle.getListArticles());

        // Probar una página fuera de los límites
        Pagination<Article> result = articleUseCase.listArticles(10, 3, "asc", "article");

        // Verificar que la página esté vacía
        assertTrue(result.getContent().isEmpty());
        assertEquals(10, result.getPageNumber());
        assertEquals(0, result.getContent().size());
    }

    @Test
    void testFindByNameBrand_Exist() {
        // Preparar el mock para la marca existente
        Brand brand = TestDataBrand.getBrand();
        when(brandPersistencePort.findByName(brand.getNameBrand())).thenReturn(brand);

        // Ejecutar el metodo
        Brand result = articleUseCase.findByNameBrand(brand.getNameBrand());

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(brand.getNameBrand(), result.getNameBrand());
    }

    @Test
    void testFindByNameBrand_NoExist() {
        // Preparar el mock para cuando la marca no existe
        when(brandPersistencePort.findByName(TestDataBrand.getBrand().getNameBrand())).thenReturn(null);

        // Verificar que se lanza la excepción correcta
        BrandNotFoundException exception = assertThrows(BrandNotFoundException.class, () -> {
            articleUseCase.findByNameBrand(TestDataBrand.getBrand().getNameBrand());
        });

        assertEquals("Brand not found: "+TestDataBrand.getBrand().getNameBrand(), exception.getMessage()+TestDataBrand.getBrand().getNameBrand());
    }

    @Test
    void testFindByNamesCategory() {
        // Preparar el mock para las categorías existentes
        Category category1 = TestDataCategory.getListCategories().get(0);
        Category category2 = TestDataCategory.getListCategories().get(1);

        when(categoryPersistencePort.findByName(category1.getNameCategory())).thenReturn(category1);
        when(categoryPersistencePort.findByName(category2.getNameCategory())).thenReturn(category2);

        // Ejecutar el metodo
        List<Category> result = articleUseCase.findByNamesCategory(Arrays.asList(category1.getNameCategory(), category2.getNameCategory()));

        // Verificar el resultado
        assertEquals(2, result.size());
        assertEquals(category1.getNameCategory(), result.get(0).getNameCategory());
        assertEquals(category2.getNameCategory(), result.get(1).getNameCategory());
    }

    @Test
    void testFindByNamesCategory_CategoryDoesNotExist() {
        // Preparar el mock para cuando una categoría no existe
        when(categoryPersistencePort.findByName(TestDataCategory.getCategory().getNameCategory())).thenReturn(null);

        // Verificar que se lanza la excepción correcta
        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> {
            articleUseCase.findByNamesCategory(List.of(TestDataCategory.getCategory().getNameCategory()));
        });

        assertEquals("Category not found", exception.getMessage());
    }

    @Test
    void testFindByNamesCategory_Duplicated() {
        Category category1 = TestDataCategory.getListCategories().get(0);
        Category category2 = TestDataCategory.getListCategories().get(0);

        when(categoryPersistencePort.findByName(category1.getNameCategory())).thenReturn(category1);

        // Verificar que se lanza la excepción de categoría duplicada
        CategoryAlreadyExistException exception = assertThrows(CategoryAlreadyExistException.class, () -> {
            articleUseCase.findByNamesCategory(Arrays.asList(category1.getNameCategory(), category2.getNameCategory()));
        });

        assertEquals("The name of category already exist: "+category1.getNameCategory(), exception.getMessage()+" "+category1.getNameCategory());
    }
}
