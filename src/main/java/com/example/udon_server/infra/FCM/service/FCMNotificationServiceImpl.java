package com.example.udon_server.infra.FCM.service;

import com.example.udon_server.infra.FCM.exception.FCMKeyNotMatchedException;
import com.example.udon_server.infra.FCM.exception.FCMTransferFailureException;
import com.example.udon_server.infra.FCM.presentation.dto.FCMNotificationRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class FCMNotificationServiceImpl implements FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;

    @Value("${server.secretKey}")
    private String secretKey;

    @Override
    public void sendNotificationToAllUsers(FCMNotificationRequest req) {

        if (!Objects.equals(req.getSecretKey(), secretKey)) {

            log.error("알림 전송에 실패하였습니다.");
            throw FCMKeyNotMatchedException.getInstance();
        }

        Notification notification = Notification.builder()
                .setTitle(req.getTitle())
                .setBody(req.getMessage() + "\n" + req.getBody())
                .build();

        try {
            Message message = Message.builder()
                    .setNotification(notification)
                    .setTopic("all_users")
                    .build();

            firebaseMessaging.send(message);

        } catch (FirebaseMessagingException e) {

            log.error("알림 전송에 실패하였습니다.");
            throw FCMTransferFailureException.getInstance();
        }

        log.info("알림이 성공적으로 전송되었습니다.");
    }
}
