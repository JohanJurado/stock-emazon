package com.example.demo_emazon.category.infraestructure.out.jpa.adapter;

import com.example.demo_emazon.category.domain.model.Category;
import com.example.demo_emazon.category.infraestructure.out.jpa.entity.CategoryEntity;
import com.example.demo_emazon.category.infraestructure.out.jpa.mapper.ICategoryEntityMapper;
import com.example.demo_emazon.category.infraestructure.out.jpa.repository.ICategoryJpaRepository;
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
        Category category = new Category(1L, "Electronics", "Electronics Description");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics", "Electronics Description");

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
        CategoryEntity categoryEntity1 = new CategoryEntity(1L, "Electronics", "Electronics Description");
        CategoryEntity categoryEntity2 = new CategoryEntity(2L, "Books", "Books Description");
        Category category1 = new Category(1L, "Electronics", "Electronics Description");
        Category category2 = new Category(2L, "Books", "Books Description");

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
        String name = "Electronics";
        CategoryEntity categoryEntity = new CategoryEntity(1L, name, "Electronics Description");
        Category category = new Category(1L, name, "Electronics Description");

        when(categoryJpaRepository.findByName(name)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toCategory(categoryEntity)).thenReturn(category);

        Category result = categoryJpaAdapter.findByName(name);

        assertEquals(category, result);
        verify(categoryJpaRepository).findByName(name);
        verify(categoryEntityMapper).toCategory(categoryEntity);
    }

}