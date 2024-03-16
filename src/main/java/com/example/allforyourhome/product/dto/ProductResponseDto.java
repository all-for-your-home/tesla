package com.example.allforyourhome.product.dto;


import com.example.allforyourhome.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Integer id;
    private String name;
    private String status;
    private Double price;
    private String photo;
    private String color;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
}
