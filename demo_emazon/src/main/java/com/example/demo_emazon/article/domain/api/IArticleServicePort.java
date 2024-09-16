package com.example.demo_emazon.article.domain.api;

import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.category.domain.model.Category;

import java.util.List;

public interface IArticleServicePort {

    Article createArticle(Article article);

    Brand findByNameBrand(String brandName);

    List<Category> findByNamesCategory(List<String> categoryNames);

}
