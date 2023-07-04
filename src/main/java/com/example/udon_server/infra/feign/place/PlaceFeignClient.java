package com.example.udon.infra.feign.place;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client", url = "http://apis.data.go.kr/1741000/TsunamiShelter3", configuration = PlaceConfig.class)
public interface PlaceFeignClient {

    @GetMapping("/getTsunamiShelter1List")
    String getPlace(
            @RequestParam("ServiceKey") String serviceKey,
            @RequestParam("pageNo") Integer page,
            @RequestParam("numOfRows") Integer rows,
            @RequestParam("type") String type
    );

}