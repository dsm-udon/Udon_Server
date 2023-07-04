package com.example.udon_server.domain.place.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PlaceListResponse {

    private long numOfRows; // 페이지당 결과 수

    private long pageNo; // page 번호

    private long totalCount; // 전체 결과 수

    private List<PlaceResponse> placeList;
}