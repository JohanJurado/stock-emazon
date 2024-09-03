package com.example.demo_emazon.category.infraestructure.configuration;

import com.example.demo_emazon.category.domain.api.ICategoryServicePort;
import com.example.demo_emazon.category.domain.spi.ICategoryPersistencePort;
import com.example.demo_emazon.category.domain.usecase.CategoryUseCase;
import com.example.demo_emazon.category.infraestructure.out.jpa.adapter.CategoryJpaAdapter;
import com.example.demo_emazon.category.infraestructure.out.jpa.mapper.ICategoryEntityMapper;
import com.example.demo_emazon.category.infraestructure.out.jpa.repository.ICategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationCategory {

    private final ICategoryJpaRepository categoryJpaRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryJpaRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

}
