package com.example.demo_emazon.brand.domain.exception;

public class BrandAlreadyExistException extends RuntimeException {

    private final String nameBrand;

    public BrandAlreadyExistException(String message, String nameBrand){
        super(message);
        this.nameBrand = nameBrand;
    }

    public String getNameBrand(){
        return nameBrand;
    }

}
