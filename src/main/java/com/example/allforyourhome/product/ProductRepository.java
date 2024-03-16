package com.example.allforyourhome.product;

import com.example.allforyourhome.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
