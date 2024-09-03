package com.example.demo_emazon.brand.application.mapper;

import com.example.demo_emazon.brand.application.dto.BrandRequest;
import com.example.demo_emazon.brand.application.dto.BrandResponse;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.domain.util.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandMapper {

    IBrandMapper INSTANCE = Mappers.getMapper(IBrandMapper.class);

    @Mapping(target = "nameBrand", source="nameBrand")
    @Mapping(target = "descriptionBrand", source="descriptionBrand")
    Brand toBrand(BrandRequest brandRequest);

    @Mapping(target = "nameBrand", source="nameBrand")
    @Mapping(target = "descriptionBrand", source="descriptionBrand")
    BrandResponse toBrandResponse(Brand brand);

    Pagination<BrandResponse> toResposePagination(Pagination<Brand> brand);

}
