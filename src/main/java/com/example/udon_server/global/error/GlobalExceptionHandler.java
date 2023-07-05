package com.example.udon_server.global.error;

import com.example.udon_server.global.error.data.BindExceptionResponse;
import com.example.udon_server.global.error.data.ErrorCode;
import com.example.udon_server.global.error.data.ErrorResponse;
import com.example.udon_server.global.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<BindExceptionResponse> bindException(BindException e) {
        return new ResponseEntity<>(ErrorResponse.of(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> businessException(BusinessException e) {

        final ErrorCode errorCode = e.getErrorCode();

        final ErrorResponse errorResponse = ErrorResponse.builder()
                .message(errorCode.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }
}
