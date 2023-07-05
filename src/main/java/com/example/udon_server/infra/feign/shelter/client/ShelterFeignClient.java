package com.example.udon_server.infra.feign.shelter.client;

import com.example.udon_server.global.config.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "shelterClient", url = "http://apis.data.go.kr/1741000/TsunamiShelter3", configuration = FeignConfig.class)
public interface ShelterFeignClient {

    @GetMapping("/getTsunamiShelter1List")
    String getPlace(
            @RequestParam("ServiceKey") String serviceKey,
            @RequestParam("pageNo") Integer page,
            @RequestParam("numOfRows") Integer rows,
            @RequestParam("type") String type
    );

}