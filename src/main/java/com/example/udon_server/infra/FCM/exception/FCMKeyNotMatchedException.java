package com.example.udon_server.infra.FCM.exception;

import com.example.udon_server.global.error.data.ErrorCode;
import com.example.udon_server.global.error.exception.BusinessException;

public class FCMKeyNotMatchedException extends BusinessException {

    public FCMKeyNotMatchedException() {
        super(ErrorCode.FCM_KEY_NOT_MATCHED);
    }

    private static class SingletonHelper{
        private static final FCMKeyNotMatchedException INSTANCE = new FCMKeyNotMatchedException();
    }

    public static FCMKeyNotMatchedException getInstance(){
        return FCMKeyNotMatchedException.SingletonHelper.INSTANCE;
    }
}
