package com.example.udon_server.domain.shelter.presentation;

import com.example.udon_server.domain.shelter.presentation.dto.request.ShelterDetailRequest;
import com.example.udon_server.domain.shelter.presentation.dto.response.ShelterListResponse;
import com.example.udon_server.domain.shelter.presentation.dto.response.ShelterResponse;
import com.example.udon_server.domain.shelter.serivce.ShelterService;
import com.example.udon_server.infra.feign.scheduler.FeignScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/shelter")
public class ShelterController {

    private final ShelterService shelterService;

    private final FeignScheduler findShelter;

    @GetMapping("/detail")
    @ResponseStatus(HttpStatus.OK)
    public ShelterListResponse getPlaceListDetail(@Valid @RequestBody ShelterDetailRequest req) {
        return shelterService.getDetail(req);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crawlShelter() {
        findShelter.autoCrawlShelter();
    }

    @GetMapping("/search")
    public List<ShelterResponse> search(@RequestParam("keyword") String keyword) {
        return shelterService.search(keyword);
    }
}
