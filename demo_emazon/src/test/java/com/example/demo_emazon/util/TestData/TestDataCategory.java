package com.example.demo_emazon.util.TestData;

import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.application.dto.CategoryResponse;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.infraestructure.out.jpa.entity.CategoryEntity;
import com.example.demo_emazon.util.Constants;

import java.util.Arrays;
import java.util.List;

public class TestDataCategory {

    public static Category getCategory(){
        Category category = new Category();
        category.setIdCategory(Constants.ID);
        category.setNameCategory(Constants.NAME);
        category.setDescriptionCategory(Constants.DESCRIPTION);
        return category;
    }

    public static List<Category> getListCategories(){
        return Arrays.asList(Constants.CATEGORY1, Constants.CATEGORY2, Constants.CATEGORY3);
    }

    public static CategoryResponse getCategoryResponse(){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setIdCategory(Constants.ID);
        categoryResponse.setNameCategory(Constants.NAME);
        categoryResponse.setDescriptionCategory(Constants.DESCRIPTION);
        return categoryResponse;
    }

    public static CategoryRequest getCategoryRequest(){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setNameCategory(Constants.NAME);
        categoryRequest.setDescriptionCategory(Constants.DESCRIPTION);
        return categoryRequest;
    }

    public static CategoryEntity getCategoryEntity(){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setNameCategory(Constants.NAME);
        categoryEntity.setDescriptionCategory(Constants.DESCRIPTION);
        return categoryEntity;
    }

}
