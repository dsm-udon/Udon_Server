package com.example.udon_server.domain.action.service;

import com.example.udon_server.domain.action.entity.Action;
import com.example.udon_server.domain.action.repository.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;

    @Override
    public String get(String safetyCateNm2, String safetyCateNm3) {
        List<Action> actionList = actionRepository.findAllBySafetyCateNm2AndSafetyCateNm3(safetyCateNm2, safetyCateNm3);

        String result = "";

        for (Action action: actionList) {
            result = result + "\n" + action.getContent();
        }

        return result;
    }
}
