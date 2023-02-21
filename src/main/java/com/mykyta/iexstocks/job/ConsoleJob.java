package com.mykyta.iexstocks.job;

import com.mykyta.iexstocks.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@EnableScheduling
@EnableAsync
public class ConsoleJob {

    private final StockService stockService;

    @Scheduled(cron = "*/5 * * * * *")
    public void showTop5Companies() {
        stockService.get5TopVolumeCompanies();
        stockService.get5TopChangeCompanies();
    }
}
