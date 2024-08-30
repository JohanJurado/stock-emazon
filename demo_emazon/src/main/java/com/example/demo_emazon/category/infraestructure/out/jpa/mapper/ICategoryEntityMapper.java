package com.example.demo_emazon.category.infraestructure.out.jpa.mapper;

import com.example.demo_emazon.category.application.mapper.ICategoryMapper;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.util.pagination.Pagination;
import com.example.demo_emazon.category.infraestructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {

    ICategoryMapper INSTANCE = Mappers.getMapper(ICategoryMapper.class);

    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
    Pagination<Category> toCategoryList(Pagination<CategoryEntity> categoryEntityPagination);

}
