package com.example.allforyourhome.category;

import com.example.allforyourhome.category.dto.CategoryResponseDto;
import com.example.allforyourhome.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    public List<CategoryResponseDto> getAll(
    ) {
        return repository
                .findAll()
                .stream()
                .map(
                        category -> mapper
                                .map(category, CategoryResponseDto.class)
                )
                .toList();
    }
}
