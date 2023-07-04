package com.example.udon_server.global.error.exception;

import com.example.udon_server.global.error.data.ErrorCode;

public class InternalServerErrorException extends BusinessException {

    private InternalServerErrorException(){
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    private static class SingletonHelper{
        private static final InternalServerErrorException INSTANCE = new InternalServerErrorException();
    }

    public static InternalServerErrorException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}