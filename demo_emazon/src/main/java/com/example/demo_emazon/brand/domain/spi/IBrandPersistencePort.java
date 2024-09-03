package com.example.demo_emazon.brand.domain.spi;

import com.example.demo_emazon.brand.domain.model.Brand;

import java.util.List;

public interface IBrandPersistencePort {

    Brand save(Brand brand);
    List<Brand> findAll();
    Brand findByName(String nameBrand);

}
