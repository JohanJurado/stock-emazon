package com.example.demo_emazon.testdata;

import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.domain.model.Category;

public class TestData {

    public static final Long ID = 20L;
    public static final String NAME = "Category";
    public static final String DESCRIPTION = "Category description";

    public static Category getCategory(){
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        category.setDescription(DESCRIPTION);
        return category;
    }

    public static CategoryRequest getCategoryRequest(){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName(NAME);
        categoryRequest.setDescription(DESCRIPTION);
        return categoryRequest;
    }

}
