package com.example.demo_emazon.testdata.exceptioncategory;

public class TheDescriptionCannotBeEmpty extends RuntimeException {
    public TheDescriptionCannotBeEmpty(String message) {
        super(message);
    }
}
