package com.example.demo_emazon.article.application.handler;

import com.example.demo_emazon.article.application.dto.ArticleRequest;
import com.example.demo_emazon.article.application.dto.ArticleResponse;
import com.example.demo_emazon.util.pagination.Pagination;

public interface IArticleHandler {

    ArticleResponse createArticle(ArticleRequest articleRequest);
    Pagination<ArticleResponse> listArticles(int number, int size, String sortDirection, String model);

}
