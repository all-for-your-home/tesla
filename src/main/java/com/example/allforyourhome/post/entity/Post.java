package com.example.allforyourhome.post.entity;

import com.example.allforyourhome.post.PostListeners;
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
@Table(name = "post")
@EntityListeners(PostListeners.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String yourName;
    private String yourEmail;
    private String yourWebsite;
    private String comment;

    private LocalDateTime created;
    private LocalDateTime updated;

}
