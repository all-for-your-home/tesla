package com.example.allforyourhome.payload;


import com.example.allforyourhome.utils.RestConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO {
    private Data data;
    private String message;
    private String accessToken;
    private String tokenType = RestConstants.TOKEN_TYPE;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private String token;
    }
}
