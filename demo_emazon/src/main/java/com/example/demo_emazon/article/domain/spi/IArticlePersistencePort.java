package com.example.demo_emazon.article.domain.spi;

import com.example.demo_emazon.article.domain.model.Article;

public interface IArticlePersistencePort {

    Article save(Article article);

}
