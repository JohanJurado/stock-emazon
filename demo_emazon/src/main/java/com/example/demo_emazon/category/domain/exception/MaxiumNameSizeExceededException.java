package com.example.demo_emazon.category.domain.exception;

public class MaxiumNameSizeExceededException extends RuntimeException {

    private final int sizeNameCategory;

    public MaxiumNameSizeExceededException(String message, int sizeNameCategory) {
        super(message);
        this.sizeNameCategory = sizeNameCategory;
    }

    public int getSizeNameCategory(){
        return sizeNameCategory;
    }

}
