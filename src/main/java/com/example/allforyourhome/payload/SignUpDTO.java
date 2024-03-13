package com.example.allforyourhome.payload;

import com.example.allforyourhome.utils.MessageConstants;
import com.example.allforyourhome.utils.RestConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {

    @NotBlank(message = MessageConstants.FIRST_NAME_CAN_NOT_BE_EMPTY)
    private String firstName;

    @NotBlank(message = MessageConstants.LAST_NAME_CAN_NOT_BE_EMPTY)
    private String lastName;

    @NotBlank(message = MessageConstants.PHONE_NUMBER_CAN_NOT_BE_EMPTY)
    @Pattern(regexp = RestConstants.PHONE_REGEX, message = MessageConstants.PHONE_NUMBER_SHOULD_MATCH_REGEX)
    private String phoneNumber;

    @NotBlank(message = MessageConstants.PASSWORD_CAN_NOT_BE_EMPTY)
    @Pattern(regexp = RestConstants.PASSWORD_REGEX, message = MessageConstants.PASSWORD_SHOULD_MATCH_REGEX)
    private String password;

    @NotBlank(message = MessageConstants.PRE_PASSWORD_CAN_NOT_BE_EMPTY)
    private String prePassword;
}
