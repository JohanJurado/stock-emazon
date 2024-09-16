package com.example.demo_emazon.article.domain.exception;

public class MaximumRelatedCategoriesException extends RuntimeException {
    public MaximumRelatedCategoriesException(String message) {
        super(message);
    }
}
