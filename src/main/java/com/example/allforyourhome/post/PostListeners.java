package com.example.allforyourhome.post;

import com.example.allforyourhome.post.entity.Post;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class PostListeners {

    @PrePersist
    public void createdAt(Post post) {
        post.setCreated(LocalDateTime.now());
    }

    @PreUpdate
    public void updatedAt(Post post) {
        post.setUpdated(LocalDateTime.now());
    }


}
