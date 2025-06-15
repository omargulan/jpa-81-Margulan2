package com.practice.springjpa81.repository;

import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void findByBetweenPrice_shouldReturProducts(){
        Category category =  new Category();
        category.setName("Наушники");
        categoryRepository.save(category);

        Product product = new Product();
        product.setCategory(category);
        product.setName("AirPods Pro");
        product.setPrice(189990.0);
        productRepository.save(product);

        Product product2 = new Product();
        product2.setCategory(category);
        product2.setName("Sony WH1000-XM4");
        product2.setPrice(179990.0);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setCategory(category);
        product3.setName("Marshall Major IV");
        product3.setPrice(699990.0);
        productRepository.save(product3);

        Product product4 = new Product();
        product4.setCategory(category);
        product4.setName("AirPods MAX");
        product4.setPrice(400000.0);
        productRepository.save(product4);

        List<Product> result = productRepository.findByPriceBetween(170000.0, 200000.0);

        System.out.println(result);



    }

    @Test
    void findByBetweenPrice_shouldReturnIsEmty(){
//        Category category =  new Category();
//        category.setName("Наушники");
//        categoryRepository.save(category);


        List<Product> result = productRepository.findByPriceBetween(50000.0, 600000.0);
        assertTrue(result.isEmpty());



    }


}
