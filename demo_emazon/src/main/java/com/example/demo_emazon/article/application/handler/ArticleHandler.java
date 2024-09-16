package com.example.demo_emazon.article.application.handler;

import com.example.demo_emazon.article.application.dto.ArticleRequest;
import com.example.demo_emazon.article.application.dto.ArticleResponse;
import com.example.demo_emazon.article.application.mapper.IArticleMapper;
import com.example.demo_emazon.article.domain.api.IArticleServicePort;
import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.util.pagination.Pagination;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler{

    private final IArticleMapper articleMapper;
    private final IArticleServicePort articleServicePort;

    @Override
    public ArticleResponse createArticle(ArticleRequest articleRequest) {

        Brand brand = articleServicePort.findByNameBrand(articleRequest.getBrandName());

        List<Category> categories = articleServicePort.findByNamesCategory(articleRequest.getCategoryNames());

        Article article = articleMapper.toArticle(articleRequest);
        article.setBrand(brand);
        article.setCategories(categories);

        return articleMapper.toResponse(articleServicePort.createArticle(article));
    }

    @Override
    public Pagination<ArticleResponse> listArticles(int number, int size, String sortDirection, String model) {
        return articleMapper.toResposePagination(
                articleServicePort.listArticles(number, size, sortDirection, model)
        );
    }
}
