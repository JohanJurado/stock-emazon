package com.example.demo_emazon.article.infraestructure.configuration;

import com.example.demo_emazon.article.domain.api.IArticleServicePort;
import com.example.demo_emazon.article.domain.spi.IArticlePersistencePort;
import com.example.demo_emazon.article.domain.usecase.ArticleUseCase;
import com.example.demo_emazon.article.infraestructure.out.jpa.adapter.ArticleJpaAdapter;
import com.example.demo_emazon.article.infraestructure.out.jpa.mapper.IArticleEntityMapper;
import com.example.demo_emazon.article.infraestructure.out.jpa.repository.IArticleJpaRepository;
import com.example.demo_emazon.brand.infraestructure.configuration.BeanConfigurationBrand;
import com.example.demo_emazon.category.infraestructure.configuration.BeanConfigurationCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationArticle {

    private final IArticleJpaRepository articleJpaRepository;
    private final IArticleEntityMapper articleEntityMapper;
    private final BeanConfigurationBrand beanConfigurationBrand;
    private final BeanConfigurationCategory beanConfigurationCategory;

    @Bean
    public IArticlePersistencePort articlePersistencePort(){
        return new ArticleJpaAdapter(articleEntityMapper, articleJpaRepository);
    }

    @Bean
    public IArticleServicePort articleServicePort(){
        return new ArticleUseCase(
                articlePersistencePort(),
                beanConfigurationCategory.categoryPersistencePort(),
                beanConfigurationBrand.brandPersistencePort()
        );
    }

}
