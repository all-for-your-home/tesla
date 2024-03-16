package com.example.allforyourhome.service;

import com.example.allforyourhome.client.EskizClient;
import com.example.allforyourhome.entity.User;
import com.example.allforyourhome.exceptions.RestException;
import com.example.allforyourhome.payload.SendSmsDTO;
import com.example.allforyourhome.payload.TokenDTO;
import com.example.allforyourhome.utils.MessageConstants;
import com.example.allforyourhome.utils.RestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class EskizSmsService implements SmsService {
    private final EskizClient eskizClient;
    @Value("${app.eskiz.email}")
    private String email;
    @Value("${app.eskiz.password}")
    private String password;
    private String token;

    @Override
    @Async
    public void sendVerificationSms(User user, String message) {
        SendSmsDTO sms = SendSmsDTO.builder()
                .message(message)
                .mobilePhone(RestConstants.UZB_CODE + user.getPhoneNumber())
                .build();
        eskizClient.sendSms(sms, token);
    }

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
        this.token = tokenDTO.getTokenType() + tokenDTO.getData().getToken();
    }
}
