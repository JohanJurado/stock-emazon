package com.example.demo_emazon.category.domain.usecase;

import com.example.demo_emazon.category.domain.exception.*;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.spi.ICategoryPersistencePort;
import com.example.demo_emazon.category.domain.util.pagination.Pagination;
import com.example.demo_emazon.testdata.Constants;
import com.example.demo_emazon.testdata.TestData.TestDataCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        Category category = TestDataCategory.getCategory();
        Mockito.when(categoryPersistencePort.save(category)).thenReturn(category);
        Mockito.when(categoryPersistencePort.findByName(category.getNameCategory())).thenReturn(null);

        Category resultado = categoryUseCase.createCategory(category);

        assertEquals(category, resultado);
        verify(categoryPersistencePort, times(1)).save(category);

    }

    @Test
    @DisplayName("Verify that when entering a name exist, an exception is returned")
    void verifyCategoryAlreadyExistException() {
        Category category = TestDataCategory.getCategory();

        Mockito.when(categoryPersistencePort.findByName(category.getNameCategory())).
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
        Category category = TestDataCategory.getCategory();
        category.setNameCategory(Constants.MAX_NAME);

        Mockito.when(categoryPersistencePort.findByName(category.getNameCategory()))
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
        Category category = TestDataCategory.getCategory();
        category.setDescriptionCategory(Constants.MAX_DESCRIPTION_CATEGORY);


        Mockito.when(categoryPersistencePort.findByName(category.getNameCategory()))
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
        Category categoryEmpty = TestDataCategory.getCategory();
        categoryEmpty.setNameCategory(Constants.EMPTY);
        Category categoryNull = TestDataCategory.getCategory();
        categoryNull.setNameCategory(Constants.NULL);

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
        Category categoryEmpty = TestDataCategory.getCategory();
        categoryEmpty.setDescriptionCategory(Constants.EMPTY);
        Category categoryNull = TestDataCategory.getCategory();
        categoryNull.setDescriptionCategory(Constants.NULL);

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

        List<Category> allCategories = TestDataCategory.getListCategories();
        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

        Pagination<Category> pagination = categoryUseCase.listCategory(1, 2, "asc");

        assertEquals(2, pagination.getContent().size());
        assertEquals("Books", pagination.getContent().get(0).getNameCategory());
        assertEquals("Electronics", pagination.getContent().get(1).getNameCategory());
        verify(categoryPersistencePort, times(1)).findAll();
    }

    @Test
    void testListCategoryDescending() {

        List<Category> allCategories = TestDataCategory.getListCategories();

        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

        Pagination<Category> pagination = categoryUseCase.listCategory(1, 2, "desc");

        assertEquals(2, pagination.getContent().size());
        assertEquals("Furniture", pagination.getContent().get(0).getNameCategory());
        assertEquals("Electronics", pagination.getContent().get(1).getNameCategory());
        verify(categoryPersistencePort, times(1)).findAll();
    }

    @Test
    void testListCategoryPagination() {

        List<Category> allCategories = TestDataCategory.getListCategories();

        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

        Pagination<Category> pagination = categoryUseCase.listCategory(2, 1, "asc");

        assertEquals(1, pagination.getContent().size());
        assertEquals("Electronics", pagination.getContent().get(0).getNameCategory());
        verify(categoryPersistencePort, times(1)).findAll();
    }

    @Test
    void testListCategoryOutOfBounds() {

        List<Category> allCategories = TestDataCategory.getListCategories();

        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

        Pagination<Category> pagination = categoryUseCase.listCategory(4, 1, "asc");

        assertTrue(pagination.getContent().isEmpty());
        assertEquals(4, pagination.getPageNumber());
        assertEquals(1, pagination.getPageSize());
        assertEquals(3, pagination.getTotalElements());
        verify(categoryPersistencePort, times(1)).findAll();
    }

    @Test
    void testListCategoryPage0() {

        List<Category> allCategories = TestDataCategory.getListCategories();

        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

        Pagination<Category> pagination = categoryUseCase.listCategory(0, 1, "asc");

        assertTrue(pagination.getContent().isEmpty());
        assertEquals(0, pagination.getPageNumber());
        assertEquals(1, pagination.getPageSize());
        assertEquals(3, pagination.getTotalElements());
        verify(categoryPersistencePort, times(1)).findAll();
    }
}
