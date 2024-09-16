package com.example.demo_emazon.category.infraestructure.out.jpa.adapter;

import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.spi.ICategoryPersistencePort;
import com.example.demo_emazon.category.infraestructure.out.jpa.mapper.ICategoryEntityMapper;
import com.example.demo_emazon.category.infraestructure.out.jpa.repository.ICategoryJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryJpaRepository categoryJpaRepository;
    private final ICategoryEntityMapper categoryEntityMapper;


    @Override
    public Category save(Category category) {
        return categoryEntityMapper.toCategory(
                categoryJpaRepository.save(
                        categoryEntityMapper.toEntity(category)
                )
        );
    }

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository.findAll().stream()
                .map(categoryEntityMapper::toCategory)
                .toList();
    }

    @Override
    public Category findByName(String nameCategory) {
        return categoryEntityMapper.toCategory(categoryJpaRepository.findByNameCategory(nameCategory).orElse(null));
    }

}
