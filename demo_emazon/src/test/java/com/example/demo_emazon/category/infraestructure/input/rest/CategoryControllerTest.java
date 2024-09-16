package com.example.demo_emazon.category.infraestructure.input.rest;

import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.application.dto.CategoryResponse;
import com.example.demo_emazon.category.application.handler.ICategoryHandler;
import com.example.demo_emazon.util.Constants;
import com.example.demo_emazon.util.pagination.Pagination;
import com.example.demo_emazon.util.TestData.TestDataCategory;
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
        CategoryRequest categoryRequest = TestDataCategory.getCategoryRequest();
        CategoryResponse categoryResponse = TestDataCategory.getCategoryResponse();

        when(categoryHandler.save(any(CategoryRequest.class))).thenReturn(categoryResponse);

        ResponseEntity<CategoryResponse> responseEntity = categoryController.createCategory(categoryRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(categoryResponse, responseEntity.getBody());
        verify(categoryHandler).save(categoryRequest);
    }

    @Test
    void testListCategories() {
        // Arrange
        int page = Constants.PAGE_IN_RANGE;
        int size = Constants.PAGE_SIZE_1;
        String sortDirection = Constants.SORT_ASC;

        CategoryResponse categoryResponse = TestDataCategory.getCategoryResponse();
        Pagination<CategoryResponse> pagination = new Pagination<>(List.of(categoryResponse), page, size, Constants.PAGE_SIZE_1);

        when(categoryHandler.listCategoryResponses(page, size, sortDirection)).thenReturn(pagination);

        ResponseEntity<Pagination<CategoryResponse>> responseEntity = categoryController.listCategories(page, size, sortDirection);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pagination, responseEntity.getBody());
        verify(categoryHandler).listCategoryResponses(page, size, sortDirection);
    }

}