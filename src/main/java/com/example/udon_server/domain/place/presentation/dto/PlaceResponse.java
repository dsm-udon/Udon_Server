package com.example.udon_server.domain.place.presentation.dto;

import com.example.udon_server.domain.place.entity.Place;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PlaceResponse {

    private long id;

    private String shelNm;

    private BigDecimal lon; // 위도

    private BigDecimal lat; // 경도

    private String address; // 주소

    private long shelAv; // 수용 가능 인원

    private String shelDivType; // 대피소 분류 명

    private boolean seismic; // 내진 적용 유무

    public static PlaceResponse of(Place place) {
        return PlaceResponse.builder()
                .id(place.getId())
                .shelNm(place.getShelNm())
                .lon(place.getLon())
                .lat(place.getLat())
                .address(place.getAddress())
                .shelAv(place.getShelAv())
                .shelDivType(place.getShelDivType())
                .seismic(place.isSeismic())
                .build();
    }
}
