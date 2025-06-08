package com.practice.springjpa81.service;
import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.model.Product;
import com.practice.springjpa81.repository.CategoryRepository;
import com.practice.springjpa81.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public Product create(Product product, long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));

        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
    // TODO: написать два теста
    // 1. где категория существует и товар создается
    // 2. где категория не существует и выбрасывается исключение
}
