package com.example.allforyourhome.preview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreviewResponseDto {

    private Integer product_id;
    private Integer rating;
    private LocalDateTime created;
    private LocalDateTime updated;

}
