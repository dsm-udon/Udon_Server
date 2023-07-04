package com.example.udon.domain.place.presentation;

import com.example.udon.domain.place.presentation.dto.PlaceListResponse;
import com.example.udon.domain.place.serivce.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;


    @GetMapping
    public PlaceListResponse getPlaceList() {
        return placeService.get();
    }
}
