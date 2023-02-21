package com.mykyta.iexstocks.service;

import com.mykyta.iexstocks.model.Stock;

import java.util.List;

public interface StockService {
    List<Stock> get5TopVolumeCompanies();

    List<Stock> get5TopChangeCompanies();

    void writeCompanyInfoToDB();
}
