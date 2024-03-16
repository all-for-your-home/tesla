package com.example.allforyourhome.service;

import com.example.allforyourhome.entity.User;
import com.example.allforyourhome.exceptions.RestException;
import com.example.allforyourhome.mapper.UserMapper;
import com.example.allforyourhome.payload.*;
import com.example.allforyourhome.repository.UserRepository;
import com.example.allforyourhome.security.JwtTokenProvider;
import com.example.allforyourhome.utils.MessageConstants;
import com.example.allforyourhome.utils.RestConstants;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SmsService smsService;

    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider,
                           @Lazy AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           UserMapper userMapper,
                           @Lazy PasswordEncoder passwordEncoder,
                           SmsService smsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.smsService = smsService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByPhoneNumber(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> RestException.restThrow(MessageConstants.USER_NOT_FOUND_WITH_ID + id));
    }

    @Override
    public Response<String> signUp(SignUpDTO signUpDTO) {
        if (!Objects.equals(signUpDTO.getPassword(), signUpDTO.getPrePassword()))
            throw RestException.restThrow(MessageConstants.PASSWORDS_AND_PRE_PASSWORD_NOT_EQUAL);
        User user;
        Optional<User> optionalUser = userRepository.findByPhoneNumber(signUpDTO.getPhoneNumber());
        if (optionalUser.isPresent()) {
            if (optionalUser.get().isEnabled())
                throw RestException.restThrow(MessageConstants.PHONE_ALREADY_REGISTERED);
            user = optionalUser.get();
            userMapper.update(signUpDTO, user);
        } else
            user = userMapper.toUser(signUpDTO);
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        int verificationCode = RestConstants.random.nextInt(100000, 999999);
        user.setVerificationCode(verificationCode);
        userRepository.save(user);

        smsService.sendVerificationSms(user, String.valueOf(user.getVerificationCode()));
        return Response.successResponseForMsg(MessageConstants.ACCOUNT_CREATED + " " + MessageConstants.VERIFICATION_CODE_SENT_TO_PHONE_NUMBER);
    }


    @Override
    public Response<TokenDTO> signIn(SignInDTO signInDTO) {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getPhoneNumber().toLowerCase(), signInDTO.getPassword()));
        } catch (DisabledException | LockedException | AccountExpiredException |
                 CredentialsExpiredException e) {
            throw RestException.restThrow(e.getMessage(), RestConstants.USER_NOT_ACTIVE, HttpStatus.UNAUTHORIZED);
        } catch (BadCredentialsException | UsernameNotFoundException badCredentialsException) {
            throw RestException.restThrow(MessageConstants.LOGIN_OR_PASSWORD_ERROR, RestConstants.INCORRECT_USERNAME_OR_PASSWORD, HttpStatus.UNAUTHORIZED);
        }

        User principal = (User) authentication.getPrincipal();
        TokenDTO tokenDTO = makeTokenDTO(principal);

        return Response.successResponse(tokenDTO);
    }

    @Override
    public Response<String> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        if (!Objects.equals(resetPasswordDTO.getPassword(), resetPasswordDTO.getPrePassword()))
            throw RestException.restThrow(MessageConstants.PASSWORDS_AND_PRE_PASSWORD_NOT_EQUAL);

        User user = userRepository.findByPhoneNumber(resetPasswordDTO.getPhoneNumber().toLowerCase()).orElseThrow(() -> RestException.restThrow(MessageConstants.USER_NOT_FOUND));

        if (!Objects.equals(user.getVerificationCode(), resetPasswordDTO.getVerificationCode()))
            throw RestException.restThrow(MessageConstants.VERIFICATION_CODE_INCORRECT_OR_USED);

        user.setVerificationCode(null);
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));
        userRepository.save(user);

        return Response.successResponseForMsg(MessageConstants.PASSWORD_SUCCESSFULLY_CHANGED);
    }

    @Override
    public Response<String> forgotPassword(String phoneNumber) {
        User user = userRepository.findByPhoneNumberAndEnabledTrue(phoneNumber.toLowerCase()).orElseThrow(() -> RestException.restThrow(MessageConstants.USER_NOT_FOUND));
        int verificationCode = RestConstants.random.nextInt(100000, 999999);
        user.setVerificationCode(verificationCode);
        userRepository.save(user);

        smsService.sendVerificationSms(user, String.valueOf(user.getVerificationCode()));

        return Response.successResponseForMsg(MessageConstants.SMS_SENT_FOR_FORGOT_PASSWORD);
    }

    @Override
    public Response<String> verifyAccount(VerificationCodeDTO verificationCodeDTO) {
        User user = userRepository.findByPhoneNumber(verificationCodeDTO.getPhoneNumber()).orElseThrow(() -> RestException.restThrow(MessageConstants.USER_NOT_FOUND));

        if (user.isEnabled())
            throw RestException.restThrow(MessageConstants.USER_ALREADY_VERIFIED);

        if (!Objects.equals(user.getVerificationCode(), verificationCodeDTO.getVerificationCode()))
            throw RestException.restThrow(MessageConstants.INCORRECT_VERIFICATION_CODE);

        user.setVerificationCode(null);
        user.setEnabled(true);
        userRepository.save(user);
        return Response.successResponseForMsg(MessageConstants.VERIFIED);
    }

    @Override
    public Response<String> checkPasswordVerificationCode(VerificationCodeDTO verificationCodeDTO) {
        if (!userRepository.existsByPhoneNumberAndVerificationCode(verificationCodeDTO.getPhoneNumber(), verificationCodeDTO.getVerificationCode()))
            throw RestException.restThrow(MessageConstants.VERIFICATION_CODE_ALREADY_USED_OR_INCORRECT);

        return Response.successResponse();
    }

    private TokenDTO makeTokenDTO(User user) {
        Timestamp tokenIssuedAt = new Timestamp(System.currentTimeMillis() / 1000 * 1000);
        String accessToken = jwtTokenProvider.generateAccessToken(user, tokenIssuedAt);

        user.setTokenIssuedAt(tokenIssuedAt);
        userRepository.save(user);

        return TokenDTO
                .builder()
                .accessToken(accessToken)
                .tokenType(RestConstants.TOKEN_TYPE)
                .build();
    }
}
