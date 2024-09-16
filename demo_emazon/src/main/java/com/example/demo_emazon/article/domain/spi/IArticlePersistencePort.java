package com.example.demo_emazon.article.domain.spi;

import com.example.demo_emazon.article.domain.model.Article;

import java.util.List;

public interface IArticlePersistencePort {

    Article save(Article article);

    List<Article> findAll();

}
