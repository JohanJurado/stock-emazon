package com.example.demo_emazon.article.infraestructure.out.jpa.entity;

import com.example.demo_emazon.brand.infraestructure.out.jpa.entity.BrandEntity;
import com.example.demo_emazon.category.infraestructure.out.jpa.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article")
    private Long idArticle;

    @Column(name = "name_article")
    private String nameArticle;

    @Column(name = "description_article")
    private String descriptionArticle;

    @Column(name = "stock_article")
    private Integer stock;

    @Column(name = "price_article")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToMany
    @JoinTable(
            name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories;

}
