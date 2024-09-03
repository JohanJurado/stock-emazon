package com.example.demo_emazon.testdata.exceptionbrand;

public class MaxiumNameSizeExceededException extends RuntimeException {

    private final int sizeNameBrand;

    public MaxiumNameSizeExceededException(String message, int sizeNameBrand) {
        super(message);
        this.sizeNameBrand = sizeNameBrand;
    }

    public int getSizeNameBrand(){
        return sizeNameBrand;
    }

}
