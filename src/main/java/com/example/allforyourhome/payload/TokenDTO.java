package com.example.allforyourhome.payload;


import com.example.allforyourhome.utils.RestConstants;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenDTO {

    private final String tokenType = RestConstants.TOKEN_TYPE;
    private String accessToken;
    private String refreshToken;
}
