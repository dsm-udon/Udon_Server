package com.example.udon_server.infra.feign.action;

import com.example.udon_server.domain.action.entity.Action;
import com.example.udon_server.domain.action.repository.ActionRepository;
import com.example.udon_server.infra.feign.action.client.CivilDefenseActionFeignClient;
import com.example.udon_server.infra.feign.action.client.LifeSafetyActionFeignClient;
import com.example.udon_server.infra.feign.action.client.NaturalDisasterActionFeignClient;
import com.example.udon_server.infra.feign.action.client.SocialDefenseActionFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActionUtil {

    private final ActionRepository actionRepository;

    private final CivilDefenseActionFeignClient civilDefenseAction;

    private final LifeSafetyActionFeignClient lifeSafetyAction;

    private final NaturalDisasterActionFeignClient naturalDisasterAction;

    private final SocialDefenseActionFeignClient socialDefenseAction;

    @Value("${feign.actionKey}")
    private String serviceKey;

    @Transactional
    public void findAction() throws Exception {


        final JSONObject life = XML.toJSONObject(lifeSafetyAction.getLife(serviceKey));

        final JSONObject natural = XML.toJSONObject(naturalDisasterAction.getNatural(serviceKey));

        final JSONObject social = XML.toJSONObject(socialDefenseAction.getSocial(serviceKey));
    }

    @Transactional
    public void findCivil() {

        int total = XML.toJSONObject(civilDefenseAction.getCivil(serviceKey, 1))
                .getJSONObject("response").getJSONObject("body").getInt("totalCount");


        for (int i = 1; i <= total; i++) {
            JSONArray jsonArray = XML.toJSONObject(civilDefenseAction.getCivil(serviceKey, i))
                    .getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");

            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject res = jsonArray.getJSONObject(j);


                String safetyCateNm1 = null;
                String safetyCateNm2 = null;
                String safetyCateNm3 = null;
                String imageUrl = null;
                try {
                    safetyCateNm1 = res.getString("safetyCateNm1");
                    safetyCateNm2 = res.getString("safetyCateNm2");
                    safetyCateNm3 = res.getString("safetyCateNm3");
                    imageUrl = res.getString("contentsUrl");
                } catch (JSONException e) {

                }


                String content = res.getString("actRmks");

                Action action = Action.builder()
                        .safetyCateNm1(safetyCateNm1)
                        .safetyCateNm2(safetyCateNm2)
                        .safetyCateNm3(safetyCateNm3)
                        .url(imageUrl)
                        .content(content)
                        .build();

                actionRepository.saveAndFlush(action);

            }

        }
    }
}
