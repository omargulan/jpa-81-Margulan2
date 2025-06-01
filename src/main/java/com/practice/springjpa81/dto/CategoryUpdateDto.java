package com.practice.springjpa81.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryUpdateDto {
    //@Schema(title = "Название категорий", example = "процессоры")
    private String name;
    private List<OptionDto> options = new ArrayList<>();


}
