package com.example.demo_emazon.category.infraestructure.input.rest;

import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.application.dto.CategoryResponse;
import com.example.demo_emazon.category.application.handler.ICategoryHandler;
import com.example.demo_emazon.category.domain.util.pagination.Pagination;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private ICategoryHandler categoryHandler;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void testCreateCategory() {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest("Electronics", "Electronics Description");
        CategoryResponse categoryResponse = new CategoryResponse(1L, "Electronics", "Electronics Description");

        when(categoryHandler.save(any(CategoryRequest.class))).thenReturn(categoryResponse);

        // Act
        ResponseEntity<CategoryResponse> responseEntity = categoryController.createCategory(categoryRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(categoryResponse, responseEntity.getBody());
        verify(categoryHandler).save(categoryRequest);
    }

    @Test
    void testListCategories() {
        // Arrange
        int page = 1;
        int size = 10;
        String sortDirection = "asc";

        CategoryResponse categoryResponse = new CategoryResponse(1L, "Electronics", "Electronics Description");
        Pagination<CategoryResponse> pagination = new Pagination<>(List.of(categoryResponse), page, size, 1);

        when(categoryHandler.listCategoryResponses(page, size, sortDirection)).thenReturn(pagination);

        // Act
        ResponseEntity<Pagination<CategoryResponse>> responseEntity = categoryController.listCategories(page, size, sortDirection);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pagination, responseEntity.getBody());
        verify(categoryHandler).listCategoryResponses(page, size, sortDirection);
    }

}