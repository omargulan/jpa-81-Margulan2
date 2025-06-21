package com.practice.springjpa81.service;

import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category create(Category category) {
        if (category.getName() == null || category.getName().isBlank()) {
            throw new RuntimeException("Название категорий не может быть пустым");
        }
        Optional<Category> optional = categoryRepository.findByName(category.getName());
        if (optional.isPresent()) {
            throw new RuntimeException("Категория с таким названием уже существует");
        }
        categoryRepository.save(category);
        return category;
    }

    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
