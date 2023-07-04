package com.example.udon_server.global.error.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SHELTER_NOT_FOUND(404, "Not Shelter"),

    FCM_TRANSFER_FAILURE(500, "FCM Transfer Failure"),
    INTERNAL_SERVER_ERROR(500, "Some Thing Went Wrong")
    ;


    private final int status;

    private final String message;
}
