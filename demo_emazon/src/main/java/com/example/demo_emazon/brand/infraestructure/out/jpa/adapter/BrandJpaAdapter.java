package com.example.demo_emazon.brand.infraestructure.out.jpa.adapter;

import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.domain.spi.IBrandPersistencePort;
import com.example.demo_emazon.brand.infraestructure.out.jpa.mapper.IBrandEntityMapper;
import com.example.demo_emazon.brand.infraestructure.out.jpa.repository.IBrandJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandJpaRepository brandJpaRepository;

    @Override
    public Brand save(Brand brand) {
        return brandEntityMapper.toBrand(
                brandJpaRepository.save(
                        brandEntityMapper.toEntity(brand)
                )
        );
    }

    @Override
    public List<Brand> findAll() {
        return brandJpaRepository.findAll().stream()
                .map(brandEntityMapper::toBrand)
                .toList();
    }

    @Override
    public Brand findByName(String nameBrand) {
        return brandEntityMapper.toBrand(brandJpaRepository.findByNameBrand(nameBrand).orElse(null));
    }
}
