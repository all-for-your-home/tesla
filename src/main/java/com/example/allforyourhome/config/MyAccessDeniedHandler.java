package com.example.allforyourhome.config;

import com.example.allforyourhome.exceptions.ExceptionHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    private final ExceptionHelper exceptionHelper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ResponseEntity<?> responseEntity = exceptionHelper.handleException(accessDeniedException);
    }
}
