package com.example.udon_server.domain.shelter.serivce;


import com.example.udon_server.domain.shelter.presentation.dto.request.ShelterDetailRequest;
import com.example.udon_server.domain.shelter.presentation.dto.response.ShelterListResponse;
import com.example.udon_server.domain.shelter.presentation.dto.response.ShelterResponse;

import java.util.List;

public interface ShelterService {

    ShelterListResponse getDetail(ShelterDetailRequest req);

    List<ShelterResponse> search(String keyword);
}
