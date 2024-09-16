package com.example.demo_emazon.brand.infraestructure.input.rest;

import com.example.demo_emazon.brand.application.dto.BrandRequest;
import com.example.demo_emazon.brand.application.dto.BrandResponse;
import com.example.demo_emazon.brand.application.handler.IBrandHandler;
import com.example.demo_emazon.util.Constants;
import com.example.demo_emazon.util.TestData.TestDataBrand;
import com.example.demo_emazon.util.pagination.Pagination;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandControllerTest {

    @Mock
    private IBrandHandler brandHandler;

    @InjectMocks
    private BrandController brandController;

    @Test
    void testCreateBrand() {
        BrandRequest brandRequest = TestDataBrand.getBrandRequest();
        BrandResponse brandResponse = TestDataBrand.getBrandResponse();

        when(brandHandler.save(any(BrandRequest.class))).thenReturn(brandResponse);

        ResponseEntity<BrandResponse> responseEntity = brandController.createBrand(brandRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(brandResponse, responseEntity.getBody());
        verify(brandHandler).save(brandRequest);
    }

    @Test
    void testListBrands() {
        int page = Constants.PAGE_IN_RANGE;
        int size = Constants.PAGE_SIZE_1;
        String sortDirection = Constants.SORT_ASC;

        BrandResponse brandResponse = TestDataBrand.getBrandResponse();
        Pagination<BrandResponse> pagination = new Pagination<>(List.of(brandResponse), page, size, Constants.PAGE_SIZE_1);

        when(brandHandler.listBrandResponses(page, size, sortDirection)).thenReturn(pagination);

        ResponseEntity<Pagination<BrandResponse>> responseEntity = brandController.listBrands(page, size, sortDirection);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pagination, responseEntity.getBody());
        verify(brandHandler).listBrandResponses(page, size, sortDirection);
    }

}