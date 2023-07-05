package com.example.udon_server.infra.feign.scheduler;

import com.example.udon_server.infra.feign.action.ActionUtil;
import com.example.udon_server.infra.feign.shelter.ShelterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeignScheduler {

    private final ShelterUtil shelterUtil;

    private final ActionUtil actionUtil;

    @Transactional
    @Scheduled(cron = "0 57 * * * *", zone = "Asia/Seoul")	// (Test용)
    //@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul") // 매일
    public void autoCrawlShelter() {

        try {
            shelterUtil.findShelter();
            log.info("Shelters Updated At" + ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        } catch (Exception e) {
            log.error(e.toString());
        }

    }

    //@Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")	// 1분마다 (Test용)
    //@Scheduled(cron = "0 0 0 1 * *", zone = "Asia/Seoul")
    public void authCrawlAction() {

        try {
            actionUtil.findAction();
            log.info("Actions Updated At" + ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
