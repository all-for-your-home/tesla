package com.example.allforyourhome.exceptions;

import com.example.allforyourhome.utils.MessageConstants;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        if (status == 401)
            return RestException.restThrow(MessageConstants.SERVICE_USED_UNAUTHORIZED_TOKEN, HttpStatus.valueOf(500));
        if (500 > status && status >= 400)
            return RestException.restThrow(response.reason());
        return RestException.restThrow(MessageConstants.SMS_SERVICE_UNAVAILABLE);
    }
}
