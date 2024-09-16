package com.example.demo_emazon.article.application.handler;

import com.example.demo_emazon.article.application.dto.ArticleRequest;
import com.example.demo_emazon.article.application.dto.ArticleResponse;

public interface IArticleHandler {

    ArticleResponse createArticle(ArticleRequest articleRequest);

}
