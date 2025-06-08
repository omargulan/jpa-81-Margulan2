package com.practice.springjpa81.service;

import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.model.Product;
import com.practice.springjpa81.repository.CategoryRepository;
import com.practice.springjpa81.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class ProductServiceTest {
    // TODO: написать два теста
    // 1. где категория существует и товар создается
    // 2. где категория не существует и выбрасывается исключение

    @Test
    void create_shouldThrowException_WhenCategoryIdExist(){
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        CategoryService categoryService = new CategoryService(categoryRepository);
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        Category existingCategory = new Category();
        existingCategory.setId(1L);

        Product product = new Product();
        product.setName("Клавиатура");

        Mockito.when(categoryRepository.findById(existingCategory.getId()))
                .thenReturn(Optional.of(existingCategory));
        Mockito.when(productRepository.save(product))
                .thenReturn(product);

        Product productCreated = prod

    }
}
