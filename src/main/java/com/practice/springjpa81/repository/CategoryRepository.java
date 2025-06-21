package com.practice.springjpa81.repository;

import com.practice.springjpa81.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // findByПоле

    Optional<Category> findByName(String name); // select * from categories where name = ?
    // Процессоры

    Optional<Category> findByNameOrName(String name1, String name2); // select * from categories where name = ? or name = ?

    Optional<Category> findByNameOrId(String name, long id); // select * from categories where name = ? or id = ?

    Optional<Category> findByNameIgnoreCase(String name); // select * from categories where upper(name) = upper(?)

    Optional<Category> findByNameContaining(String name); // Проц
    //

    List<Category> findByNameContainingIgnoreCase(String name); // р

    List<Category> id(Long id);

    // Создайте класс Film
    // - заполните полями.
    // Создайте интерфейс FilmRepository.
    // Создайте класс FilmController.
    // - создайте эндпойнт:
    //   GET /films/search?q=НАЗВАНИЕ_ФИЛЬМА&mpa=G - найти фильмы по названию И mpa
    //   GET /films/find-in-range?min=8.0&max=9.0 - найти фильмы по рейтингу в указанном диапазоне


//  GET /films/search
//   каждый параметр необязательный
//     q - название фильма
//     mpa - mpa рейтинг
//     minRating - минимальный рейтинг
//     maxRating - максимальный рейтинг
//     genre - жанр

}
