package com.example.demo_emazon.brand.infraestructure.out.jpa.adapter;

import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.infraestructure.out.jpa.entity.BrandEntity;
import com.example.demo_emazon.brand.infraestructure.out.jpa.mapper.IBrandEntityMapper;
import com.example.demo_emazon.brand.infraestructure.out.jpa.repository.IBrandJpaRepository;
import com.example.demo_emazon.util.Constants;
import com.example.demo_emazon.util.TestData.TestDataBrand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandJpaAdapterTest {

    @Mock
    private IBrandJpaRepository brandJpaRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;


    @Test
    @DisplayName("Test Save Brand")
    void testSaveBrand() {
        Brand brand = TestDataBrand.getBrand();
        BrandEntity brandEntity = TestDataBrand.getBrandEntity();

        when(brandEntityMapper.toEntity(any(Brand.class))).thenReturn(brandEntity);
        when(brandJpaRepository.save(any(BrandEntity.class))).thenReturn(brandEntity);
        when(brandEntityMapper.toBrand(any(BrandEntity.class))).thenReturn(brand);

        Brand result = brandJpaAdapter.save(brand);

        assertEquals(brand, result);
        verify(brandEntityMapper).toEntity(brand);
        verify(brandJpaRepository).save(brandEntity);
        verify(brandEntityMapper).toBrand(brandEntity);
    }

    @Test
    @DisplayName("Test Find All Responses")
    void testFindAllBrands() {
        BrandEntity brandEntity1 = TestDataBrand.getBrandEntity();
        BrandEntity brandEntity2 = TestDataBrand.getBrandEntity();
        Brand brand1 = TestDataBrand.getBrand();
        Brand brand2 = TestDataBrand.getBrand();

        when(brandJpaRepository.findAll()).thenReturn(List.of(brandEntity1, brandEntity2));
        when(brandEntityMapper.toBrand(brandEntity1)).thenReturn(brand1);
        when(brandEntityMapper.toBrand(brandEntity2)).thenReturn(brand2);

        List<Brand> result = brandJpaAdapter.findAll();

        assertEquals(2, result.size());
        assertEquals(brand1, result.get(0));
        assertEquals(brand2, result.get(1));
        verify(brandJpaRepository).findAll();
        verify(brandEntityMapper).toBrand(brandEntity1);
        verify(brandEntityMapper).toBrand(brandEntity2);
    }

    @Test
    @DisplayName("Test Find Brand with a name")
    void testFindBrandByName() {
        String name = Constants.NAME;
        BrandEntity brandEntity = TestDataBrand.getBrandEntity();
        Brand brand = TestDataBrand.getBrand();

        when(brandJpaRepository.findByNameBrand(name)).thenReturn(Optional.of(brandEntity));
        when(brandEntityMapper.toBrand(brandEntity)).thenReturn(brand);

        Brand result = brandJpaAdapter.findByName(name);

        assertEquals(brand, result);
        verify(brandJpaRepository).findByNameBrand(name);
        verify(brandEntityMapper).toBrand(brandEntity);
    }

}