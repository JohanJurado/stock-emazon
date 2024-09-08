package com.example.demo_emazon.brand.application.handler;

import com.example.demo_emazon.brand.application.dto.BrandRequest;
import com.example.demo_emazon.brand.application.dto.BrandResponse;
import com.example.demo_emazon.brand.application.mapper.IBrandMapper;
import com.example.demo_emazon.brand.domain.api.IBrandServicePort;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.util.TestData.TestDataBrand;
import com.example.demo_emazon.util.pagination.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandHandlerTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandMapper brandMapper;

    @InjectMocks
    private BrandHandler brandHandler;

    @Test
    @DisplayName("Create a New Brand correctly")
    void testSave() {
        BrandRequest brandRequest = TestDataBrand.getBrandRequest();
        Brand brand = TestDataBrand.getBrand();
        Brand savedBrand = TestDataBrand.getBrand();
        BrandResponse expectedResponse = TestDataBrand.getBrandResponse();

        when(brandMapper.toBrand(brandRequest)).thenReturn(brand);
        when(brandServicePort.createBrand(brand)).thenReturn(savedBrand);
        when(brandMapper.toBrandResponse(savedBrand)).thenReturn(expectedResponse);

        BrandResponse actualResponse = brandHandler.save(brandRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(brandMapper).toBrand(brandRequest);
        verify(brandServicePort).createBrand(brand);
        verify(brandMapper).toBrandResponse(savedBrand);
    }

    @Test
    @DisplayName("Test List Brands Responses")
    void testListBrandResponses() {
        int pageNumber = 1;
        int pageSize = 10;
        String sortDirection = "asc";

        List<Brand> content = null;
        List<BrandResponse> contentResponse = null;
        int totalElements = 20;

        Pagination<Brand> brandPagination =
                new Pagination<>(content, pageNumber, pageSize, totalElements);
        Pagination<BrandResponse> expectedResponsePagination =
                new Pagination<>(contentResponse, pageNumber, pageSize, totalElements);

        when(brandServicePort.listBrand(pageNumber, pageSize, sortDirection))
                .thenReturn(brandPagination);

        when(brandMapper.toResposePagination(brandPagination))
                .thenReturn(expectedResponsePagination);

        Pagination<BrandResponse> actualResponsePagination =
                brandHandler.listBrandResponses(pageNumber, pageSize, sortDirection);

        assertEquals(expectedResponsePagination, actualResponsePagination);
        verify(brandServicePort).listBrand(pageNumber, pageSize, sortDirection);
        verify(brandMapper).toResposePagination(brandPagination);
    }
}