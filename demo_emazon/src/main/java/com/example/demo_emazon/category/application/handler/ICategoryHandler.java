package com.example.demo_emazon.category.application.handler;

import com.example.demo_emazon.category.application.dto.CategoryResponse;
import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.domain.util.pagination.Pagination;

public interface ICategoryHandler {

    CategoryResponse save(CategoryRequest categoryRequest);
    Pagination<CategoryResponse> listCategoryResponses(int number, int size, String sortDirection);

}
