package com.example.udon_server.domain.shelter.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShelterListResponse {

    private List<ShelterResponse> shelterList;
}