package com.example.allforyourhome.security;

import com.example.allforyourhome.payload.ErrorData;
import com.example.allforyourhome.payload.Response;
import com.example.allforyourhome.utils.RestConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException {
        log.error("Responding with unauthorized error. URL -  {}, Message - {}", req.getRequestURI(), e.getMessage());
        Response<ErrorData> errorDataApiResult = Response.errorResponse("Unauthorized access", HttpStatus.UNAUTHORIZED.value());
        res.getWriter().write(RestConstants.objectMapper.writeValueAsString(errorDataApiResult));
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Headers", "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setContentType("application/json");
    }

}
