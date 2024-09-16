package com.example.demo_emazon.article.infraestructure.out.jpa.repository;

import com.example.demo_emazon.article.infraestructure.out.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {
}
