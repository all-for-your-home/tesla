package com.example.allforyourhome.entity;

import com.example.allforyourhome.entity.template.AbsUUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment extends AbsUUIDEntity {

    @Column(nullable = false)
    private String originalName;

    private long size;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String path;
}