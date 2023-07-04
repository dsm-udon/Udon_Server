package com.example.udon_server.domain.shelter.presentation.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ShelterDetailRequest {

    @NotNull(message = "null일 수 없습니다.")
    private BigDecimal lon;

    @NotNull(message = "null일 수 없습니다.")
    private BigDecimal lat;
}
