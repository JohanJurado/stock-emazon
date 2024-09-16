package com.example.demo_emazon.article.application.dto;

import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.category.domain.model.Category;
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

    private Brand brand;
    private List<Category> categories;

}
