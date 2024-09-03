package com.example.demo_emazon.brand.infraestructure.configuration;

import com.example.demo_emazon.brand.domain.api.IBrandServicePort;
import com.example.demo_emazon.brand.domain.spi.IBrandPersistencePort;
import com.example.demo_emazon.brand.domain.usecase.BrandUseCase;
import com.example.demo_emazon.brand.infraestructure.out.jpa.adapter.BrandJpaAdapter;
import com.example.demo_emazon.brand.infraestructure.out.jpa.mapper.IBrandEntityMapper;
import com.example.demo_emazon.brand.infraestructure.out.jpa.repository.IBrandJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationBrand {

    private final IBrandJpaRepository brandJpaRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandJpaAdapter(brandEntityMapper, brandJpaRepository);
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }

}
