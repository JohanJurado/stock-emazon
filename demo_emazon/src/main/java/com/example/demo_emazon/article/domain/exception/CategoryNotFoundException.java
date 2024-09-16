package com.example.demo_emazon.article.domain.exception;

public class CategoryNotFoundException extends RuntimeException {

    private final String nameCategory;

    public CategoryNotFoundException(String message, String nameCategory){
        super(message);
        this.nameCategory = nameCategory;
    }

    public String getNameBrand(){
        return nameCategory;
    }

}
