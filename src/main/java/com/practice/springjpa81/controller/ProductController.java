package com.practice.springjpa81.controller;

import com.practice.springjpa81.dto.ProductDto;
import com.practice.springjpa81.dto.ProductMapper;
import com.practice.springjpa81.dto.ProductShortDto;
import com.practice.springjpa81.model.Product;
import com.practice.springjpa81.repository.ProductRepository;
import com.practice.springjpa81.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductShortDto> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String field,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(required = false) Long categoryId
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), field);
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Product> products = productRepository.findByCategory_Id(categoryId, pageable).getContent();

        return products.stream()
                .map(product ->  productMapper.toNotFullDto(product))
                .toList();
    }

    // GET /films/search?q=НАЗВАНИЕ_ФИЛЬМА&min=7&max=9&page=2&size=20&sortBy=rating&sortDirection=desc

//    @GetMapping
//    public List<Product> findAll(@RequestParam String field, @RequestParam String sortDirection) {
//        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), field);
//        return productRepository.findAll(sort);
//    }


//    @GetMapping
//    public List<Product> findAll(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "20") int size
//    ) {
//        // org.springframework.data.domain
//        // of(номерСтраницы, размерСтраницы)
//
//        // номерСтраницы=2
//        // размерСтраницы=2
//
//        // select * from products offset 4 limit 2
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Product> productsPage = productRepository.findAll(pageable);
//        return productsPage.getContent();
//    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return productMapper.toDto(product);
    }
}
