package com.mykyta.iexstocks.service;

import com.mykyta.iexstocks.dto.StockDto;
import com.mykyta.iexstocks.model.Company;
import com.mykyta.iexstocks.model.Stock;
import com.mykyta.iexstocks.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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
        Executor executor = getCustomExecutor();

        List<CompletableFuture<Stock>> futures = getAllSports().stream()
                .map(stockDto -> CompletableFuture.supplyAsync(stockDto::toStock, executor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0]));

        CompletableFuture<List<Stock>> result = allOf.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        );

        List<Stock> stockList = result.join();
        stockRepository.saveAll(stockList);
    }

    private Executor getCustomExecutor() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public List<StockDto> getAllSports() {
        return WebClient.create("")
                .get()
                .retrieve()
                .bodyToFlux(StockDto.class)
                .collectList()
                .block();
    }
}
