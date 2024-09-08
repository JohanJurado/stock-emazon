package com.example.demo_emazon.category.domain.usecase;

import com.example.demo_emazon.category.domain.api.ICategoryServicePort;
import com.example.demo_emazon.category.domain.exception.*;
import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.domain.spi.ICategoryPersistencePort;
import com.example.demo_emazon.category.domain.util.constants.ConstantsCategory;
import com.example.demo_emazon.category.domain.util.constants.ExceptionConstantsCategory;
import com.example.demo_emazon.util.pagination.Pagination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }


    @Override
    public Category createCategory(Category category) {
        if (category.getNameCategory()==null || category.getNameCategory().trim().isEmpty()){
            throw new TheNameCannotBeEmpty(ExceptionConstantsCategory.THE_NAME_CANNOT_BE_EMPTY.getMessage());
        } else if (categoryPersistencePort.findByName(category.getNameCategory())!=null){
            throw new CategoryAlreadyExistException(ExceptionConstantsCategory.CATEGORY_ALREADY_EXIST.getMessage(), category.getNameCategory());
        } else if (category.getNameCategory().length() > ConstantsCategory.MAX_NUM){
            throw new MaxiumNameSizeExceededException(ExceptionConstantsCategory.MAXIUM_NAME_SIZE_EXCEEDED.getMessage(), category.getNameCategory().length());
        }

        if (category.getDescriptionCategory()==null || category.getDescriptionCategory().trim().isEmpty()){
            throw new TheDescriptionCannotBeEmpty(ExceptionConstantsCategory.THE_DESCRIPTION_CANNOT_BE_EMPTY.getMessage());
        } else if (category.getDescriptionCategory().length() > ConstantsCategory.MAX_DESCRIPTION){
            throw new MaxiumDescriptionSizeExceededException(ExceptionConstantsCategory.MAXIUM_DESCRIPTION_SIZE_EXCEEDED.getMessage(), category.getDescriptionCategory().length());
        }

        category.setNameCategory(category.getNameCategory().trim());
        category.setDescriptionCategory(category.getDescriptionCategory().trim());
        return categoryPersistencePort.save(category);
    }

    @Override
    public Pagination<Category> listCategory(int number, int size, String sortDirection) {
        List<Category> allCategories = new ArrayList<>(categoryPersistencePort.findAll());

        if ("asc".equalsIgnoreCase(sortDirection)){
            allCategories.sort(Comparator.comparing(Category::getNameCategory));
        } else if ("desc".equalsIgnoreCase(sortDirection)){
            allCategories.sort(Comparator.comparing(Category::getNameCategory).reversed());
        }

        int totalElements = allCategories.size();
        int fromIndex = (number-1)*size;
        int toIndex = Math.min(fromIndex + size, totalElements);

        if (fromIndex >= totalElements || fromIndex <0) {
            return new Pagination<>(Collections.emptyList(), number, size, totalElements);
        }

        List<Category> pageContent = allCategories.subList(fromIndex, toIndex);

        return new Pagination<>(pageContent, number, size, totalElements);
    }
}
