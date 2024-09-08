package com.example.demo_emazon.category.infraestructure.out.jpa.mapper;

import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.infraestructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {

    @Mapping(target = "idCategory", source="idCategory")
    @Mapping(target = "nameCategory", source="nameCategory")
    @Mapping(target = "descriptionCategory", source="descriptionCategory")
    CategoryEntity toEntity(Category category);

    @Mapping(target = "idCategory", source="idCategory")
    @Mapping(target = "nameCategory", source="nameCategory")
    @Mapping(target = "descriptionCategory", source="descriptionCategory")
    Category toCategory(CategoryEntity categoryEntity);


}
