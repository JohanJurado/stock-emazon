package com.example.demo_emazon.brand.infraestructure.out.jpa.mapper;

import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.domain.util.Pagination;
import com.example.demo_emazon.brand.infraestructure.out.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandEntityMapper {

    BrandEntity toEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);

}