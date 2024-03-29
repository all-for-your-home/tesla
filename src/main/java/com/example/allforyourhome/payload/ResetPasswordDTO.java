package com.example.allforyourhome.payload;

import com.example.allforyourhome.utils.MessageConstants;
import com.example.allforyourhome.utils.RestConstants;
import jakarta.validation.constraints.*;
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

    @NotNull(message = MessageConstants.VERIFICATION_CODE_CAN_NOT_TO_NULL)
    @Min(value = 100000, message = MessageConstants.MIN_VALUE_IS_100000)
    @Max(value = 999999, message = MessageConstants.MAX_VALUE_IS_999999)
    private Integer verificationCode;
}
