package com.practice.springjpa81.service;

import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.model.Product;
import com.practice.springjpa81.repository.CategoryRepository;
import com.practice.springjpa81.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductServiceTest {
    // TODO: написать два теста
    // 1. где категория существует и товар создается
    // 2. где категория не существует и выбрасывается исключение

    @Test
    void create_shouldThrowException_WhenCategoryIdExist(){
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        ProductService productService = new ProductService(categoryRepository, productRepository);

        Category existingCategory = new Category();
        existingCategory.setId(1L);

        Product product = new Product();
        product.setName("Клавиатура");
        product.setPrice(15000.00);
        product.setId(100L);
        //product.setCategory(existingCategory);

        Mockito.when(categoryRepository.findById(existingCategory.getId()))
                .thenReturn(Optional.of(existingCategory));
        Mockito.when(productRepository.save(product))
                .thenReturn(product);

        Product productCreated = productService.create(product, existingCategory.getId());
        assertEquals(existingCategory.getId(), productCreated.getCategory().getId());

    }

    @Test
    void create_shouldThrowException_WhenCategoryIsNotExist(){
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

        ProductService productService = new ProductService(categoryRepository, productRepository);


        Product product = new Product();
        product.setName("Клавиатура");
        product.setPrice(15000.00);
        //product.setId(100L);

        long categoryId=1L;

        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> productService.create(product, categoryId));
        assertEquals("Категория не найдена", ex.getMessage());


    }
}
