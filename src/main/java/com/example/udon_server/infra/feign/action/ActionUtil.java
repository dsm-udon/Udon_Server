package com.example.udon_server.infra.feign.action;

import com.example.udon_server.infra.feign.action.client.CivilDefenseActionFeignClient;
import com.example.udon_server.infra.feign.action.client.LifeSafetyActionFeignClient;
import com.example.udon_server.infra.feign.action.client.NaturalDisasterActionFeignClient;
import com.example.udon_server.infra.feign.action.client.SocialDefenseActionFeignClient;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ActionUtil {

    private final CivilDefenseActionFeignClient civilDefenseAction;

    private final LifeSafetyActionFeignClient lifeSafetyAction;

    private final NaturalDisasterActionFeignClient naturalDisasterAction;

    private final SocialDefenseActionFeignClient socialDefenseAction;

    @Value("feign.actionKey")
    private String serviceKey;

    @Transactional
    public void findAction(){

        final JSONObject civil = new JSONObject(civilDefenseAction.getCivil(serviceKey));

        final JSONObject life = new JSONObject(lifeSafetyAction.getLife(serviceKey));

        final JSONObject natural = new JSONObject(naturalDisasterAction.getNatural(serviceKey));

        final JSONObject social = new JSONObject(socialDefenseAction.getSocial(serviceKey));
    }
}
