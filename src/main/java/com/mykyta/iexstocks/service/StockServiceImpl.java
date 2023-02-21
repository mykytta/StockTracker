package com.mykyta.iexstocks.service;

import com.mykyta.iexstocks.dto.StockDto;
import com.mykyta.iexstocks.model.Company;
import com.mykyta.iexstocks.model.Stock;
import com.mykyta.iexstocks.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class StockServiceImpl implements StockService {
    private final RestTemplate restTemplate;
    private final StockRepository stockRepository;
    private final CompanyService companyService;
    private final ExecutorService executorService;

    public List<Stock> get5TopVolumeCompanies() {
        List<Stock> top5 = stockRepository.getTopByVolume();
        if (top5.isEmpty()) {
            System.out.println("Waiting for info..");
        }
        top5.forEach((s) -> System.out.println(s.getCompanyName() + " " + s.getVolume()));
        System.out.println("------------------------");
        return top5;
    }

    public List<Stock> get5TopChangeCompanies() {
        List<Stock> top5 = stockRepository.getTopByChange();
        if (top5.isEmpty()) {
            System.out.println("Waiting for first updates..");
        }
        top5.forEach((s) -> System.out.println(s.getCompanyName() + " " + s.getChange() + "%"));
        System.out.println("------------------------");
        return top5;
    }

    public void writeCompanyInfoToDB() {
        List<Company> companies = companyService.getCompaniesDto();
        List<Stock> stockList = new CopyOnWriteArrayList<>();
        List<CompletableFuture<Void>> futureList = new ArrayList<>();

        companies.forEach(company -> {
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> restTemplate
                            .getForObject
                                    ("https://cloud.iexapis.com/stable/stock/" +
                                            company.getCompanyName() +
                                            "/quote?token=pk_0a4c90fecb8f466d8cd7220e70b0830a", StockDto.class)
                            .toStock(), executorService)
                    .thenAccept(stock -> {
                        stock.setId(company.getId());
                        stockList.add(stock);
                    });
            futureList.add(future.exceptionally(ex -> {
                return null;
            }));
        });
        futureList.forEach(CompletableFuture::join);
        stockRepository.saveAll(stockList);
    }
}
