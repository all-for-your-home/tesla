package com.example.allforyourhome.product;

import com.example.allforyourhome.category.CategoryRepository;
import com.example.allforyourhome.category.entity.Category;
import com.example.allforyourhome.product.dto.ProductCreateDto;
import com.example.allforyourhome.product.dto.ProductResponseDto;
import com.example.allforyourhome.product.dto.ProductUpdateDto;
import com.example.allforyourhome.product.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper = new ModelMapper();

    public ProductResponseDto create(ProductCreateDto productCreateDto) {
        Product product = mapper.map(productCreateDto, Product.class);
        Category category = categoryRepository.findById(productCreateDto.getCategory_id()).orElseThrow(() -> new EntityNotFoundException("Category Not Found"));

        product.setCategory(category);
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());

        repository.save(product);
        return mapper.map(product, ProductResponseDto.class);
    }


    public ProductResponseDto getById(Integer id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product Not Found")
        );
        return mapper.map(product, ProductResponseDto.class);
    }

    public Page<ProductResponseDto> getAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(
                        product -> mapper.
                                map(product, ProductResponseDto.class)
                );
    }

    public void delete(Integer id) {
        Product product = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("This id %d Product Not found")
        );

        repository.delete(product);
    }

    public ProductResponseDto update(Integer id, ProductUpdateDto updateDto) {
        Product product = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("This id %d Product Not found")
        );
        mapper.map(updateDto, product);

        product = repository.save(product);

        return mapper.map(product, ProductResponseDto.class);
    }
}
