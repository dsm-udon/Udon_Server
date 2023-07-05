package com.example.udon_server.infra.feign.shelter;

import com.example.udon_server.domain.shelter.entity.Shelter;
import com.example.udon_server.domain.shelter.repository.ShelterRepository;
import com.example.udon_server.infra.feign.shelter.client.ShelterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShelterUtil {

    private final ShelterRepository shelterRepository;

    private final ShelterFeignClient shelterFeignClient;

    @Value("${feign.shelterKey}")
    private String serviceKey;

    @Transactional
    public void findShelter(){

        final long totalCount = new JSONObject(shelterFeignClient.getPlace(serviceKey, 1, 1, "json"))
                .getJSONArray("TsunamiShelter").getJSONObject(0).getJSONArray("head")
                .optJSONObject(0)
                .getLong("totalCount");

        List<Shelter> shelterList = new ArrayList<>();

        for (int i = 0; i < totalCount; i++){

            JSONObject data = new JSONObject(shelterFeignClient.getPlace(serviceKey, i + 1, 1, "json"))
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

            shelterList.add(shelter);

            log.info(shelter.toString());

        }

        shelterRepository.saveAllAndFlush(shelterList);

    }
}
