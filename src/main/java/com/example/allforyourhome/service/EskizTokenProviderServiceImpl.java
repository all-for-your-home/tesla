package com.example.allforyourhome.service;

import com.example.allforyourhome.client.EskizClient;
import com.example.allforyourhome.exceptions.RestException;
import com.example.allforyourhome.payload.TokenDTO;
import com.example.allforyourhome.utils.MessageConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EskizTokenProviderServiceImpl implements TokenProviderService {
    @Getter
    private String token;
    private final EskizClient eskizClient;
    @Value("${app.eskiz.email}")
    private String email;
    @Value("${app.eskiz.password}")
    private String password;

    @Override
    public void authorize() {
        setHeaders(eskizClient.authorize(email, password));
    }

    @Override
    public void refresh() {
        setHeaders(eskizClient.refresh(token));
    }

    private void setHeaders(TokenDTO tokenDTO) {
        if (Objects.isNull(tokenDTO.getData()))
            throw RestException.restThrow(MessageConstants.UNABLE_TO_GET_TOKEN);
        token = tokenDTO.getTokenType() + tokenDTO.getData().getToken();
    }
}
