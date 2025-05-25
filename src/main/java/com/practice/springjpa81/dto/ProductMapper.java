package com.practice.springjpa81.dto;

import com.practice.springjpa81.model.Option;
import com.practice.springjpa81.model.Product;
import com.practice.springjpa81.model.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductMapper {
    // Из Product в ProductDto
    public ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategoryName(product.getCategory().getName());

        Map<String, String> valOpt = new HashMap<>();
        for (Value value : product.getValues()){
            valOpt.put(value.getOption().getName(), value.getName());
        }
        dto.setOptions(valOpt);
        return dto;
    }
    public ProductShortDto toNotFullDto(Product product) {
        ProductShortDto dto = new ProductShortDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategoryName(product.getCategory().getName());

        return dto;
    }

}
