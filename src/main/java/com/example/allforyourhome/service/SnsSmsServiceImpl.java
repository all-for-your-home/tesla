package com.example.allforyourhome.service;

import com.example.allforyourhome.entity.User;
import com.example.allforyourhome.utils.RestConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Slf4j
@RequiredArgsConstructor
public class SnsSmsServiceImpl implements SmsService {
    private final SnsClient snsClient;

    @Async
    @Override
    public void sendVerificationSms(User user, String verificationCode) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(verificationCode)
                    .phoneNumber(RestConstants.UZB_CODE + user.getPhoneNumber())
                    .build();

            PublishResponse result = snsClient.publish(request);
            System.out
                    .println(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());
        } catch (SnsException e) {
            e.printStackTrace();
        }
    }
}
