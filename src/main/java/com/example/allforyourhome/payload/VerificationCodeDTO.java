package com.example.allforyourhome.payload;

import com.example.allforyourhome.utils.MessageConstants;
import com.example.allforyourhome.utils.RestConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCodeDTO {
    @NotBlank(message = MessageConstants.PHONE_NUMBER_CAN_NOT_BE_EMPTY)
    @Pattern(regexp = RestConstants.PHONE_REGEX, message = MessageConstants.PHONE_NUMBER_SHOULD_MATCH_REGEX)
    private String phoneNumber;

    @NotNull(message = MessageConstants.VERIFICATION_CODE_CAN_NOT_TO_NULL)
    @Min(value = 100000, message = MessageConstants.MIN_VALUE_IS_100000)
    @Max(value = 999999, message = MessageConstants.MAX_VALUE_IS_999999)
    private Integer verificationCode;
}
