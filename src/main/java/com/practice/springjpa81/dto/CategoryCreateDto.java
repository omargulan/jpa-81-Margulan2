package com.practice.springjpa81.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryCreateDto {
    //@Schema(title = "Название категорий", example = "процессоры")
    @NotBlank(message="название категорий не может быть пустым")
    private String name;
    private List<String> options = new ArrayList<>();



}
