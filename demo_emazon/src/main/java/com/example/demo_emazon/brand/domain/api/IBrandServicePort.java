package com.example.demo_emazon.brand.domain.api;

import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.domain.util.Pagination;

public interface IBrandServicePort {

    Brand createBrand(Brand brand);
    Pagination<Brand> listBrand(int number, int size, String sortDirection);

}
