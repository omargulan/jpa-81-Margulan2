package com.practice.springjpa81.service;

import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@AutoConfigureTestDatabase
public class ProductServiceIT {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Test
    void deleteByIdTest_shouldDelete_WhenIncorrectIdGiven(){
        Category category = new Category();
        category.setName("Smartphones");
        categoryService.create(category);

        Product product = new Product();
        product.setName("Samsung S25");

        //product.setCategory(category.getId());
        productService.create(product, category.getId());
        productService.deleteById(product.getId());

        Assertions.assertThrows(
                ResponseStatusException.class,
                () -> productService.findById(product.getId())
        );
    }

    @Test
    void deleteByIdTest_shouldNotDelete_WhenIncorrectIdGiven(){
        Category category = new Category();
        category.setName("Smartphones");
        categoryService.create(category);

        Product product = new Product();
        product.setName("Samsung S25");
        productService.create(product, category.getId());
        productService.deleteById(product.getId()+1);
        Assertions.assertDoesNotThrow(
                ()-> productService.findById(product.getId())
        );
    }
}
