package com.example.allforyourhome.service;

import com.example.allforyourhome.entity.User;
import com.example.allforyourhome.payload.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface AuthService extends UserDetailsService {
    User getUserById(UUID id);

    Response<String> signUp(SignUpDTO signUpDTO);

    Response<TokenDTO> signIn(SignInDTO signInDTO);

    Response<String> resetPassword(ResetPasswordDTO resetPasswordDTO);

    Response<String> forgotPassword(String phoneNumber);

    Response<String> verifyAccount(VerificationCodeDTO verificationCodeDTO);

    Response<String> checkPasswordVerificationCode(VerificationCodeDTO verificationCodeDTO);
}
