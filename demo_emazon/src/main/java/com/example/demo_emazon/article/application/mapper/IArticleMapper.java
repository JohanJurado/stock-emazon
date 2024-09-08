package com.example.demo_emazon.article.application.mapper;

import com.example.demo_emazon.article.application.dto.ArticleRequest;
import com.example.demo_emazon.article.application.dto.ArticleResponse;
import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.category.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticleMapper {

    @Mapping(target = "idArticle", ignore = true)
    @Mapping(source = "nameArticle", target="nameArticle")
    @Mapping(source = "descriptionArticle", target="descriptionArticle")
    @Mapping(source = "stock", target="stock")
    @Mapping(source = "price", target="price")
    @Mapping(target = "brand.idBrand", ignore = true)
    @Mapping(source = "brandName", target = "brand.nameBrand")
    @Mapping(target = "brand.descriptionBrand", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Article toArticle(ArticleRequest articleRequest);

    @Mapping(source = "idArticle", target = "idArticle")
    @Mapping(source = "nameArticle", target="nameArticle")
    @Mapping(source = "descriptionArticle", target="descriptionArticle")
    @Mapping(source = "stock", target="stock")
    @Mapping(source = "price", target="price")
    @Mapping(source = "brand.nameBrand", target = "brandName")
    @Mapping(expression = "java(getCategoryNames(article))", target = "categoryNames")
    ArticleResponse toResponse(Article article);

    // Helper method to get category names
    default List<String> getCategoryNames(Article article) {
        return article.getCategories().stream()
                .map(Category::getNameCategory)
                .toList();
    }

}
