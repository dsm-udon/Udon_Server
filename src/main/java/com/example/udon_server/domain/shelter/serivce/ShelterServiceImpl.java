package com.example.udon_server.domain.shelter.serivce;

import com.example.udon_server.domain.shelter.entity.Shelter;
import com.example.udon_server.domain.shelter.presentation.dto.request.ShelterDetailRequest;
import com.example.udon_server.domain.shelter.presentation.dto.response.ShelterListResponse;
import com.example.udon_server.domain.shelter.presentation.dto.response.ShelterResponse;
import com.example.udon_server.domain.shelter.repository.ShelterRepository;
import com.example.udon_server.domain.shelter.exception.ShelterNotFoundException;
import com.example.udon_server.infra.feign.shelter.ShelterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    private final ShelterUtil shelterUtil;

    @Override
    public void get() {
        shelterUtil.findPlace(
                "ce1Xt98RxWbgebuHIYVtCG4IPkRZ2BmKv0eHXGpU/Jv0JkJDyFs+yGzYULQQEOnQ01JERyyeTZr+RmHNgQT8zQ==",
                1,
                1,
                "json"
        );
    }

    @Override
    public ShelterListResponse getDetail(ShelterDetailRequest req) {
        List<Shelter> shelterList = shelterRepository.findAllShelter(req.getLat(), req.getLon());

        if (shelterList.isEmpty()) {
            throw ShelterNotFoundException.getInstance();
        }

        return ShelterListResponse.builder()
                .shelterList(shelterList.stream().map(ShelterResponse::of).toList())
                .build();
    }
}
