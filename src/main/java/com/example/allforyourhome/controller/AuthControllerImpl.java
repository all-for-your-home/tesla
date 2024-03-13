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
    public Response<String> forgotPassword(String email) {
        return authService.forgotPassword(email);
    }

    @Override
    public Response<String> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        return authService.resetPassword(resetPasswordDTO);
    }

    @Override
    public Response<String> verifyAccount(String verificationCode) {
        return authService.verifyAccount(verificationCode);
    }

    @Override
    public Response<String> checkPasswordVerificationCode(String code) {
        return authService.checkPasswordVerificationCode(code);
    }
}
