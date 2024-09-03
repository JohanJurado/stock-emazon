package com.example.demo_emazon.brand.domain.util.constants;

public enum ExceptionConstants {

    BRAND_ALREADY_EXIST("The name of category already exist:"),
    MAXIUM_NAME_SIZE_EXCEEDED("The name has exceeded the maximum number allowed"),
    MAXIUM_DESCRIPTION_SIZE_EXCEEDED("The description has exceeded the maximum number allowed"),
    THE_NAME_CANNOT_BE_EMPTY("the name cannot be empty"),
    THE_DESCRIPTION_CANNOT_BE_EMPTY("the description cannot be empty");

    private final String message;

    ExceptionConstants(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }



}
