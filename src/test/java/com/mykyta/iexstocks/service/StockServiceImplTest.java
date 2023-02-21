package com.mykyta.iexstocks.service;

import com.mykyta.iexstocks.model.Company;
import com.mykyta.iexstocks.model.Stock;
import com.mykyta.iexstocks.repository.CompanyRepository;
import com.mykyta.iexstocks.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class StockServiceImplTest {

    @MockBean
    private StockRepository stockRepository;

    @MockBean
    private CompanyRepository companyRepository;
    @Autowired
    private StockService stockService;

    @Test
    void get5TopVolumeCompanies() {
        when(stockRepository.getTopByVolume()).thenReturn(stockList());
        assertEquals(2, stockService.get5TopVolumeCompanies().size());
        assertNotNull(stockService.get5TopVolumeCompanies());
    }

    @Test
    void get5TopChangeCompanies() {
        when(stockRepository.getTopByChange()).thenReturn(stockList());
        assertEquals(2, stockService.get5TopChangeCompanies().size());
        assertNotNull(stockService.get5TopChangeCompanies());
    }


    private List<Stock> stockList(){
        return Stream.of(new Stock(1L, "BKNG", "someName", "USD", 23.32, 0.2, 3223L),
                new Stock(2L, "CDS", "otherName", "USD", 23.32, 0.2, 3223L)).collect(Collectors.toList());
    }
}