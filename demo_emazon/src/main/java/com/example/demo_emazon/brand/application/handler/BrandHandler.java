package com.example.demo_emazon.brand.application.handler;

import com.example.demo_emazon.brand.application.dto.BrandRequest;
import com.example.demo_emazon.brand.application.dto.BrandResponse;
import com.example.demo_emazon.brand.application.mapper.IBrandMapper;
import com.example.demo_emazon.brand.domain.api.IBrandServicePort;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.util.pagination.Pagination;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandMapper brandMapper;
    private final IBrandServicePort brandServicePort;

    @Override
    public BrandResponse save(BrandRequest brandRequest) {
        Brand brand = brandMapper.toBrand(brandRequest);
        return brandMapper.toBrandResponse(brandServicePort.createBrand(brand));
    }

    @Override
    public Pagination<BrandResponse> listBrandResponses(int number, int size, String sortDirection) {
        return brandMapper.toResposePagination(
                brandServicePort.listBrand(number, size, sortDirection)
        );
    }


}
