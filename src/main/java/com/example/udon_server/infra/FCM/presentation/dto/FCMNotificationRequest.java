package com.example.udon_server.infra.FCM.presentation.dto;

import lombok.*;

@Builder
@Data
public class FCMNotificationRequest {

    private String title;

    private String body;
}
