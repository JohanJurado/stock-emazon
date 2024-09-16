package com.example.demo_emazon.util;

import com.example.demo_emazon.article.domain.model.Article;
import com.example.demo_emazon.brand.application.dto.BrandResponse;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.util.TestData.TestDataArticle;
import com.example.demo_emazon.util.TestData.TestDataCategory;

import java.util.List;

public final class Constants {

    private Constants(){
    }

    // Categories
    public static final Category CATEGORY1 = new Category(1L, "Books", "Books category");
    public static final Category CATEGORY2 = new Category(2L, "Electronics", "Electronics category");
    public static final Category CATEGORY3 = new Category(3L, "Furniture", "Furniture category");
    public static final Category CATEGORY4 = new Category(4L, "Category4", "Description category4");
    public static final Category CATEGORY_WITHOUT_NAME = new Category(4L, "", "Description category4");

    // Brands
    public static final Brand BRAND1 = new Brand(1L, "Norma", "Norma Description");
    public static final Brand BRAND2 = new Brand(2L, "LG", "LG Description");
    public static final Brand BRAND3 = new Brand(3L, "Motorola", "Motorola Description");

    // Articles
    public static final Article ARTICLE = TestDataArticle.getArticle();

    // Exceptions fields
    public static final String MAX_NAME = "123451234512345123451234512345123451234512345123451234512345";
    public static final String MAX_DESCRIPTION_CATEGORY = "123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345";
    public static final String MAX_DESCRIPTION_BRAND = "12345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234523451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123";
    public static final String EMPTY = "";
    public static final String NULL = null;
    public static final List<Brand> NULL_LIST_BRAND = null;
    public static final List<BrandResponse> NULL_LIST_BRANDRESPONSE = null;

    // Pagination arguments
    public static final String SORT_ASC = "asc";
    public static final String SORT_DESC = "desc";
    public static final Integer PAGE_OUT_OF_RANGE = 4;
    public static final Integer PAGE_IN_RANGE = 1;
    public static final Integer PAGE_0 = 0;
    public static final Integer PAGE_SIZE_2 = 2;
    public static final Integer PAGE_SIZE_1 = 1;

    // Properties Brand, Category and Article
    public static final Long ID = 20L;
    public static final String NAME = "Object Name";
    public static final String DESCRIPTION = "Object description";

    // Other Properties of Article
    public static final Integer STOCK = 15;
    public static final Double PRICE = 2000d;
    public static final String NAME_BRAND = "Motorola";
    public static final List<String> CATEGORY_NAMES = List.of("Category1", "Category2", "Category3");
    public static final List<Category> CATEGORIES = List.of(CATEGORY1, CATEGORY2, CATEGORY3);
    public static final List<Category> CATEGORIES_OUT_RANGE = List.of(CATEGORY1, CATEGORY2, CATEGORY3, CATEGORY4);


}
