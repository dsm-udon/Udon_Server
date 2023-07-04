package com.example.udon_server.infra.feign.place;

import com.example.udon_server.domain.place.entity.Place;
import com.example.udon_server.domain.place.presentation.dto.PlaceResponse;
import com.example.udon_server.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceUtil {

    private final PlaceRepository placeRepository;

    private final PlaceFeignClient placeFeignClient;

    public List<PlaceResponse> findPlace(String serviceKey, Integer page, Integer rows, String type) {

        final JSONArray response = new JSONObject(placeFeignClient.getPlace(serviceKey, page, rows, type))
                .getJSONArray("TsunamiShelter");

        final long totalCount = response.getJSONObject(0).getJSONArray("head").optJSONObject(0)
                .getLong("totalCount");

        List<PlaceResponse> result = new ArrayList<>();

        for (int i = 0; i < totalCount; i++){

            JSONObject data = response.getJSONObject(1).getJSONArray("row").getJSONObject(i);

            Place place = Place.builder()
                    .id(data.getLong("id"))
                    .lon(data.getBigDecimal("lon").toString())
                    .lat(data.getBigDecimal("lat").toString())
                    .address(data.getString("address"))
                    .shelAv(data.getLong("shel_av"))
                    .shelDivType(data.getString("shel_div_type"))
                    .seismic(!data.getString("seismic").isEmpty())
                    .build();

            placeRepository.save(place);

            result.add(PlaceResponse.of(place));
        }

        return result;

    }
}
