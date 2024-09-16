package com.example.demo_emazon.article.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

    private String nameArticle;
    private String descriptionArticle;
    private Integer stock;
    private Double price;

    private String brandName;
    private List<String> categoryNames;

}
