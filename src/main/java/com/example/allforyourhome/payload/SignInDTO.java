package com.example.allforyourhome.payload;

import com.example.allforyourhome.utils.MessageConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDTO {

    @NotBlank(message = MessageConstants.PHONE_NUMBER_CAN_NOT_BE_EMPTY)
    private String phoneNumber;

    @NotBlank(message = MessageConstants.PASSWORD_CAN_NOT_BE_EMPTY)
    private String password;
}
