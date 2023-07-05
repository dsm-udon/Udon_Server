package com.example.udon_server.domain.shelter.serivce;

import com.example.udon_server.domain.shelter.entity.Shelter;
import com.example.udon_server.domain.shelter.exception.ShelterNotFoundException;
import com.example.udon_server.domain.shelter.presentation.dto.request.ShelterDetailRequest;
import com.example.udon_server.domain.shelter.presentation.dto.response.ShelterListResponse;
import com.example.udon_server.domain.shelter.presentation.dto.response.ShelterResponse;
import com.example.udon_server.domain.shelter.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

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

    public List<ShelterResponse> search(String keyword) {
        List<Shelter> shelters = shelterRepository.findByShelNmContaining(keyword);
        return shelters.stream().map(p -> new ShelterResponse(
                p.getId(),
                p.getShelNm(),
                p.getLon(),
                p.getLat(),
                p.getAddress(),
                p.getShelAv(),
                p.getShelDivType(),
                p.isSeismic())).collect(Collectors.toList());
    }
}
