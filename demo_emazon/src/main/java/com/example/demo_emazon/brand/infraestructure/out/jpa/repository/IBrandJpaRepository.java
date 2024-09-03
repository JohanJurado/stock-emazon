package com.example.demo_emazon.brand.infraestructure.out.jpa.repository;

import com.example.demo_emazon.brand.infraestructure.out.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandJpaRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findByNameBrand(String nameBrand);

}
