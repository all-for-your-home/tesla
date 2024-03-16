package com.example.allforyourhome.category;

import com.example.allforyourhome.category.dto.CategoryResponseDto;
import com.example.allforyourhome.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository repository;
    private final CategoryService service;
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAll(){
        List<CategoryResponseDto> all = service.getAll();
        return ResponseEntity.ok(all);
    }
}
