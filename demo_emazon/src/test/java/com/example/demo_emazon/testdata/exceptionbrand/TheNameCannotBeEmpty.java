package com.example.demo_emazon.testdata.exceptionbrand;

public class TheNameCannotBeEmpty extends RuntimeException {
    public TheNameCannotBeEmpty(String message) {
        super(message);
    }
}
