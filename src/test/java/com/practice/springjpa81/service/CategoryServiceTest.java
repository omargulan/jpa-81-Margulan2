package com.practice.springjpa81.service;

import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    void create_shouldThrowException_whenCategoryNameIsTaken(){
        //CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        //CategoryService categoryService = new CategoryService(categoryRepository);

        Category existingCategory = new Category();
        existingCategory.setId(1L);
        existingCategory.setName("Кухонная гарнитура");

        Mockito.when(categoryRepository.findByName("Кухонная гарнитура"))
                .thenReturn(Optional.of(existingCategory));
        Category newCategory = new Category();
        newCategory.setName("Кухонная гарнитура");

        //categoryService.create(newCategory);
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> categoryService.create(newCategory));
        assertEquals("Категория с такими названием уже существует", ex.getMessage());
    }

    @Test
    void create_shouldThrowException_whenCategoryNameIsNotTaken(){
        //CategoryService categoryService = new CategoryService();
        //CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        //CategoryService categoryService = new CategoryService(categoryRepository);

        Mockito.when(categoryRepository.findByName("Кухонная гарнитура"))
                .thenReturn(Optional.empty());
        Category newCategory = new Category();
        newCategory.setName("Кухонная гарнитура");

        Category created = categoryService.create(newCategory);
        assertEquals(newCategory.getName(), created.getName());
    }

    @Test
    void create_shouldThrowException_whenCategoryNameIsNull(){
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        //CategoryService categoryService = new CategoryService(categoryRepository);

        Category newCategory = new Category();
        newCategory.setName(null);

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> categoryService.create(newCategory));
        assertEquals("Название категорий не может быть пустым", ex.getMessage());
    }

}
