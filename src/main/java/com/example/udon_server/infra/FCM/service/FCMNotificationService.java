package com.example.udon_server.infra.FCM.service;

import com.example.udon_server.infra.FCM.presentation.dto.FCMNotificationRequest;

public interface FCMNotificationService {

    void sendNotificationToAllUsers(FCMNotificationRequest req);
}
