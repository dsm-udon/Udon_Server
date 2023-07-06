package com.example.udon_server.domain.shelter.entity;

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
@Entity(name = "shelter")
public class Shelter {

    @Id @Column(name = "idclass", columnDefinition = "BIGINT", nullable = false)
    private Long id;

    @Column(name = "shel_nm", columnDefinition = "VARCHAR(50)", nullable = false)
    private String shelNm;

    @Column(name = "lon", columnDefinition = "DECIMAL(12,8)", nullable = false)
    private BigDecimal lon; // 위도

    @Column(name = "lat", columnDefinition = "DECIMAL(12,8)", nullable = false)
    private BigDecimal lat; // 경도

    @Column(name = "address", columnDefinition = "VARCHAR(100)", nullable = false)
    private String address; // 주소

    @Column(name = "shel_av", columnDefinition = "BIGINT", nullable = false)
    private Long shelAv; // 수용 가능 인원

    @Column(name = "shel_div_type", columnDefinition = "VARCHAR(30)", nullable = false)
    private String shelDivType; // 대피소 분류 명

    @Column(name = "is_seismic", columnDefinition = "BIT", nullable = false)
    private Boolean isSeismic; // 내진 적용 유무
}
