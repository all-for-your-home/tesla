package com.example.allforyourhome.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    private Integer id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
}
