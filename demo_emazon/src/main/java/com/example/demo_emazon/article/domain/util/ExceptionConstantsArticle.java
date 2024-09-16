package com.example.demo_emazon.article.domain.util;

public enum ExceptionConstantsArticle {

    BRAND_NOT_FOUND("Brand not found: "),
    CATEGORY_NOT_FOUND("Category not found"),
    MAXIUM_RELATED_CATEGORIES("The number of categories has been exceeded (Max 3)"),
    MINIUM_OF_RELATED_CATEGORIES("The categories field is empty, at least one category must be included");

    private final String message;

    ExceptionConstantsArticle(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}
