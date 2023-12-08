package com.mykyta.iexstocks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mykyta.iexstocks.model.Stock;
import lombok.Data;

@Data
public class StockDto {
    @JsonProperty(value = "symbol")
    private String symbol;
    @JsonProperty(value = "companyName")
    private String companyName;
    @JsonProperty(value = "currency")
    private String currency;
    @JsonProperty(value = "latestPrice")
    private double price;
    @JsonProperty(value = "change")
    private double change;
    @JsonProperty(value = "previousVolume")
    private Long volume;

    public Stock toStock() {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setCompanyName(companyName);
        stock.setCurrency(currency);
        stock.setPrice(price);
        stock.setChange(change);
        stock.setVolume(volume);

        return stock;
    }

    public StockDto fromStock(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setSymbol(stock.getSymbol());
        stockDto.setCompanyName(stock.getCompanyName());
        stockDto.setCurrency(stock.getCurrency());
        stockDto.setPrice(stock.getPrice());
        stockDto.setChange(stock.getChange());
        stockDto.setVolume(stock.getVolume());

        return stockDto;
    }
}