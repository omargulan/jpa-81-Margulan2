package com.practice.springjpa81.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryCreateDto {
    //@Schema(title = "Название категорий", example = "процессоры")
    private String name;
    private List<String> options = new ArrayList<>();


}
