package com.example.udon_server.infra.feign.shelter;

import com.example.udon_server.domain.shelter.entity.Shelter;
import com.example.udon_server.domain.shelter.presentation.dto.response.ShelterResponse;
import com.example.udon_server.domain.shelter.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterUtil {

    private final ShelterRepository shelterRepository;

    private final ShelterFeignClient shelterFeignClient;

    public List<ShelterResponse> findPlace(String serviceKey, Integer page, Integer rows, String type) {

        final long totalCount = new JSONObject(shelterFeignClient.getPlace(serviceKey, page, rows, type))
                .getJSONArray("TsunamiShelter").getJSONObject(0).getJSONArray("head")
                .optJSONObject(0)
                .getLong("totalCount");

        List<ShelterResponse> result = new ArrayList<>();

        for (int i = 0; i < totalCount; i++){

            JSONObject data = new JSONObject(shelterFeignClient.getPlace(serviceKey, page + i, rows, type))
                    .getJSONArray("TsunamiShelter").getJSONObject(1).getJSONArray("row").getJSONObject(0);

            Shelter shelter = Shelter.builder()
                    .id(data.getLong("id"))
                    .shelNm(data.getString("shel_nm"))
                    .lon(data.getBigDecimal("lon"))
                    .lat(data.getBigDecimal("lat"))
                    .address(data.getString("address"))
                    .shelAv(data.getLong("shel_av"))
                    .shelDivType(data.getString("shel_div_type"))
                    .isSeismic(!data.getString("seismic").isEmpty())
                    .build();

            shelterRepository.save(shelter);

            result.add(ShelterResponse.of(shelter));
        }

        return result;

    }
}
