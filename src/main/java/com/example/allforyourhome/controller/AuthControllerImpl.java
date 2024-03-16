package com.example.allforyourhome.controller;

import com.example.allforyourhome.payload.*;
import com.example.allforyourhome.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public Response<String> signUp(SignUpDTO signUpDTO) {
        return authService.signUp(signUpDTO);
    }

    @Override
    public Response<TokenDTO> signIn(SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }

    @Override
    public Response<String> forgotPassword(String phoneNumber) {
        return authService.forgotPassword(phoneNumber);
    }

    @Override
    public Response<String> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        return authService.resetPassword(resetPasswordDTO);
    }

    @Override
    public Response<String> verifyAccount(VerificationCodeDTO verificationCodeDTO) {
        return authService.verifyAccount(verificationCodeDTO);
    }

    @Override
    public Response<String> checkPasswordVerificationCode(VerificationCodeDTO verificationCodeDTO) {
        return authService.checkPasswordVerificationCode(verificationCodeDTO);
    }
}
