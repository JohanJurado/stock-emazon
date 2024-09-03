package com.example.demo_emazon.testdata.TestData;

import com.example.demo_emazon.brand.application.dto.BrandRequest;
import com.example.demo_emazon.brand.application.dto.BrandResponse;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.infraestructure.out.jpa.entity.BrandEntity;
import com.example.demo_emazon.testdata.Constants;

import java.util.Arrays;
import java.util.List;

public class TestDataBrand {

    public static Brand getBrand(){
        Brand brand = new Brand();
        brand.setIdBrand(Constants.ID);
        brand.setNameBrand(Constants.NAME);
        brand.setDescriptionBrand(Constants.DESCRIPTION);
        return brand;
    }

    public static List<Brand> getListBrands(){
        return Arrays.asList(Constants.BRAND1, Constants.BRAND2, Constants.BRAND3);
    }

    public static BrandResponse getBrandResponse(){
        BrandResponse brandResponse = new BrandResponse();
        brandResponse.setIdBrand(Constants.ID);
        brandResponse.setNameBrand(Constants.NAME);
        brandResponse.setDescriptionBrand(Constants.DESCRIPTION);
        return brandResponse;
    }

    public static BrandRequest getBrandRequest(){
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setNameBrand(Constants.NAME);
        brandRequest.setDescriptionBrand(Constants.DESCRIPTION);
        return brandRequest;
    }

    public static BrandEntity getBrandEntity(){
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setNameBrand(Constants.NAME);
        brandEntity.setDescriptionBrand(Constants.DESCRIPTION);
        return brandEntity;
    }

}

