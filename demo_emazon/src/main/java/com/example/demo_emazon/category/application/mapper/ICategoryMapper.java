package com.example.demo_emazon.category.application.mapper;

import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.application.dto.CategoryResponse;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.util.pagination.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {

    Category toCategory(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(Category category);

    Pagination<CategoryResponse> toResposePagination(Pagination<Category> category);


}
