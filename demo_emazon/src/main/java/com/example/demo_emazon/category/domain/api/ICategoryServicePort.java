package com.example.demo_emazon.category.domain.api;

import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.util.pagination.Pagination;

public interface ICategoryServicePort {

    Category createCategory(Category category);
    Pagination<Category> listCategory(int number, int size, String sortDirection);

}
