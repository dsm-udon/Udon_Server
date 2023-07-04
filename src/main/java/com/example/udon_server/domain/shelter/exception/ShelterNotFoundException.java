package com.example.udon_server.domain.shelter.exception;

import com.example.udon_server.global.error.data.ErrorCode;
import com.example.udon_server.global.error.exception.BusinessException;

public class ShelterNotFoundException extends BusinessException {

    private ShelterNotFoundException(){
        super(ErrorCode.SHELTER_NOT_FOUND);
    }

    private static class SingletonHelper{
        private static final ShelterNotFoundException INSTANCE = new ShelterNotFoundException();
    }

    public static ShelterNotFoundException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}