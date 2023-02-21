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
public class CompanyJob {

    private final StockService stockService;

    @Scheduled(cron = "* */3 * * * *")
    public void process() {
        stockService.writeCompanyInfoToDB();
    }
}
