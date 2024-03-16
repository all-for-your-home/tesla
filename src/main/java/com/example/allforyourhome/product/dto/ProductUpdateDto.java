package com.example.allforyourhome.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {

    private String name;
    private String status;
    private Double price;
    private String photo;
    private String color;
    private String description;
}
