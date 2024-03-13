package com.example.allforyourhome.utils;

import com.example.allforyourhome.controller.AuthController;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

public interface RestConstants {

    String[] OPEN_PAGES = {
            AuthController.BASE_PATH + "/**",
            RestConstants.BASE_PATH + "/v3/api-docs/**",
            RestConstants.BASE_PATH + "/swagger-ui/**",
            RestConstants.BASE_PATH + "/swagger-ui.html",
    };

    String BASE_PATH = "/api";
    String BASE_PATH_V1 = BASE_PATH + "/v1";

    String AUTHENTICATION_HEADER = "Authorization";
    ObjectMapper objectMapper = new ObjectMapper();
    Random random = new Random();

    String TOKEN_TYPE = "Bearer ";
    int INCORRECT_USERNAME_OR_PASSWORD = 3001;

    int REQUIRED = 3007;

    int SERVER_ERROR = 3008;
    int CONFLICT = 3009;
    int NO_ITEMS_FOUND = 3011;
    int CONFIRMATION = 3012;
    int USER_NOT_ACTIVE = 3013;
    int JWT_TOKEN_INVALID = 3014;
    String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@!?£$%^&\\-+*=/\\\\_])[A-Za-z\\d@!?£$%^&\\-+*=/\\\\_]{8,}$";
    String PHONE_REGEX = "^\\d{9}$";
    String UZB_CODE = "+998";
    String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    String NUMBER_REGEX = "^\\d+$";
    String LOWER_CASE = "[a-z]";
    String UPPER_CASE = "[A-Z]";
    String CONTAINS_NUMBERS = "[\\d]";
    String SPECIAL_SYMBOLS = "[@!?£$%^&\\-+*=/\\\\_]";
    String MIN_LENGTH = ".{8}";
    String MAX_LENGTH = "^.{8,30}$";
}
