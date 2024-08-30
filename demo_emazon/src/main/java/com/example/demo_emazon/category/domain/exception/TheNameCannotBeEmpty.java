package com.example.demo_emazon.category.domain.exception;

public class TheNameCannotBeEmpty extends RuntimeException {
    public TheNameCannotBeEmpty(String message) {
        super(message);
    }
}
