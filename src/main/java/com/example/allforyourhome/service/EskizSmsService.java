package com.example.allforyourhome.service;

import com.example.allforyourhome.client.EskizClient;
import com.example.allforyourhome.entity.User;
import com.example.allforyourhome.payload.SendSmsDTO;
import com.example.allforyourhome.utils.RestConstants;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EskizSmsService implements SmsService {
    private final EskizClient eskizClient;
    private final TokenProviderService tokenProviderService;

    @Override
    public void sendVerificationSms(User user, String message) {
        SendSmsDTO sms = SendSmsDTO.builder()
                .message(message)
                .mobilePhone(RestConstants.UZB_CODE + user.getPhoneNumber())
                .build();
        eskizClient.sendSms(sms, tokenProviderService.getToken());
    }


}
