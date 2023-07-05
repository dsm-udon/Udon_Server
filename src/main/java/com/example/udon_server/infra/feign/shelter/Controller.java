package com.example.udon_server.infra.feign.shelter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final ShelterUtil shelterUtil;

    @PutMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void crawl(){
        shelterUtil.findShelter();
    }
}
