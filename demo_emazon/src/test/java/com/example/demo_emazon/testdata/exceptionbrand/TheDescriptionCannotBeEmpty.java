package com.example.demo_emazon.testdata.exceptionbrand;

public class TheDescriptionCannotBeEmpty extends RuntimeException {
    public TheDescriptionCannotBeEmpty(String message) {
        super(message);
    }
}
