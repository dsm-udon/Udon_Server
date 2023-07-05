package com.example.udon_server.infra.FCM.presentation.controller;

import com.example.udon_server.infra.FCM.presentation.dto.FCMNotificationRequest;
import com.example.udon_server.infra.FCM.service.FCMNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class FCMNotificationController {

    private final FCMNotificationService fcmService;

    @PostMapping("/send-notification")
    public void sendNotification(
        @RequestBody @Valid
        FCMNotificationRequest req
    ) {
        fcmService.sendNotificationToAllUsers(req);
    }
}
