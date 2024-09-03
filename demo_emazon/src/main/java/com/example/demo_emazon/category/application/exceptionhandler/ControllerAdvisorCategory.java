package com.example.demo_emazon.category.application.exceptionhandler;

import com.example.demo_emazon.category.domain.exception.TheDescriptionCannotBeEmpty;
import com.example.demo_emazon.category.domain.exception.TheNameCannotBeEmpty;
import com.example.demo_emazon.category.domain.exception.MaxiumNameSizeExceededException;
import com.example.demo_emazon.category.domain.exception.MaxiumDescriptionSizeExceededException;
import com.example.demo_emazon.category.domain.exception.CategoryAlreadyExistException;
import com.example.demo_emazon.category.domain.util.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisorCategory {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            CategoryAlreadyExistException categoryAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, categoryAlreadyExistException.getMessage()+" '"+categoryAlreadyExistException.getNameCategory()+"'"));
    }

    @ExceptionHandler(MaxiumNameSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxiumNameSizeExceededException(
            MaxiumNameSizeExceededException maxiumNameSizeExceededException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, maxiumNameSizeExceededException.getMessage()+" ("+maxiumNameSizeExceededException.getSizeNameCategory()+" > 50)"));
    }

    @ExceptionHandler(MaxiumDescriptionSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxiumDescriptionSizeExceededException(
            MaxiumDescriptionSizeExceededException maxiumDescriptionSizeExceededException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, maxiumDescriptionSizeExceededException.getMessage()+" ("+maxiumDescriptionSizeExceededException.getSizeDescriptionCategory()+" > 90)"));
    }

    @ExceptionHandler(TheNameCannotBeEmpty.class)
    public ResponseEntity<Map<String, String>> handleTheNameCannotBeEmpty(
            TheNameCannotBeEmpty theNameCannotBeEmpty) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionConstants.THE_NAME_CANNOT_BE_EMPTY.getMessage()));
    }

    @ExceptionHandler(TheDescriptionCannotBeEmpty.class)
    public ResponseEntity<Map<String, String>> handleTheDescriptionCannotBeEmpty(
            TheDescriptionCannotBeEmpty theDescriptionCannotBeEmpty) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionConstants.THE_DESCRIPTION_CANNOT_BE_EMPTY.getMessage()));
    }

}
