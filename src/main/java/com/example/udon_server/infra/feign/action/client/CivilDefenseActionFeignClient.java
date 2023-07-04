package com.example.udon_server.infra.feign.action.client;

import com.example.udon_server.global.config.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "actionClient", url = "http://openapi.safekorea.go.kr/openapi/service", configuration = FeignConfig.class)
public interface CivilDefenseActionFeignClient {

    @GetMapping("/behaviorconduct/disaster/civildefence/total/list")
    String getCivil(@RequestParam("serviceKey") String serviceKey);

}