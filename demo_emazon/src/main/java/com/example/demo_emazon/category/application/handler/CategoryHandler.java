package com.example.demo_emazon.category.application.handler;

import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.application.dto.CategoryResponse;
import com.example.demo_emazon.category.application.mapper.ICategoryMapper;
import com.example.demo_emazon.category.domain.api.ICategoryServicePort;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.util.pagination.Pagination;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryMapper categoryMapper;


    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toCategory(categoryRequest);
        return categoryMapper.toCategoryResponse(categoryServicePort.createCategory(category));
    }

    @Override
    public Pagination<CategoryResponse> listCategoryResponses(int number, int size, String sortDirection) {
        return categoryMapper.toResposePagination(
                categoryServicePort.listCategory(number, size, sortDirection)
        );
    }
}
