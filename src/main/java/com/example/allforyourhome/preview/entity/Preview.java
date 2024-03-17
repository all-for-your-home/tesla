package com.example.allforyourhome.preview.entity;

import com.example.allforyourhome.preview.PreviewListeners;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "preview")
@EntityListeners(PreviewListeners.class)
public class Preview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer product_id;
    private Integer rating;
    private LocalDateTime created;
    private LocalDateTime updated;


}
