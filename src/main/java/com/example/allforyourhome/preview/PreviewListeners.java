package com.example.allforyourhome.preview;

import com.example.allforyourhome.preview.entity.Preview;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class PreviewListeners {

    @PrePersist
    public void createdAt(Preview preview) {
        preview.setCreated(LocalDateTime.now());
    }

    @PreUpdate
    public void updatedAt(Preview preview) {
        preview.setUpdated(LocalDateTime.now());
    }

}
