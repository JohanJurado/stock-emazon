package com.example.demo_emazon.testdata.exceptioncategory;

public class MaxiumDescriptionSizeExceededException extends RuntimeException {

    private final int sizeDescriptionCategory;

    public MaxiumDescriptionSizeExceededException(String message, int sizeDescriptionCategory) {
        super(message);
        this.sizeDescriptionCategory = sizeDescriptionCategory;
    }

    public int getSizeDescriptionCategory(){
        return sizeDescriptionCategory;
    }

}
