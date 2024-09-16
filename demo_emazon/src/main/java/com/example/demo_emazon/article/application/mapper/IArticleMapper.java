package com.example.demo_emazon.article.application.mapper;

import com.example.demo_emazon.article.application.dto.ArticleRequest;
import com.example.demo_emazon.article.application.dto.ArticleResponse;
import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.util.pagination.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "categories", target = "categories")
    ArticleResponse toResponse(Article article);

    Pagination<ArticleResponse> toResposePagination(Pagination<Article> article);



}
