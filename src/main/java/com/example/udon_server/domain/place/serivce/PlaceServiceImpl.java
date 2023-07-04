package com.example.udon_server.domain.place.serivce;

import com.example.udon_server.domain.place.presentation.dto.PlaceListResponse;
import com.example.udon_server.infra.feign.place.PlaceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceUtil placeUtil;

    @Override
    public PlaceListResponse get() {
        return PlaceListResponse.builder()
            .placeList(
                placeUtil.findPlace(
                    "ce1Xt98RxWbgebuHIYVtCG4IPkRZ2BmKv0eHXGpU/Jv0JkJDyFs+yGzYULQQEOnQ01JERyyeTZr+RmHNgQT8zQ==",
                    1,
                    1,
                    "json"
                )
            ).build();
    }
}
