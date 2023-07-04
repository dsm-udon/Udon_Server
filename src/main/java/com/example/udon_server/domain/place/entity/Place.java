package com.example.udon_server.domain.place.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "places")
public class Place {

    @Id @Column(name = "id", columnDefinition = "BIGINT", nullable = false)
    private long id;

    @Column(name = "shel_nm", columnDefinition = "VARCHAR(255)", nullable = false)
    private String shelNm;

    @Column(name = "lon", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal lon; // 위도

    @Column(name = "lat", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal lat; // 경도

    @Column(name = "address", columnDefinition = "VARCHAR(250)", nullable = false)
    private String address; // 주소

    @Column(name = "shel_av", columnDefinition = "BIGINT", nullable = false)
    private long shelAv; // 수용 가능 인원

    @Column(name = "shel_div_type", columnDefinition = "VARCHAR(255)", nullable = false)
    private String shelDivType; // 대피소 분류 명

    @Column(name = "is_seismic", columnDefinition = "BIT", nullable = false)
    private boolean isSeismic; // 내진 적용 유무
}
