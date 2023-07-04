package com.example.udon_server.infra.feign.place;

import com.example.udon_server.domain.place.entity.Place;
import com.example.udon_server.domain.place.presentation.dto.PlaceResponse;
import com.example.udon_server.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceUtil {

    private final PlaceRepository placeRepository;

    private final PlaceFeignClient placeFeignClient;

    public List<PlaceResponse> findPlace(String serviceKey, Integer page, Integer rows, String type) {

        final long totalCount = new JSONObject(placeFeignClient.getPlace(serviceKey, page, rows, type))
                .getJSONArray("TsunamiShelter").getJSONObject(0).getJSONArray("head")
                .optJSONObject(0)
                .getLong("totalCount");

        List<PlaceResponse> result = new ArrayList<>();

        for (int i = 0; i < totalCount; i++){

            JSONObject data = new JSONObject(placeFeignClient.getPlace(serviceKey, page + i, rows, type))
                    .getJSONArray("TsunamiShelter").getJSONObject(1).getJSONArray("row").getJSONObject(0);

            Place place = Place.builder()
                    .id(data.getLong("id"))
                    .lon(data.getBigDecimal("lon"))
                    .lat(data.getBigDecimal("lat"))
                    .address(data.getString("address"))
                    .shelAv(data.getLong("shel_av"))
                    .shelDivType(data.getString("shel_div_type"))
                    .isSeismic(!data.getString("seismic").isEmpty())
                    .build();

            placeRepository.save(place);

            result.add(PlaceResponse.of(place));
        }

        return result;

    }
}
