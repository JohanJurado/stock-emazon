package com.example.demo_emazon.util.TestData;

import com.example.demo_emazon.article.application.dto.ArticleRequest;
import com.example.demo_emazon.article.application.dto.ArticleResponse;
import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.article.infraestructure.out.jpa.entity.ArticleEntity;
import com.example.demo_emazon.util.Constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestDataArticle {

    public static Article getArticle(){
        Article article = new Article();

        article.setIdArticle(Constants.ID);
        article.setNameArticle(Constants.NAME);
        article.setDescriptionArticle(Constants.DESCRIPTION);
        article.setStock(Constants.STOCK);
        article.setPrice(Constants.PRICE);
        article.setBrand(Constants.BRAND1);
        article.setCategories(List.of(Constants.CATEGORY1, Constants.CATEGORY2, Constants.CATEGORY3));

        return article;
    }

    public static Article getArticleWith4Categories(){
        Article article = new Article();

        article.setIdArticle(Constants.ID);
        article.setNameArticle(Constants.NAME);
        article.setDescriptionArticle(Constants.DESCRIPTION);
        article.setStock(Constants.STOCK);
        article.setPrice(Constants.PRICE);
        article.setBrand(Constants.BRAND1);
        article.setCategories(Constants.CATEGORIES_OUT_RANGE);

        return article;
    }


    public static Article getArticleWithoutCategories(){
        Article article = new Article();

        article.setIdArticle(Constants.ID);
        article.setNameArticle(Constants.NAME);
        article.setDescriptionArticle(Constants.DESCRIPTION);
        article.setStock(Constants.STOCK);
        article.setPrice(Constants.PRICE);
        article.setBrand(Constants.BRAND1);
        article.setCategories(Collections.emptyList());

        return article;
    }

    public static List<Article> getListArticles(){
        Article article1 = Constants.ARTICLE;
        Article article2 = Constants.ARTICLE;
        Article article3 = Constants.ARTICLE;

        return Arrays.asList(article1, article2, article3);
    }

    public static ArticleResponse getArticleResponse(){
        ArticleResponse articleResponse = new ArticleResponse();

        articleResponse.setIdArticle(Constants.ID);
        articleResponse.setNameArticle(Constants.NAME);
        articleResponse.setDescriptionArticle(Constants.DESCRIPTION);
        articleResponse.setStock(Constants.STOCK);
        articleResponse.setPrice(Constants.PRICE);
        articleResponse.setBrand(Constants.BRAND1);
        articleResponse.setCategories(Constants.CATEGORIES);

        return articleResponse;
    }

    public static ArticleRequest getArticleRequest(){
        ArticleRequest articleRequest = new ArticleRequest();

        articleRequest.setNameArticle(Constants.NAME);
        articleRequest.setDescriptionArticle(Constants.DESCRIPTION);
        articleRequest.setStock(Constants.STOCK);
        articleRequest.setPrice(Constants.PRICE);
        articleRequest.setBrandName(Constants.NAME_BRAND);
        articleRequest.setCategoryNames(Constants.CATEGORY_NAMES);

        return articleRequest;
    }

    public static ArticleEntity getArticleEntity(){
        ArticleEntity articleEntity = new ArticleEntity();

        articleEntity.setIdArticle(Constants.ID);
        articleEntity.setNameArticle(Constants.NAME);
        articleEntity.setDescriptionArticle(Constants.DESCRIPTION);
        articleEntity.setStock(Constants.STOCK);
        articleEntity.setPrice(Constants.PRICE);
        articleEntity.setBrand(TestDataBrand.getBrandEntity());
        articleEntity.setCategories(List.of(TestDataCategory.getCategoryEntity(), TestDataCategory.getCategoryEntity(), TestDataCategory.getCategoryEntity()));

        return articleEntity;
    }

}

