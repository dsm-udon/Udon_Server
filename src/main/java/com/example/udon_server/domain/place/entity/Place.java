package com.example.udon.domain.place.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "place")
public class Place {

    @Id @Column(name = "id", columnDefinition = "BIGINT")
    private long id;

    @Column(name = "lon", columnDefinition = "BIGDECIMAL")
    private String lon; // 위도

    @Column(name = "lat", columnDefinition = "BIGDECIMAL")
    private String lat; // 경도

    @Column(name = "address", columnDefinition = "VARCHAR(250)")
    private String address; // 주소

    @Column(name = "shel_av", columnDefinition = "BIGINT")
    private long shelAv; // 수용 가능 인원

    @Column(name = "shel_div_type", columnDefinition = "VARCHAR(255)")
    private String shelDivType; // 대피소 분류 명

    @Column(name = "seismic", columnDefinition = "BIT")
    private boolean seismic; // 내진 적용 유무
}
