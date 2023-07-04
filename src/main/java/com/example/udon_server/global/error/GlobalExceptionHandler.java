package com.example.udon_server.global.error;

import com.example.udon_server.global.error.data.BindExceptionResponse;
import com.example.udon_server.global.error.exception.BusinessException;
import com.example.udon_server.global.error.data.ErrorCode;
import com.example.udon_server.global.error.data.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    protected BindExceptionResponse bindException(BindException e) {
        return ErrorResponse.of(e);
    }

    @ExceptionHandler(BusinessException.class)
    protected ErrorResponse businessException(BusinessException e) {

        final ErrorCode errorCode = e.getErrorCode();

        return ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }
}
