package com.example.demo_emazon.article.infraestructure.out.jpa.mapper;

import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.article.infraestructure.out.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IArticleEntityMapper {

    @Mapping(target = "idArticle", source="idArticle")
    @Mapping(target = "nameArticle", source="nameArticle")
    @Mapping(target = "descriptionArticle", source="descriptionArticle")
    @Mapping(target = "stock", source="stock")
    @Mapping(target = "price", source="price")
    @Mapping(target = "brand", source="brand")
    @Mapping(target = "categories", source="categories")
    Article toArticle(ArticleEntity articleEntity);

    @Mapping(target = "idArticle", source="idArticle")
    @Mapping(target = "nameArticle", source="nameArticle")
    @Mapping(target = "descriptionArticle", source="descriptionArticle")
    @Mapping(target = "stock", source="stock")
    @Mapping(target = "price", source="price")
    @Mapping(target = "brand", source="brand")
    @Mapping(target = "categories", source="categories")
    ArticleEntity toArticleEntity(Article article);

}
