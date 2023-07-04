package com.example.udon.domain.place.presentation.dto;

import com.example.udon.domain.place.entity.Place;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceResponse {

    private long id;

    private String lon; // 위도

    private String lat; // 경도

    private String address; // 주소

    private long shelAv; // 수용 가능 인원

    private String shelDivType; // 대피소 분류 명

    private boolean seismic; // 내진 적용 유무

    public static PlaceResponse of(Place place) {
        return PlaceResponse.builder()
                .id(place.getId())
                .lon(place.getLon())
                .lat(place.getLat())
                .address(place.getAddress())
                .shelAv(place.getShelAv())
                .shelDivType(place.getShelDivType())
                .seismic(place.isSeismic())
                .build();
    }
}
