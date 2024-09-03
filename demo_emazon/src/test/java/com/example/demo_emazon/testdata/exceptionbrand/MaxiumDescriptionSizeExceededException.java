package com.example.demo_emazon.testdata.exceptionbrand;

public class MaxiumDescriptionSizeExceededException extends RuntimeException {

    private final int sizeDescriptionBrand;

    public MaxiumDescriptionSizeExceededException(String message, int sizeDescriptionBrand) {
        super(message);
        this.sizeDescriptionBrand = sizeDescriptionBrand;
    }

    public int getSizeDescriptionBrand(){
        return sizeDescriptionBrand;
    }

}
