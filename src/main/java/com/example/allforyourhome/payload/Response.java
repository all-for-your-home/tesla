package com.example.allforyourhome.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private final boolean success;
    private String message;
    private T data;
    private List<ErrorData> errors;


    //RESPONSE WITH BOOLEAN (SUCCESS OR FAIL)
    private Response() {
        this.success = true;
    }


    //SUCCESS RESPONSE WITH DATA
    private Response(T data) {
        this();
        this.data = data;
    }

    //SUCCESS RESPONSE WITH DATA AND MESSAGE
    private Response(T data, String message) {
        this();
        this.data = data;
        this.message = message;
    }

    //SUCCESS RESPONSE WITH MESSAGE
    private Response(String message, boolean msg) {
        this();
        this.message = message;
    }

    //ERROR RESPONSE WITH MESSAGE AND ERROR CODE
    private Response(String errorMsg, Integer errorCode) {
        this.success = false;
        this.errors = Collections.singletonList(new ErrorData(errorMsg, errorCode));
    }

    //ERROR RESPONSE WITH ERROR DATA LIST
    private Response(List<ErrorData> errors) {
        this.success = false;
        this.errors = errors;
    }

    public static <E> Response<E> successResponse(E data) {
        return new Response<>(data);
    }

    public static <E> Response<E> successResponse(E data, String message) {
        return new Response<>(data, message);
    }

    public static <E> Response<E> successResponse() {
        return new Response<>();
    }

    public static Response<String> successResponseForMsg(String message) {
        return new Response<>(message, true);
    }


    public static Response<ErrorData> errorResponse(String errorMsg, Integer errorCode) {
        return new Response<>(errorMsg, errorCode);
    }

    public static Response<ErrorData> errorResponse(List<ErrorData> errors) {
        return new Response<>(errors);
    }
}
