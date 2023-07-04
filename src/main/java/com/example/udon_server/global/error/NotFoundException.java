package com.example.udon_server.global.error;

public class NotFoundException extends BusinessException {

    private NotFoundException(){
        super(ErrorCode.NOT_FOUND);
    }

    private static class SingletonHelper{
        private static final NotFoundException INSTANCE = new NotFoundException();
    }

    public static NotFoundException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}