package com.example.allforyourhome.service;

import com.example.allforyourhome.entity.User;

public interface SmsService {
    void sendVerificationSms(User user, String verificationCode);

    void authorize();

    void refresh();
}
