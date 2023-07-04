package com.example.udon_server.domain.shelter.presentation.dto.response;

import com.example.udon_server.domain.shelter.entity.Shelter;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ShelterResponse {

    private long id;

    private String shelNm;

    private BigDecimal lon; // 위도

    private BigDecimal lat; // 경도

    private String address; // 주소

    private long shelAv; // 수용 가능 인원

    private String shelDivType; // 대피소 분류 명

    private boolean seismic; // 내진 적용 유무

    public static ShelterResponse of(Shelter shelter) {
        return ShelterResponse.builder()
                .id(shelter.getId())
                .shelNm(shelter.getShelNm())
                .lon(shelter.getLon())
                .lat(shelter.getLat())
                .address(shelter.getAddress())
                .shelAv(shelter.getShelAv())
                .shelDivType(shelter.getShelDivType())
                .seismic(shelter.isSeismic())
                .build();
    }
}
