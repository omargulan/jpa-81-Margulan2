package com.practice.springjpa81.service;

import com.practice.springjpa81.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@AutoConfigureTestDatabase
public class CategoryServiceIT {

    @Autowired
    private CategoryService categoryService;

    @Test
    void deleteByIdTest_shouldDelete_WhenIncorrectIdGiven(){
        Category category = new Category();
        category.setName("Smartphones");
        categoryService.create(category);

        categoryService.deleteById(category.getId());

        Assertions.assertThrows(
                ResponseStatusException.class,
                () -> categoryService.findById(category.getId())

        );
    }

    @Test
    void deleteByIdTest_shouldNotDelete_WhenIncorrectIdGiven(){
        Category category = new Category();
        category.setName("Smartphones");
        categoryService.create(category);

        categoryService.deleteById(category.getId()+1);

        Assertions.assertDoesNotThrow(
                () -> categoryService.findById(category.getId())

        );
    }


}
