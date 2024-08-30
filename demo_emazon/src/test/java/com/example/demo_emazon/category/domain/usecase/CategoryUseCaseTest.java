package com.example.demo_emazon.category.domain.usecase;

import com.example.demo_emazon.category.domain.exception.*;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.spi.ICategoryPersistencePort;
import com.example.demo_emazon.category.domain.util.pagination.Pagination;
import com.example.demo_emazon.testdata.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    @DisplayName("Create a New Category correctly")
    void verifyWhenCategoryReturnsCorrectCreatedStatus() {
        Category category = TestData.getCategory();
        Mockito.when(categoryPersistencePort.save(category)).thenReturn(category);
        Mockito.when(categoryPersistencePort.findByName(category.getName())).thenReturn(null);

        Category resultado = categoryUseCase.createCategory(category);

        assertEquals(category, resultado);
        verify(categoryPersistencePort, times(1)).save(category);

    }

    @Test
    @DisplayName("Verify that when entering a name exist, an exception is returned")
    void verifyCategoryAlreadyExistException() {
        Category category = TestData.getCategory();


        Mockito.when(categoryPersistencePort.findByName(category.getName())).
                thenReturn(category);

        assertThrows(
                CategoryAlreadyExistException.class,
                () -> categoryUseCase.createCategory(category),
                "Expected createCategory to throw CategoryAlreadyExistException, but it didn't"
        );

        verify(categoryPersistencePort, never()).save(any(Category.class));

    }

    @Test
    @DisplayName("Verify that when entering a name greater than 50 characters, an exception is returned")
    void verifyMaxiumNameSizeExceededException() {
        Category category = TestData.getCategory();
        category.setName("123451234512345123451234512345123451234512345123451234512345");


        Mockito.when(categoryPersistencePort.findByName(category.getName()))
                .thenReturn(null);

        assertThrows(
                MaxiumNameSizeExceededException.class,
                () -> categoryUseCase.createCategory(category),
                "Expected createCategory to throw MaxiumNameSizeExceededException, but it didn't"
        );

        verify(categoryPersistencePort, never()).save(any(Category.class));

    }

    @Test
    @DisplayName("Verify that when entering a description greater than 90 characters, an exception is returned")
    void verifyMaxiumDescriptonSizeExceededException() {
        Category category = TestData.getCategory();
        category.setDescription("123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345");


        Mockito.when(categoryPersistencePort.findByName(category.getName()))
                .thenReturn(null);

        assertThrows(
                MaxiumDescriptionSizeExceededException.class,
                () -> categoryUseCase.createCategory(category),
                "Expected createCategory to throw MaxiumDescriptionSizeExceededException, but it didn't"
        );

        verify(categoryPersistencePort, never()).save(any(Category.class));

    }

    @Test
    @DisplayName("Verify that when entering a name null, an exception is returned")
    void verifyNameCannotBeEmptyException() {
        Category categoryEmpty = TestData.getCategory();
        categoryEmpty.setName("");
        Category categoryNull = TestData.getCategory();
        categoryNull.setName(null);

        assertThrows(
                TheNameCannotBeEmpty.class,
                () -> categoryUseCase.createCategory(categoryEmpty),
                "Expected createCategory to throw TheNameCannotBeEmpty by empty name, but it didn't"
        );

        assertThrows(
                TheNameCannotBeEmpty.class,
                () -> categoryUseCase.createCategory(categoryNull),
                "Expected createCategory to throw TheNameCannotBeEmpty by null name, but it didn't"
        );

        verify(categoryPersistencePort, never()).save(any(Category.class));

    }


    @Test
    @DisplayName("Verify that when entering a description null, an exception is returned")
    void verifyDescriptionCannotBeEmptyException() {
        Category categoryEmpty = TestData.getCategory();
        categoryEmpty.setDescription("");
        Category categoryNull = TestData.getCategory();
        categoryNull.setDescription(null);

        assertThrows(
                TheDescriptionCannotBeEmpty.class,
                () -> categoryUseCase.createCategory(categoryEmpty),
                "Expected createCategory to throw TheDescriptionCannotBeEmpty by empty description, but it didn't"
        );

        assertThrows(
                TheDescriptionCannotBeEmpty.class,
                () -> categoryUseCase.createCategory(categoryNull),
                "Expected createCategory to throw TheDescriptionCannotBeEmpty by null description, but it didn't"
        );

        verify(categoryPersistencePort, never()).save(any(Category.class));

    }

    @Test
    void testListCategoryAscending() {
        // Arrange
        Category category1 = new Category(1L, "Books", "Books category");
        Category category2 = new Category(2L, "Electronics", "Electronics category");
        Category category3 = new Category(3L, "Furniture", "Furniture category");

        List<Category> allCategories = Arrays.asList(category2, category3, category1);
        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

            // Act
        Pagination<Category> pagination = categoryUseCase.listCategory(1, 2, "asc");

            // Assert
        assertEquals(2, pagination.getContent().size());
        assertEquals("Books", pagination.getContent().get(0).getName());
        assertEquals("Electronics", pagination.getContent().get(1).getName());
        verify(categoryPersistencePort, times(1)).findAll();
    }

    @Test
    void testListCategoryDescending() {
        // Arrange
        Category category1 = new Category(1L, "Books", "Books category");
        Category category2 = new Category(2L, "Electronics", "Electronics category");
        Category category3 = new Category(3L, "Furniture", "Furniture category");

        List<Category> allCategories = Arrays.asList(category2, category3, category1);
        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

            // Act
        Pagination<Category> pagination = categoryUseCase.listCategory(1, 2, "desc");

            // Assert
        assertEquals(2, pagination.getContent().size());
        assertEquals("Furniture", pagination.getContent().get(0).getName());
        assertEquals("Electronics", pagination.getContent().get(1).getName());
        verify(categoryPersistencePort, times(1)).findAll();
    }

    @Test
    void testListCategoryPagination() {
            // Arrange
        Category category1 = new Category(1L, "Books", "Books category");
        Category category2 = new Category(2L, "Electronics", "Electronics category");
        Category category3 = new Category(3L, "Furniture", "Furniture category");

        List<Category> allCategories = Arrays.asList(category1, category2, category3);
        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

            // Act
        Pagination<Category> pagination = categoryUseCase.listCategory(2, 1, "asc");

            // Assert
        assertEquals(1, pagination.getContent().size());
        assertEquals("Electronics", pagination.getContent().get(0).getName());
        verify(categoryPersistencePort, times(1)).findAll();
    }

    @Test
    void testListCategoryOutOfBounds() {
        // Arrange
        Category category1 = new Category(1L, "Books", "Books category");
        Category category2 = new Category(2L, "Electronics", "Electronics category");

        List<Category> allCategories = Arrays.asList(category1, category2);
        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

            // Act
        Pagination<Category> pagination = categoryUseCase.listCategory(3, 1, "asc");

            // Assert
        assertTrue(pagination.getContent().isEmpty());
        assertEquals(3, pagination.getPageNumber());
        assertEquals(1, pagination.getPageSize());
        assertEquals(2, pagination.getTotalElements());
        verify(categoryPersistencePort, times(1)).findAll();
    }

    @Test
    void testListCategoryPage0() {
        // Arrange
        Category category1 = new Category(1L, "Books", "Books category");
        Category category2 = new Category(2L, "Electronics", "Electronics category");

        List<Category> allCategories = Arrays.asList(category1, category2);
        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

        // Act
        Pagination<Category> pagination = categoryUseCase.listCategory(0, 1, "asc");

        // Assert
        assertTrue(pagination.getContent().isEmpty());
        assertEquals(0, pagination.getPageNumber());
        assertEquals(1, pagination.getPageSize());
        assertEquals(2, pagination.getTotalElements());
        verify(categoryPersistencePort, times(1)).findAll();
    }
}
