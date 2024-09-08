package com.example.demo_emazon.brand.infraestructure.out.jpa.mapper;

import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.infraestructure.out.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {

    @Mapping(target = "idBrand", source="idBrand")
    @Mapping(target = "nameBrand", source="nameBrand")
    @Mapping(target = "descriptionBrand", source="descriptionBrand")
    BrandEntity toEntity(Brand brand);

    @Mapping(target = "idBrand", source="idBrand")
    @Mapping(target = "nameBrand", source="nameBrand")
    @Mapping(target = "descriptionBrand", source="descriptionBrand")
    Brand toBrand(BrandEntity brandEntity);

}
