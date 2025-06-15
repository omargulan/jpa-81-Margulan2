package com.practice.springjpa81.repository;

import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p " +
            "where :categoryId is null " +
            "   or p.category.id = :categoryId")
    Page<Product> findByCategory_Id(Long categoryId, Pageable pageable);


    List<Product> findByPriceBetween(Double min, Double max);

    // select p from Product p
    // join p.category c
    // where c.name = ?

    // select * from products p
    // join categories c on p.category_id = c.id
    // where c.name = ?
}



// JPA
// EntityManager +

// Spring Data JPA

// DAO - Data Access Object
// Repository:
//  findById - select * from table where id = ?
//  findAll  - select * from table
//  save
//  deleteById
//

// controller - service - dao - БД
// controller - repository - БД

// Запросные методы (query methods)
