package com.example.demo_emazon.testdata.exceptioncategory;

public class TheNameCannotBeEmpty extends RuntimeException {
    public TheNameCannotBeEmpty(String message) {
        super(message);
    }
}
