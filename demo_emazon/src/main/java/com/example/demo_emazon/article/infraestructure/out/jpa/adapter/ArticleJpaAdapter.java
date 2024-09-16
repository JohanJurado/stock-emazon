package com.example.demo_emazon.article.infraestructure.out.jpa.adapter;

import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.article.domain.spi.IArticlePersistencePort;
import com.example.demo_emazon.article.infraestructure.out.jpa.mapper.IArticleEntityMapper;
import com.example.demo_emazon.article.infraestructure.out.jpa.repository.IArticleJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleEntityMapper articleEntityMapper;
    private final IArticleJpaRepository articleJpaRepository;

    @Override
    public Article save(Article article) {
        return articleEntityMapper.toArticle(
                articleJpaRepository.save(articleEntityMapper.toArticleEntity(article))
        );
    }

    @Override
    public List<Article> findAll() {
        return articleJpaRepository.findAll().stream()
                .map(articleEntityMapper::toArticle)
                .toList();
    }
}
