package com.example.demo_emazon.brand.application.handler;

import com.example.demo_emazon.brand.application.dto.BrandRequest;
import com.example.demo_emazon.brand.application.dto.BrandResponse;
import com.example.demo_emazon.util.pagination.Pagination;

public interface IBrandHandler {

    BrandResponse save(BrandRequest brandRequest);
    Pagination<BrandResponse> listBrandResponses(int number, int size, String sortDirection);

}
