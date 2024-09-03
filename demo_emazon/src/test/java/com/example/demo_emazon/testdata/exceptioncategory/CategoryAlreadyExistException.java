package com.example.demo_emazon.testdata.exceptioncategory;

public class CategoryAlreadyExistException extends RuntimeException {

    private final String nameCategory;

    public CategoryAlreadyExistException(String message, String nameCategory){
        super(message);
        this.nameCategory = nameCategory;
    }

    public String getNameCategory(){
        return nameCategory;
    }

}
