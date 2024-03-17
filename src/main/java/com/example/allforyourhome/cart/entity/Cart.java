package com.example.allforyourhome.cart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private Integer id;
    private Integer user_id;
    private Integer categories_id;

    private LocalDateTime created;
    private LocalDateTime updated;

}
