package com.example.demo_emazon.brand.application.mapper;

import com.example.demo_emazon.brand.application.dto.BrandRequest;
import com.example.demo_emazon.brand.application.dto.BrandResponse;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.util.pagination.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IBrandMapper {

    IBrandMapper INSTANCE = Mappers.getMapper(IBrandMapper.class);

    @Mapping(target = "idBrand", ignore = true)
    @Mapping(target = "nameBrand", source="nameBrand")
    @Mapping(target = "descriptionBrand", source="descriptionBrand")
    Brand toBrand(BrandRequest brandRequest);

    @Mapping(target = "nameBrand", source="nameBrand")
    @Mapping(target = "descriptionBrand", source="descriptionBrand")
    BrandResponse toBrandResponse(Brand brand);

    Pagination<BrandResponse> toResposePagination(Pagination<Brand> brand);

}
