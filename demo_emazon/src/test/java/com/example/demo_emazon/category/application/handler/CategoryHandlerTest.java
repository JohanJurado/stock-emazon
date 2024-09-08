package com.example.demo_emazon.category.application.handler;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.application.dto.CategoryResponse;
import com.example.demo_emazon.category.application.mapper.ICategoryMapper;
import com.example.demo_emazon.category.domain.api.ICategoryServicePort;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.util.pagination.Pagination;
import com.example.demo_emazon.util.TestData.TestDataCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CategoryHandlerTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryMapper categoryMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @Test
    @DisplayName("Create a New Category correctly")
    void testSave() {
        CategoryRequest categoryRequest = TestDataCategory.getCategoryRequest();
        Category category = TestDataCategory.getCategory();
        Category savedCategory = TestDataCategory.getCategory();
        CategoryResponse expectedResponse = TestDataCategory.getCategoryResponse();

        when(categoryMapper.toCategory(categoryRequest)).thenReturn(category);
        when(categoryServicePort.createCategory(category)).thenReturn(savedCategory);
        when(categoryMapper.toCategoryResponse(savedCategory)).thenReturn(expectedResponse);

        CategoryResponse actualResponse = categoryHandler.save(categoryRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(categoryMapper).toCategory(categoryRequest);
        verify(categoryServicePort).createCategory(category);
        verify(categoryMapper).toCategoryResponse(savedCategory);
    }

    @Test
    @DisplayName("Test List Categories Responses")
    void testListCategoryResponses() {
        int pageNumber = 1;
        int pageSize = 10;
        String sortDirection = "asc";

        List<Category> content = null;
        List<CategoryResponse> contentResponse = null;
        int totalElements = 20;

        Pagination<Category> categoryPagination =
                new Pagination<>(content, pageNumber, pageSize, totalElements);
        Pagination<CategoryResponse> expectedResponsePagination =
                new Pagination<>(contentResponse, pageNumber, pageSize, totalElements);

        when(categoryServicePort.listCategory(pageNumber, pageSize, sortDirection))
                .thenReturn(categoryPagination);

        when(categoryMapper.toResposePagination(categoryPagination))
                .thenReturn(expectedResponsePagination);

        Pagination<CategoryResponse> actualResponsePagination =
                categoryHandler.listCategoryResponses(pageNumber, pageSize, sortDirection);

        assertEquals(expectedResponsePagination, actualResponsePagination);
        verify(categoryServicePort).listCategory(pageNumber, pageSize, sortDirection);
        verify(categoryMapper).toResposePagination(categoryPagination);
    }
}