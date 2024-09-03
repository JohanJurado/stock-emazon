package com.example.demo_emazon.brand.domain.exception;

public class TheNameCannotBeEmpty extends RuntimeException {
    public TheNameCannotBeEmpty(String message) {
        super(message);
    }
}
