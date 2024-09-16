package com.example.demo_emazon.article.domain.usecase;

import com.example.demo_emazon.article.domain.api.IArticleServicePort;
import com.example.demo_emazon.article.domain.exception.BrandNotFoundException;
import com.example.demo_emazon.article.domain.exception.CategoryNotFoundException;
import com.example.demo_emazon.article.domain.exception.MaximumRelatedCategoriesException;
import com.example.demo_emazon.article.domain.exception.MinimumOfRelatedCategoriesException;
import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.article.domain.spi.IArticlePersistencePort;
import com.example.demo_emazon.article.domain.util.ConstantsArticle;
import com.example.demo_emazon.article.domain.util.ExceptionConstantsArticle;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.domain.spi.IBrandPersistencePort;
import com.example.demo_emazon.category.domain.exception.CategoryAlreadyExistException;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.spi.ICategoryPersistencePort;
import com.example.demo_emazon.category.domain.util.constants.ExceptionConstantsCategory;
import com.example.demo_emazon.util.pagination.Pagination;

import java.util.*;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort, ICategoryPersistencePort categoryPersistencePort, IBrandPersistencePort brandPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public Article createArticle(Article article) {

        if (brandPersistencePort.findByName(article.getBrand().getNameBrand()) == null){
            throw new BrandNotFoundException(ExceptionConstantsArticle.BRAND_NOT_FOUND.getMessage(), article.getBrand().getNameBrand());
        }

        if (article.getCategories().isEmpty() || article.getCategories() == null){
            throw new MinimumOfRelatedCategoriesException(ExceptionConstantsArticle.MINIUM_OF_RELATED_CATEGORIES.getMessage());
        } else if (article.getCategories().size() > ConstantsArticle.MAX_CATEGORIES){
            throw new MaximumRelatedCategoriesException(ExceptionConstantsArticle.MAXIUM_RELATED_CATEGORIES.getMessage());
        } else {
            article.getCategories()
                    .forEach(category -> {
                        if (categoryPersistencePort.findByName(category.getNameCategory()) == null) {
                            throw new CategoryNotFoundException(ExceptionConstantsArticle.CATEGORY_NOT_FOUND.getMessage(), category.getNameCategory());
                        }
                    });
        }

        return articlePersistencePort.save(article);
    }

    @Override
    public Brand findByNameBrand(String brandName) {
        Brand brand = brandPersistencePort.findByName(brandName);
        if (brand == null){
            throw new BrandNotFoundException(ExceptionConstantsArticle.BRAND_NOT_FOUND.getMessage(), brandName);
        }

        return brand;
    }

    @Override
    public List<Category> findByNamesCategory(List<String> categoryNames) {

        Set<String> categoryNamesReturn = new HashSet<>();

        return categoryNames.stream()
                .map(name -> {
                    if (!categoryNamesReturn.add(name)) {
                        throw new CategoryAlreadyExistException(ExceptionConstantsCategory.CATEGORY_ALREADY_EXIST.getMessage(), name);
                    }
                    Category category = categoryPersistencePort.findByName(name);
                    if (category == null) {
                        throw new CategoryNotFoundException(ExceptionConstantsArticle.CATEGORY_NOT_FOUND.getMessage(), name);
                    }
                    return category;
                })
                .toList();
    }

    @Override
    public Pagination<Article> listArticles(int number, int size, String sortDirection, String model) {
        List<Article> allArticles = new ArrayList<>(articlePersistencePort.findAll());
        Comparator<Article> comparator = Comparator.comparing(Article::getNameArticle);

        if ("article".equalsIgnoreCase(model)){
            comparator = Comparator.comparing(Article::getNameArticle);
        } else if ("brand".equalsIgnoreCase(model)){
            comparator = Comparator.comparing(article -> article.getBrand().getNameBrand());
        } else if ("category".equalsIgnoreCase(model)){
            comparator = Comparator.comparing(article -> article.getCategories().get(0).getNameCategory());
        }

        if ("asc".equalsIgnoreCase(sortDirection)){
            allArticles.sort(comparator);
        } else if ("desc".equalsIgnoreCase(sortDirection)){
            allArticles.sort(comparator.reversed());
        }

        int totalElements = allArticles.size();
        int fromIndex = (number-1)*size;
        int toIndex = Math.min(fromIndex + size, totalElements);

        if (fromIndex >= totalElements || fromIndex < 0) {
            return new Pagination<>(Collections.emptyList(), number, size, totalElements);
        }

        List<Article> pageContent = allArticles.subList(fromIndex, toIndex);

        return new Pagination<>(pageContent, number, size, totalElements);
    }
}
