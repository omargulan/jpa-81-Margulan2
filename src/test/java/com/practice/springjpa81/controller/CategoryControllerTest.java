package com.practice.springjpa81.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.springjpa81.dto.CategoryCreateDto;
import com.practice.springjpa81.model.Category;
import com.practice.springjpa81.model.Option;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void findById_shouldReturnCategory_whenCorrectIdGiven(){
        long categoryId=1;
        RequestBuilder requestBuilder= get("/categories/" + categoryId);
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").value(categoryId));
        resultActions.andExpect(jsonPath("$.name").value("Процессоры"));

    }
    @Test
    @SneakyThrows
    void findById_shouldReturnCategory_whenInCorrectIdGiven(){
        long categoryId=9999;
        mockMvc.perform(get("/categories/" + categoryId))
                        .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void findAll_shouldReturnNonEmptyList(){
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Процессоры"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Мониторы"));

    }

    @Test
    @SneakyThrows
    void create_shouldReturnCategory() {
        CategoryCreateDto createDto = new CategoryCreateDto();
        createDto.setName("Роутеры");
        createDto.setOptions(List.of("Скорость порта", "Количество портов", "Производитель"));

        String json = objectMapper.writeValueAsString(createDto); //{name": }

        mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(createDto.getName()))
                .andExpect(jsonPath("$.options").isArray())
                .andExpect(jsonPath("$.options.length()").value(3))
                .andExpect(jsonPath("$.options[0].name").value("Скорость порта"))
                .andExpect(jsonPath("$.options[1].name").value("Количество портов"))
                .andExpect(jsonPath("$.options[2].name").value("Производитель"));


    }
    @Test
    @SneakyThrows
    void create_shouldReturnBadRequest_whenNameIsBlank(){
        CategoryCreateDto createDto = new CategoryCreateDto();
        createDto.setName("");
        String json = objectMapper.writeValueAsString(createDto);
        mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }
}
