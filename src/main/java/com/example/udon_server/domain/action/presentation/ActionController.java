package com.example.udon_server.domain.action.presentation;

import com.example.udon_server.domain.action.service.ActionService;
import com.example.udon_server.infra.feign.action.ActionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/action")
public class ActionController {

    private final ActionUtil actionUtil;

    private final ActionService actionService;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crawlAction(){
        actionUtil.findCivil();
    }

    @GetMapping("/civil")
    public void getAction() {
        System.out.println(actionService.get("태풍", "태풍 특보 중 행동요령"));
    }
}
