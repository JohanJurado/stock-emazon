package com.example.demo_emazon.article.application.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private Long idArticle;
    private String nameArticle;
    private String descriptionArticle;
    private Integer stock;
    private Double price;

    private String brandName;
    private List<String> categoryNames;

}
