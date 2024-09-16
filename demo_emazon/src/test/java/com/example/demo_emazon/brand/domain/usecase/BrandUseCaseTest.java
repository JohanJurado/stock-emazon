package com.example.demo_emazon.brand.domain.usecase;

import com.example.demo_emazon.brand.domain.model.Brand;
import com.example.demo_emazon.brand.domain.spi.IBrandPersistencePort;
import com.example.demo_emazon.util.Constants;
import com.example.demo_emazon.util.TestData.TestDataBrand;
import com.example.demo_emazon.brand.domain.exception.*;
import com.example.demo_emazon.util.pagination.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;
    @InjectMocks
    private BrandUseCase brandUseCase;

    @Test
    @DisplayName("Create a New Brand correctly")
    void verifyWhenBrandReturnsCorrectCreatedStatus() {
        Brand brand = TestDataBrand.getBrand();
        Mockito.when(brandPersistencePort.save(brand)).thenReturn(brand);
        Mockito.when(brandPersistencePort.findByName(brand.getNameBrand())).thenReturn(null);

        Brand resultado = brandUseCase.createBrand(brand);

        assertEquals(brand, resultado);
        verify(brandPersistencePort, times(1)).save(brand);

    }

    @Test
    @DisplayName("Verify that when entering a name exist, an exception is returned")
    void verifyBrandAlreadyExistException() {
        Brand brand = TestDataBrand.getBrand();

        Mockito.when(brandPersistencePort.findByName(brand.getNameBrand())).
                thenReturn(brand);

        assertThrows(
                BrandAlreadyExistException.class,
                () -> brandUseCase.createBrand(brand),
                "Expected createBrand to throw BrandAlreadyExistException, but it didn't"
        );

        verify(brandPersistencePort, never()).save(any(Brand.class));

    }

    @Test
    @DisplayName("Verify that when entering a name greater than 50 characters, an exception is returned")
    void verifyMaxiumNameSizeExceededException() {
        Brand brand = TestDataBrand.getBrand();
        brand.setNameBrand(Constants.MAX_NAME);

        Mockito.when(brandPersistencePort.findByName(brand.getNameBrand()))
                .thenReturn(null);

        assertThrows(
                MaxiumNameSizeExceededException.class,
                () -> brandUseCase.createBrand(brand),
                "Expected createBrand to throw MaxiumNameSizeExceededException, but it didn't"
        );

        verify(brandPersistencePort, never()).save(any(Brand.class));

    }

    @Test
    @DisplayName("Verify that when entering a description greater than 90 characters, an exception is returned")
    void verifyMaxiumDescriptonSizeExceededException() {
        Brand brand = TestDataBrand.getBrand();
        brand.setDescriptionBrand(Constants.MAX_DESCRIPTION_BRAND);

        Mockito.when(brandPersistencePort.findByName(brand.getNameBrand()))
                .thenReturn(null);

        assertThrows(
                MaxiumDescriptionSizeExceededException.class,
                () -> brandUseCase.createBrand(brand),
                "Expected createBrand to throw MaxiumDescriptionSizeExceededException, but it didn't"
        );

        verify(brandPersistencePort, never()).save(any(Brand.class));

    }

    @Test
    @DisplayName("Verify that when entering a name null, an exception is returned")
    void verifyNameCannotBeEmptyException() {
        Brand brandEmpty = TestDataBrand.getBrand();
        brandEmpty.setNameBrand(Constants.EMPTY);
        Brand brandNull = TestDataBrand.getBrand();
        brandNull.setNameBrand(Constants.NULL);

        assertThrows(
                TheNameCannotBeEmpty.class,
                () -> brandUseCase.createBrand(brandEmpty),
                "Expected createBrand to throw TheNameCannotBeEmpty by empty name, but it didn't"
        );

        assertThrows(
                TheNameCannotBeEmpty.class,
                () -> brandUseCase.createBrand(brandNull),
                "Expected createBrand to throw TheNameCannotBeEmpty by null name, but it didn't"
        );

        verify(brandPersistencePort, never()).save(any(Brand.class));

    }


    @Test
    @DisplayName("Verify that when entering a description null, an exception is returned")
    void verifyDescriptionCannotBeEmptyException() {
        Brand brandEmpty = TestDataBrand.getBrand();
        brandEmpty.setDescriptionBrand(Constants.EMPTY);
        Brand brandNull = TestDataBrand.getBrand();
        brandNull.setDescriptionBrand(Constants.NULL);

        assertThrows(
                TheDescriptionCannotBeEmpty.class,
                () -> brandUseCase.createBrand(brandEmpty),
                "Expected createBrand to throw TheDescriptionCannotBeEmpty by empty description, but it didn't"
        );

        assertThrows(
                TheDescriptionCannotBeEmpty.class,
                () -> brandUseCase.createBrand(brandNull),
                "Expected createBrand to throw TheDescriptionCannotBeEmpty by null description, but it didn't"
        );

        verify(brandPersistencePort, never()).save(any(Brand.class));

    }

    @Test
    void testListBrandAscending() {

        List<Brand> allBrands = TestDataBrand.getListBrands();
        when(brandPersistencePort.findAll()).thenReturn(allBrands);

        Pagination<Brand> pagination = brandUseCase.listBrand(Constants.PAGE_IN_RANGE, Constants.PAGE_SIZE_2, Constants.SORT_ASC);

        assertEquals(Constants.PAGE_SIZE_2, pagination.getContent().size());
        assertEquals(allBrands.get(1).getNameBrand(), pagination.getContent().get(0).getNameBrand());
        assertEquals(allBrands.get(2).getNameBrand(), pagination.getContent().get(1).getNameBrand());
        verify(brandPersistencePort, times(1)).findAll();
    }

    @Test
    void testListBrandDescending() {

        List<Brand> allBrands = TestDataBrand.getListBrands();

        when(brandPersistencePort.findAll()).thenReturn(allBrands);

        Pagination<Brand> pagination = brandUseCase.listBrand(Constants.PAGE_IN_RANGE, Constants.PAGE_SIZE_2, Constants.SORT_DESC);

        assertEquals(Constants.PAGE_SIZE_2, pagination.getContent().size());
        assertEquals(allBrands.get(0).getNameBrand(), pagination.getContent().get(0).getNameBrand());
        assertEquals(allBrands.get(2).getNameBrand(), pagination.getContent().get(1).getNameBrand());
        verify(brandPersistencePort, times(1)).findAll();
    }

    @Test
    void testListCategoryOutOfBounds() {

        List<Brand> allBrands = TestDataBrand.getListBrands();

        when(brandPersistencePort.findAll()).thenReturn(allBrands);

        Pagination<Brand> pagination = brandUseCase.listBrand(Constants.PAGE_OUT_OF_RANGE, Constants.PAGE_SIZE_1, Constants.SORT_ASC);

        assertTrue(pagination.getContent().isEmpty());
        assertEquals(Constants.PAGE_OUT_OF_RANGE, pagination.getPageNumber());
        assertEquals(Constants.PAGE_SIZE_1, pagination.getPageSize());
        assertEquals(allBrands.size(), pagination.getTotalElements());
        verify(brandPersistencePort, times(1)).findAll();
    }

    @Test
    void testListBrandPage0() {

        List<Brand> allBrands = TestDataBrand.getListBrands();

        when(brandPersistencePort.findAll()).thenReturn(allBrands);

        Pagination<Brand> pagination = brandUseCase.listBrand(Constants.PAGE_0, Constants.PAGE_SIZE_1, Constants.SORT_ASC);

        assertTrue(pagination.getContent().isEmpty());
        assertEquals(Constants.PAGE_0, pagination.getPageNumber());
        assertEquals(Constants.PAGE_SIZE_1, pagination.getPageSize());
        assertEquals(allBrands.size(), pagination.getTotalElements());
        verify(brandPersistencePort, times(1)).findAll();
    }
}
