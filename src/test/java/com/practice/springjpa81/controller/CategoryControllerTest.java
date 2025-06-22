package com.practice.springjpa81.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    MockMvc mockMvc;

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
}
