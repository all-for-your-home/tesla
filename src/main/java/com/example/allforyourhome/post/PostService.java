package com.example.allforyourhome.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
}
