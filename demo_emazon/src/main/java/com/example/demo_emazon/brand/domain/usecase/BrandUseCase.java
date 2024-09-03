package com.example.demo_emazon.brand.domain.usecase;

import com.example.demo_emazon.brand.domain.api.IBrandServicePort;
import com.example.demo_emazon.brand.domain.exception.*;
import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.domain.spi.IBrandPersistencePort;
import com.example.demo_emazon.brand.domain.util.Pagination;
import com.example.demo_emazon.brand.domain.util.constants.Constants;
import com.example.demo_emazon.brand.domain.util.constants.ExceptionConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class BrandUseCase implements IBrandServicePort {

    private IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public Brand createBrand(Brand brand) {
        if (brand.getNameBrand()==null || brand.getNameBrand().trim().isEmpty()){
            throw new TheNameCannotBeEmpty(ExceptionConstants.THE_NAME_CANNOT_BE_EMPTY.getMessage());
        } else if (brandPersistencePort.findByName(brand.getNameBrand())!=null){
            throw new BrandAlreadyExistException(ExceptionConstants.BRAND_ALREADY_EXIST.getMessage(), brand.getNameBrand());
        } else if (brand.getNameBrand().length() > Constants.MAX_NUM){
            throw new MaxiumNameSizeExceededException(ExceptionConstants.MAXIUM_NAME_SIZE_EXCEEDED.getMessage(), brand.getNameBrand().length());
        }

        if (brand.getDescriptionBrand()==null || brand.getDescriptionBrand().trim().isEmpty()){
            throw new TheDescriptionCannotBeEmpty(ExceptionConstants.THE_DESCRIPTION_CANNOT_BE_EMPTY.getMessage());
        } else if (brand.getDescriptionBrand().length() > Constants.MAX_DESCRIPTION){
            throw new MaxiumDescriptionSizeExceededException(ExceptionConstants.MAXIUM_DESCRIPTION_SIZE_EXCEEDED.getMessage(), brand.getDescriptionBrand().length());
        }

        brand.setNameBrand(brand.getNameBrand().trim());
        brand.setDescriptionBrand(brand.getDescriptionBrand().trim());
        return brandPersistencePort.save(brand);
    }

    @Override
    public Pagination<Brand> listBrand(int number, int size, String sortDirection) {
        List<Brand> allBrands = new ArrayList<>(brandPersistencePort.findAll());

        if ("asc".equalsIgnoreCase(sortDirection)){
            allBrands.sort(Comparator.comparing(Brand::getNameBrand));
        } else if ("desc".equalsIgnoreCase(sortDirection)){
            allBrands.sort(Comparator.comparing(Brand::getNameBrand).reversed());
        }

        int totalElements = allBrands.size();
        int fromIndex = (number-1)*size;
        int toIndex = Math.min(fromIndex + size, totalElements);

        if (fromIndex >= totalElements || fromIndex <0) {
            return new Pagination<>(Collections.emptyList(), number, size, totalElements);
        }

        List<Brand> pageContent = allBrands.subList(fromIndex, toIndex);

        return new Pagination<>(pageContent, number, size, totalElements);
    }
}