package com.example.demo_emazon.brand.infraestructure.input.rest;

import com.example.demo_emazon.brand.application.dto.BrandRequest;
import com.example.demo_emazon.brand.application.dto.BrandResponse;
import com.example.demo_emazon.brand.application.handler.IBrandHandler;
import com.example.demo_emazon.brand.domain.util.Pagination;
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
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandHandler brandHandler;

    @Operation(summary = "Add a new Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "1 Brand added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid arguments supplied",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest){
        BrandResponse brandResponse = brandHandler.save(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandResponse);
    }

    @Operation(summary = "Get the Brands with their pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Brands with pagination",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
    })
    @GetMapping
    public ResponseEntity<Pagination<BrandResponse>> listBrands(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortDirection){

        Pagination<BrandResponse> brandResponsePagination = brandHandler.listBrandResponses(page, size, sortDirection);
        return ResponseEntity.ok(brandResponsePagination);
    }

}
