package com.example.allforyourhome.payload;

import com.example.allforyourhome.utils.MessageConstants;
import com.example.allforyourhome.utils.RestConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDTO {

    @NotBlank(message = MessageConstants.PHONE_NUMBER_CAN_NOT_BE_EMPTY)
    private String phoneNumber;

    @NotBlank(message = MessageConstants.PASSWORD_CAN_NOT_BE_EMPTY)
    @Pattern(regexp = RestConstants.PASSWORD_REGEX, message = MessageConstants.PASSWORD_SHOULD_MATCH_REGEX)
    private String password;

    @NotBlank(message = MessageConstants.PRE_PASSWORD_CAN_NOT_BE_EMPTY)
    private String prePassword;

    @NotBlank
    private String verificationCode;
}
