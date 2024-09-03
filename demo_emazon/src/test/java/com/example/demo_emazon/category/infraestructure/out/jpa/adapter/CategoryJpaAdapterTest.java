package com.example.demo_emazon.category.infraestructure.out.jpa.adapter;

import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.infraestructure.out.jpa.entity.CategoryEntity;
import com.example.demo_emazon.category.infraestructure.out.jpa.mapper.ICategoryEntityMapper;
import com.example.demo_emazon.category.infraestructure.out.jpa.repository.ICategoryJpaRepository;
import com.example.demo_emazon.testdata.Constants;
import com.example.demo_emazon.testdata.TestData.TestDataCategory;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryJpaAdapterTest {

    @Mock
    private ICategoryJpaRepository categoryJpaRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;


    @Test
    @DisplayName("Test Save Category")
    void testSaveCategory() {
        Category category = TestDataCategory.getCategory();
        CategoryEntity categoryEntity = TestDataCategory.getCategoryEntity();

        when(categoryEntityMapper.toEntity(any(Category.class))).thenReturn(categoryEntity);
        when(categoryJpaRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);
        when(categoryEntityMapper.toCategory(any(CategoryEntity.class))).thenReturn(category);

        Category result = categoryJpaAdapter.save(category);

        assertEquals(category, result);
        verify(categoryEntityMapper).toEntity(category);
        verify(categoryJpaRepository).save(categoryEntity);
        verify(categoryEntityMapper).toCategory(categoryEntity);
    }

    @Test
    @DisplayName("Test Find All Responses")
    void testFindAllCategories() {
        CategoryEntity categoryEntity1 = TestDataCategory.getCategoryEntity();
        CategoryEntity categoryEntity2 = TestDataCategory.getCategoryEntity();
        Category category1 = TestDataCategory.getCategory();
        Category category2 = TestDataCategory.getCategory();

        when(categoryJpaRepository.findAll()).thenReturn(List.of(categoryEntity1, categoryEntity2));
        when(categoryEntityMapper.toCategory(categoryEntity1)).thenReturn(category1);
        when(categoryEntityMapper.toCategory(categoryEntity2)).thenReturn(category2);

        List<Category> result = categoryJpaAdapter.findAll();

        assertEquals(2, result.size());
        assertEquals(category1, result.get(0));
        assertEquals(category2, result.get(1));
        verify(categoryJpaRepository).findAll();
        verify(categoryEntityMapper).toCategory(categoryEntity1);
        verify(categoryEntityMapper).toCategory(categoryEntity2);
    }

    @Test
    @DisplayName("Test Find Category with a name")
    void testFindCategoryByName() {
        String name = Constants.NAME;
        CategoryEntity categoryEntity = TestDataCategory.getCategoryEntity();
        Category category = TestDataCategory.getCategory();

        when(categoryJpaRepository.findByName(name)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);

        Category result = categoryJpaAdapter.findByName(name);

        assertEquals(category, result);
        verify(categoryJpaRepository).findByName(name);
        verify(categoryEntityMapper).toCategory(categoryEntity);
    }

}