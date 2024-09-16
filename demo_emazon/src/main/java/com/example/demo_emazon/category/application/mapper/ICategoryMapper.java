package com.example.demo_emazon.category.application.mapper;

import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.application.dto.CategoryResponse;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.util.pagination.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {

    @Mapping(target = "idCategory", ignore = true)
    @Mapping(target = "nameCategory", source="nameCategory")
    @Mapping(target = "descriptionCategory", source="descriptionCategory")
    Category toCategory(CategoryRequest categoryRequest);

    @Mapping(target = "idCategory", source="idCategory")
    @Mapping(target = "nameCategory", source="nameCategory")
    @Mapping(target = "descriptionCategory", source="descriptionCategory")
    CategoryResponse toCategoryResponse(Category category);

    Pagination<CategoryResponse> toResposePagination(Pagination<Category> category);

}
