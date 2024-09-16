package com.example.demo_emazon.article.application.exceptionhandler;

import com.example.demo_emazon.article.domain.exception.BrandNotFoundException;
import com.example.demo_emazon.article.domain.exception.CategoryNotFoundException;
import com.example.demo_emazon.article.domain.exception.MaximumRelatedCategoriesException;
import com.example.demo_emazon.article.domain.exception.MinimumOfRelatedCategoriesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice

public class ControllerAdvisorArticle {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBrandNotFound(
            BrandNotFoundException brandNotFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, brandNotFoundException.getMessage()+" '"+ brandNotFoundException.getNameBrand()+"'"));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFound(
            CategoryNotFoundException categoryNotFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, categoryNotFoundException.getMessage()+" '"+ categoryNotFoundException.getNameBrand()+"'"));
    }

    @ExceptionHandler(MaximumRelatedCategoriesException.class)
    public ResponseEntity<Map<String, String>> handleMaximumRelatedCategories(
            MaximumRelatedCategoriesException maximumRelatedCategoriesException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, maximumRelatedCategoriesException.getMessage()));
    }

    @ExceptionHandler(MinimumOfRelatedCategoriesException.class)
    public ResponseEntity<Map<String, String>> handleMinimumOfRelatedCategories(
            MinimumOfRelatedCategoriesException minimumOfRelatedCategoriesException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, minimumOfRelatedCategoriesException.getMessage()));
    }

}
