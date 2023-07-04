package com.example.udon_server.infra.FCM.exception;

import com.example.udon_server.global.error.exception.BusinessException;
import com.example.udon_server.global.error.data.ErrorCode;

public class FCMTransferFailureException extends BusinessException {

    public FCMTransferFailureException() {
        super(ErrorCode.FCM_TRANSFER_FAILURE);
    }

    private static class SingletonHelper{
        private static final FCMTransferFailureException INSTANCE = new FCMTransferFailureException();
    }

    public static FCMTransferFailureException getInstance(){
        return FCMTransferFailureException.SingletonHelper.INSTANCE;
    }
}
