package com.example.demo_emazon.brand.application.exceptiohandler;

import com.example.demo_emazon.brand.domain.exception.BrandAlreadyExistException;
import com.example.demo_emazon.brand.domain.exception.MaxiumDescriptionSizeExceededException;
import com.example.demo_emazon.brand.domain.exception.MaxiumNameSizeExceededException;
import com.example.demo_emazon.brand.domain.exception.TheNameCannotBeEmpty;
import com.example.demo_emazon.brand.domain.exception.TheDescriptionCannotBeEmpty;
import com.example.demo_emazon.brand.domain.util.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisorBrand {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(BrandAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExistsException(
            BrandAlreadyExistException brandAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, brandAlreadyExistException.getMessage()+" '"+brandAlreadyExistException.getNameBrand()+"'"));
    }

    @ExceptionHandler(MaxiumNameSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxiumNameSizeExceededException(
            MaxiumNameSizeExceededException maxiumNameSizeExceededException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, maxiumNameSizeExceededException.getMessage()+" ("+maxiumNameSizeExceededException.getSizeNameBrand()+" > 50)"));
    }

    @ExceptionHandler(MaxiumDescriptionSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxiumDescriptionSizeExceededException(
            MaxiumDescriptionSizeExceededException maxiumDescriptionSizeExceededException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, maxiumDescriptionSizeExceededException.getMessage()+" ("+maxiumDescriptionSizeExceededException.getSizeDescriptionBrand()+" > 120)"));
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

