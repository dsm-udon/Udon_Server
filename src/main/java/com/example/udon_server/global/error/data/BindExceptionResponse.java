package com.example.udon_server.global.error.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class BindExceptionResponse {

    private List<Map<String, String>> fieldError;
}
