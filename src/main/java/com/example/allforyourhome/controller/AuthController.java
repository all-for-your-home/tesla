package com.example.allforyourhome.controller;

import com.example.allforyourhome.payload.*;
import com.example.allforyourhome.utils.RestConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = AuthController.BASE_PATH)
public interface AuthController {

    String BASE_PATH = RestConstants.BASE_PATH_V1 + "/auth";
    String SIGN_UP_PATH = "/sign-up";
    String SIGN_IN_PATH = "/sign-in";
    String FORGOT_PASSWORD_PATH = "/forgot-password";
    String RESET_PASSWORD_PATH = "/reset-password";
    String VERIFY_ACCOUNT_PATH = "/verify-account";
    String CHECK_PASSWORD_VERIFICATION_CODE_PATH = "/check-password-verification";

    @PostMapping(SIGN_UP_PATH)
    Response<String> signUp(@Valid @RequestBody SignUpDTO signUpDTO);

    @PostMapping(SIGN_IN_PATH)
    Response<TokenDTO> signIn(@Valid @RequestBody SignInDTO signInDTO);

    @GetMapping(FORGOT_PASSWORD_PATH)
    Response<String> forgotPassword(@Valid @Email @RequestParam String phoneNumber);

    @PutMapping(RESET_PASSWORD_PATH)
    Response<String> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO);

    @PostMapping(VERIFY_ACCOUNT_PATH)
    Response<String> verifyAccount(@Valid @RequestBody VerificationCodeDTO verificationCodeDTO);

    @PostMapping(CHECK_PASSWORD_VERIFICATION_CODE_PATH)
    Response<String> checkPasswordVerificationCode(@Valid @RequestBody VerificationCodeDTO verificationCodeDTO);
}
