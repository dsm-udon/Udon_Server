package com.example.udon_server.global.error.data;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class ErrorResponse {

    private int status;

    private String message;

    public static ErrorResponse of(ErrorCode errorCode) {
        return  ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }

    public static BindExceptionResponse of(BindException e) {
        final HashMap<String, String> errorMap = new HashMap<>();

        for (FieldError error: e.getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }

        List<Map<String, String>> errorList = new ArrayList<>();
        errorList.add(errorMap);

        return BindExceptionResponse.builder()
                .fieldError(errorList)
                .build();
    }
}
