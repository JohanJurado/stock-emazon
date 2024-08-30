package com.example.demo_emazon.category.domain.spi;

import com.example.demo_emazon.category.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {

    Category save(Category category);
    List<Category> findAll();
    Category findByName(String name);


}
