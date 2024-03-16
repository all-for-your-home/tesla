package com.example.allforyourhome.client;

import com.example.allforyourhome.payload.SendSmsDTO;
import com.example.allforyourhome.payload.SmsResponseDTO;
import com.example.allforyourhome.payload.TokenDTO;
import com.example.allforyourhome.utils.RestConstants;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "eskiz", url = EskizClient.BASE_PATH)
public interface EskizClient {
    String BASE_PATH = "https://notify.eskiz.uz/api";
    String LOGIN_PATH = "/auth/login";
    String REFRESH_PATH = "/auth/refresh";
    String SEND_MESSAGE = "/message/sms/send";

    @PostMapping(SEND_MESSAGE)
    @Headers("Content-Type: application/json")
    SmsResponseDTO sendSms(@RequestBody SendSmsDTO sendSmsDTO, @RequestHeader(RestConstants.AUTHENTICATION_HEADER) String token);

    @PostMapping(LOGIN_PATH)
    TokenDTO authorize(@RequestParam("email") String email, @RequestParam("password") String password);

    @PatchMapping(REFRESH_PATH)
    TokenDTO refresh(@RequestHeader(RestConstants.AUTHENTICATION_HEADER) String token);
}
