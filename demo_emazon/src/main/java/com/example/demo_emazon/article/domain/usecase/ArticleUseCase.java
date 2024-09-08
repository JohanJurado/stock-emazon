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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        findByNameBrand(article.getBrand().getNameBrand());

        if (article.getCategories().isEmpty()){
            throw new MinimumOfRelatedCategoriesException(ExceptionConstantsArticle.MINIUM_OF_RELATED_CATEGORIES.getMessage());
        } else if (article.getCategories().size()> ConstantsArticle.MAX_CATEGORIES){
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
}
