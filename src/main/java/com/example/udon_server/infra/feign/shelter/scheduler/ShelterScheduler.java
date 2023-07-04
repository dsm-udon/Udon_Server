package com.example.udon_server.infra.feign.shelter.scheduler;

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
@Transactional(readOnly = true)
public class ShelterScheduler {

    private final ShelterUtil shelterUtil;

    //@Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")	// 1분마다 (Test용)
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul") // 매일
    public void autoCrawlShelter() {

        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        try {
            shelterUtil.findPlace();

            log.info("shelter updated" + curDate);
        } catch (Exception e) {
            log.error(e.toString());
        }

    }
}
