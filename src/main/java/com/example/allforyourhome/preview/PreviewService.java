package com.example.allforyourhome.preview;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class PreviewService {


    private final PreviewRepository previewRepository;

}
