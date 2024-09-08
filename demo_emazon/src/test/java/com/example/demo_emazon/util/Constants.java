package com.example.demo_emazon.util;

import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.category.domain.model.Category;

public final class Constants {

    private Constants(){
    }

    public static final Long ID = 20L;
    public static final String NAME = "Object Name";
    public static final String DESCRIPTION = "Object description";

    public static final Category CATEGORY1 = new Category(1L, "Books", "Books category");
    public static final Category CATEGORY2 = new Category(2L, "Electronics", "Electronics category");
    public static final Category CATEGORY3 = new Category(3L, "Furniture", "Furniture category");

    public static final Brand BRAND1 = new Brand(1L, "Norma", "Norma Description");
    public static final Brand BRAND2 = new Brand(2L, "LG", "LG Description");
    public static final Brand BRAND3 = new Brand(3L, "Motorola", "Motorola Description");

    public static final String MAX_NAME = "123451234512345123451234512345123451234512345123451234512345";
    public static final String MAX_DESCRIPTION_CATEGORY = "123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345";
    public static final String MAX_DESCRIPTION_BRAND = "12345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234523451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123";
    public static final String EMPTY = "";
    public static final String NULL = null;

}
