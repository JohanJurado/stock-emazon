package com.example.demo_emazon.category.infraestructure.input.rest;

import com.example.demo_emazon.category.application.dto.CategoryRequest;
import com.example.demo_emazon.category.application.dto.CategoryResponse;
import com.example.demo_emazon.category.application.handler.ICategoryHandler;
import com.example.demo_emazon.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Add a new Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "1 Category added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid arguments supplied",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryHandler.save(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @Operation(summary = "Get the categories with their pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Categories with pagination",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
    })
    @GetMapping
    public ResponseEntity<Pagination<CategoryResponse>> listCategories(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortDirection){

        Pagination<CategoryResponse> listCategoryResponses = categoryHandler.listCategoryResponses(page, size, sortDirection);
        return ResponseEntity.ok(listCategoryResponses);
    }

}
