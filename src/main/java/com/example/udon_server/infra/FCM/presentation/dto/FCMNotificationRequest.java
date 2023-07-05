package com.example.udon_server.infra.FCM.presentation.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class FCMNotificationRequest {


    @NotBlank(message = "title을 입력해주세요")
    private String title;

    @NotBlank(message = "body 입력해주세요")
    private String body;

    @NotBlank(message = "area을 입력해주세요")
    private String area;

    @NotBlank(message = "message을 입력해주세요")
    private String message;
}
