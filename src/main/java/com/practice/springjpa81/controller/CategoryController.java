package com.practice.springjpa81.controller;

import com.practice.springjpa81.dto.CategoryCreateDto;
import com.practice.springjpa81.dto.CategoryFullDto;
import com.practice.springjpa81.dto.CategoryMapper;
import com.practice.springjpa81.dto.CategoryShortDto;
import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.model.Option;
import com.practice.springjpa81.repository.CategoryRepository;
import com.practice.springjpa81.repository.OptionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final OptionRepository optionRepository;

    @GetMapping
    public List<CategoryShortDto> findAll() {
        return categoryRepository.findAll().stream()   // select * from categories
                .map(category -> categoryMapper.toShortDto(category))
                .toList();
    }

    @GetMapping("/{id}")
    public CategoryFullDto findById(@PathVariable long id) {
        Category category = categoryRepository.findById(id)  // select * from categories where id = ?
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return categoryMapper.toFullDto(category);
    }

//    @PostMapping
//    public Category create(@RequestBody Category category) {
//        return categoryRepository.save(category);  // insert into categories (name) values (?)
//    }

    @PostMapping
    public CategoryFullDto create(@Valid @RequestBody CategoryCreateDto dto){
        Category category = new Category();
        category.setName(dto.getName());

        List<Option> list = new ArrayList<>();
        for (String optionName : dto.getOptions()){
            Option option = new Option();
            option.setName(optionName);
            option.setCategory(category);
            category.getOptions().add(option);
            list.add(option);
        }
        categoryRepository.save(category);
        optionRepository.saveAll(list);
        return categoryMapper.toFullDto(category);
    }
    
    @PutMapping("/{id}")
    public Category update(long id, @RequestBody Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        existingCategory.setName(category.getName());
        
        return categoryRepository.save(existingCategory); // update categories set name = ? where id = ?
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        categoryRepository.deleteById(id); // delete from categories where id = ?
    }

    // GET /categories/find-by-name/Процессоры
    @GetMapping("/find-by-name/{name}")
    public List<Category> findByName(@PathVariable String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    // GET /products/search?name=Intel
//    public List<Category> findByNameContaining(@RequestParam String name) {
}


