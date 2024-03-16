package com.example.allforyourhome.product;


import com.example.allforyourhome.product.dto.ProductCreateDto;
import com.example.allforyourhome.product.dto.ProductResponseDto;
import com.example.allforyourhome.product.dto.ProductUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductCreateDto productCreateDto) {
        ProductResponseDto productResponseDto = productService.create(productCreateDto);
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> get(@PathVariable("id") Integer id) {
        ProductResponseDto product = productService.getById(id);

        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>>getAll(Pageable pageable){
        Page<ProductResponseDto> all = productService.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Integer id){
        productService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto>update(@PathVariable("id")Integer id,@RequestBody ProductUpdateDto updateDto){
        ProductResponseDto update = productService.update(id,updateDto);
        return ResponseEntity.ok(update);
    }
}
