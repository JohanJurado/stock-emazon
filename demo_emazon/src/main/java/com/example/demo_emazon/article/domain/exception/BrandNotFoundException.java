package com.example.demo_emazon.article.domain.exception;

public class BrandNotFoundException extends RuntimeException {

    private final String nameBrand;

    public BrandNotFoundException(String message, String nameBrand){
        super(message);
        this.nameBrand = nameBrand;
    }

    public String getNameBrand(){
        return nameBrand;
    }

}
