package com.practice.springjpa81.repository;

import com.practice.springjpa81.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void findByName_shouldReturnCategory(){
        Category category = new Category();
        category.setName("мониторы");
        categoryRepository.save(category);

        String searchName = category.getName();
        Optional<Category> optional = categoryRepository.findByName(searchName);
        assertTrue(optional.isPresent());
    }

    @Test
    void findByName_shouldReturnEmptyOptional(){


        String searchName = "wrong Category name";
        Optional<Category> optional = categoryRepository.findByName(searchName);
        assertTrue(optional.isEmpty());
    }
}
